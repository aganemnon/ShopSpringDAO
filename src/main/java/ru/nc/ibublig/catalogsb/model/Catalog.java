package ru.nc.ibublig.catalogsb.model;

import java.util.List;

public class Catalog {

    private Long id;
    private String name;
    private List<Item> items;

    public Catalog() {
    }

    public Catalog(Long id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
