package com.hero.backend.enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "t_users")
public class TgUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tg_id")
    private String tgId;

    @OneToMany(mappedBy = "user")
    private List<BankAccount> banks;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    public TgUser(String name, String tgId) {
        this.name = name;
        this.tgId = tgId;
    }
}
