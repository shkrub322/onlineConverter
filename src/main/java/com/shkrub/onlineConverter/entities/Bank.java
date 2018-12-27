package com.shkrub.onlineConverter.entities;

import javax.persistence.*;

@Entity
@Table(name = "bank")
public class Bank implements Comparable<Bank> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Bank() {
    }

    public Bank(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object object) {
        Bank bank = null;
        if (object instanceof Bank) {
            bank = (Bank) object;
        }

        if (object instanceof String) {
            return name.equals(object);
        }

        if (bank != null) {
            return name.equals(bank.name);
        }

        return false;
    }

    @Override
    public int compareTo(Bank bank) {
        return this.name.compareTo(bank.name);
    }
}
