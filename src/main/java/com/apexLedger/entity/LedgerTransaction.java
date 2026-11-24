package com.apexLedger.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "ledger_transaction")
public class LedgerTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate txnDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<LedgerEntry> entries = new ArrayList<>();

    @PrePersist
    void onCreate() { createdAt = LocalDateTime.now(); }

    public void addEntry(LedgerEntry entry) {   // keeps both sides linked
        entries.add(entry);
        entry.setTransaction(this);
    }

    // getters and setters
}