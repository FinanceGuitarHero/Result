package com.hero.backend.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class BotCommandControllerConfiguration {

    private final ApplicationContext context;

    private final Map<String, Method> commandHandlers = new HashMap<>();
    private final Map<Method, Object> beanCache = new HashMap<>();


    @PostConstruct
    public void init() {
        Map<String, Object> beans = context.getBeansWithAnnotation(CommandsController.class);
        for (Object bean : beans.values()) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(BotCommand.class)) {
                    BotCommand annotation = method.getAnnotation(BotCommand.class);
                    commandHandlers.put(annotation.value(), method);
                    beanCache.put(method, bean); // Сохранение бина в кэше
                }
            }
        }
    }

    public String handle(Update update){
        String command = update.getMessage().getText();
        Method method = commandHandlers.getOrDefault(command, commandHandlers.get("/unknown"));
        try {
            Object bean = beanCache.get(method);
            if (method.getParameterCount() == 0)
                return (String) method.invoke(bean);
            return (String) method.invoke(bean, update);
        } catch (Exception e) {
            log.error("Error in method {} invocation", method.getName());
        }
        return "Ошибка на сервере";
    }
}
