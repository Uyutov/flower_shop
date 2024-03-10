package uyutov.flower_shop.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private Integer order_id;
    private Float total_price;
    private List<Flower> flowers;
    private Date order_date;

    public Order(Integer orderId) {
        order_id = orderId;
        flowers = new ArrayList<>();
    }
    public Order() {
        flowers = new ArrayList<>();
    }
    public void addFlower(Flower flower)
    {
        flowers.removeIf(flower1 -> flower1.getName().equals(flower.getName()));
        flowers.add(flower);
    }
    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }

    public Float getSumTotal_price() {
        total_price=0.f;
        for(Flower flower:flowers)
        {
            total_price += flower.getPrice()*flower.getAmount();
        }
        return total_price;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public void addToTotal_price(Float price) {
        this.total_price = price;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    @Override
    public String toString() {
        return "{" +
                "order_id=" + order_id +
                ", total_price=" + total_price +
                ", flowers=" + flowers +
                ", order_date=" + order_date +
                '}';
    }
}
