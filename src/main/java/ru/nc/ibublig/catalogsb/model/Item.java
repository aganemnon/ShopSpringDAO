package ru.nc.ibublig.catalogsb.model;

public class Item {

    private Long id;
    private String name;
    private String description;
    private Long cost;
    private String image;
    private Long categoryId;

    public Item() {
    }

    public Item(Long id, String name, String description, Long cost, String image, Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.image = image;
        this.categoryId = categoryId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
