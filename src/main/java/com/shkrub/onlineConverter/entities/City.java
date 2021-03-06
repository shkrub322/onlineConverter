package com.shkrub.onlineConverter.entities;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    public City() {

    }

    public City(String name, Region region) {
        this.name = name;
        this.region = region;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public boolean equals(Object object) {
        City city = null;
        if (object instanceof City) {
            city = (City) object;
        }

        if (object instanceof String) {
            return name.equals(object);
        }

        if (city != null) {
            return name.equals(city.name);
        }

        return false;
    }
}
