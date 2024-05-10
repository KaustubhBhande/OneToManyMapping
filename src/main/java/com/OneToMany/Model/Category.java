package com.OneToMany.Model;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;
    
   
    private String name;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="pid",referencedColumnName = "category_id")
    private List<Product> products;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Category(int id, String name, List<Product> products) {
        super();
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Category() {
        super();
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", products=" + products + "]";
    }
}
