package com.dietsheet.model;

import javax.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @Column(name = "ingredient_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name ="product_id")
    private Product product;

    @Column(name = "amount")
    private int amount;

    public Ingredient() {
    }

    public Ingredient(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

//    public Meal getMeal() {
//        return meal;
//    }
//
//    public void setMeal(Meal meal) {
//        this.meal = meal;
//    }
}
