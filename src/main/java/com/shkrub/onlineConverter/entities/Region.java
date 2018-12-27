package com.shkrub.onlineConverter.entities;

import javax.persistence.*;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Region() {
    }

    public Region(String name) {
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
        Region region = null;
        if (object instanceof Region) {
            region = (Region) object;
        }

        if (object instanceof String) {
            return name.equals(object);
        }

        if (region != null) {
            return name.equals(region.name);
        }

        return false;
    }
}
