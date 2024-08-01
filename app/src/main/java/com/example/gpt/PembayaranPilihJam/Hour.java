package com.example.gpt.PembayaranPilihJam;

import java.io.Serializable;

public class Hour implements Serializable {
    private String name;
    private double price;

    public Hour(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
