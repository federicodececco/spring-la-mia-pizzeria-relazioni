package org.spring_la_mia_pizzeria.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @NotNull(message = "The order time cannot be null")
    @PastOrPresent(message = "The order time cannot be set in the future")
    private LocalTime orderTime;

    @PastOrPresent(message = "The delivery time cannot be set in the future")
    private LocalTime deliveryTime;

    @Lob
    private String notes;

    public Order(int id, LocalTime orderTime, LocalTime deliveryTime, String notes) {
        this.id = id;
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
        this.notes = notes;

    }

    public Order() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setOrderTime(LocalTime time) {
        this.orderTime = time;
    }

    public void setDeliveryTime(LocalTime time) {
        this.deliveryTime = time;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

    public String getNotes() {
        return notes;
    }

}
