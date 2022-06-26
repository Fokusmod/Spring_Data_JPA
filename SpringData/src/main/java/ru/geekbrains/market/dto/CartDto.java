package ru.geekbrains.market.dto;

import lombok.ToString;

@ToString
public class CartDto {

    private String title;
    private int price;
    private int count;

    public CartDto(String title, int cost) {
        this.title = title;
        this.price = cost;
        this.count = 1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
