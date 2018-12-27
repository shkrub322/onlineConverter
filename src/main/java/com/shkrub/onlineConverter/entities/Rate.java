package com.shkrub.onlineConverter.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String operation;
    private Double value;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Rate() {
    }

    public Rate(String name, String operation, Double value, Department department) {
        this.name = name;
        this.operation = operation;
        this.value = value;
        this.department = department;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object obj) {
        Rate rate = null;
        if (obj instanceof Rate) {
            rate = (Rate) obj;
        }

        if (rate != null) {
            return name.equals(rate.name) && department.equals(rate.department) &&
                    operation.equals(rate.operation);
        }

        return false;
    }
}
