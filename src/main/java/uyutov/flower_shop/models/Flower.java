package uyutov.flower_shop.models;

import java.io.Serializable;

public class Flower implements Serializable {
    private Integer flower_id;
    private String name;
    private Integer amount;
    private Float price;
    private Integer length;
    private String color;
    private String icon;
    public Flower(Integer flower_id, String name, Integer amount, Float price, Integer length, String color) {
        this.flower_id = flower_id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.length = length;
        this.color = color;
    }

    public Flower() {}

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getFlower_id() {
        return flower_id;
    }

    public void setFlower_id(Integer flower_id) {
        this.flower_id = flower_id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconPath()
    {
        if(icon == null) return null;
        return "flower-icons/" + name + "/"+icon;
    }
    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
