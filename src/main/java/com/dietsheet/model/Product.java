package com.dietsheet.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nutrients_id", referencedColumnName = "nutrients_id", unique = true)
    private Nutrients nutrients;

    @Column(name = "kcal")
    private int kcal;

    @ManyToMany(mappedBy = "products")
    private Set<Meal> meals = new HashSet<>();

    public Product() {
    }

    public Product(String name, Nutrients nutrients, int kcal) {
        this.name = name;
        this.nutrients = nutrients;
        this.kcal = kcal;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nutrients getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }
}
