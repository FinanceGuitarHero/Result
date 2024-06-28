package com.hero.backend.enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "t_transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Category category;

    private boolean type;

    private Long amount;

    @ManyToOne
    private TgUser user;

    public Transaction(Category category, boolean type, Long amount, TgUser user) {
        this.category = category;
        this.type = type;
        this.amount = amount;
        this.user = user;
    }
}
