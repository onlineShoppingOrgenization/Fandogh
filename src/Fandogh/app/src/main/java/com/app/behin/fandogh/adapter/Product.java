package com.app.behin.fandogh.adapter;

/**
 * Created by najme_sa on 24/07/2016.
 */
public class Product {
    private String name, amount, desc;

    public Product() {
    }

    public Product(String name, String amount, String desc) {
        this.name = name;
        this.amount = amount;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
