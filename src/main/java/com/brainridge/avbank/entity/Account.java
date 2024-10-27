package com.brainridge.avbank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="accounts")
public class Account {

    @Id
    @SequenceGenerator(name="account_id_sequence", sequenceName = "account_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_sequence")
    private Long id;

    @Column(name="ownerName", nullable = false)
    private String ownerName;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    public Account(String ownerName, String email, BigDecimal balance) {
        this.ownerName = ownerName;
        this.email = email;
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
    }
}
