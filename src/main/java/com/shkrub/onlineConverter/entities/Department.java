package com.shkrub.onlineConverter.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public Department() {
    }

    public Department(String name, String address, City city, Bank bank) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.bank = bank;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object obj) {
        Department department = null;
        if (obj instanceof Department) {
            department = (Department) obj;
        }

        if (department != null) {
            return name.equals(department.name) && address.equals(department.address);
        }

        return false;
    }
}
