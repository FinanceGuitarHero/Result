package com.hero.botClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class BotClientApplication {

    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(BotClientApplication.class, args);
    }
}
