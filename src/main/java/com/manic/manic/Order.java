package com.manic.manic;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Burger_Order")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    private String personName;
    
    private String state;
    
    private String city;
    
    private String street;
    
    private String zip;
    
    private String ccNumber;
    
    private String ccExpiration;
    
    private String ccCVV;

    @ManyToOne
    private User user;

    @ManyToMany(targetEntity = Burger.class)
    private List<Burger> burgers = new ArrayList<>();

    public void addBurger(Burger burger) {
        this.burgers.add(burger);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
    
}
