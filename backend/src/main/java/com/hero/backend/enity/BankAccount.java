package com.hero.backend.enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "t_banks")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String telephone;

    @ManyToOne
    private TgUser user;

    public BankAccount(String name, String telephone, TgUser user) {
        this.name = name;
        this.telephone = telephone;
        this.user = user;
    }
}
