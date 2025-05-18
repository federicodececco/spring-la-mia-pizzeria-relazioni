package org.spring_la_mia_pizzeria.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "special_offers")
public class SpecialOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @NotNull(message = "the offer must have a name")
    @Size(min = 3, message = "the title must be at least 3 character long")
    private String title;

    @NotNull(message = "start date cannot be null")
    // @PastOrPresent(message = "the offer cannot start in the past")
    private LocalDate startDate;

    private LocalDate endDate;

    public SpecialOffer(Integer id, Pizza pizza, String title, LocalDate starDate, LocalDate endDate) {
        this.id = id;
        this.pizza = pizza;
        this.title = title;
        this.startDate = starDate;
        this.endDate = endDate;
    }

    public SpecialOffer() {
    }

    public Integer getId() {
        return id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDate date) {
        startDate = date;
    }

    public void setEndDate(LocalDate date) {
        endDate = date;
    }

    @Override
    public String toString() {

        return String.format("id = '%d', title = '%s', start date = '%s', end date = '%s', pizza = '%s' ",
                String.valueOf(id), title,
                String.valueOf(startDate), String.valueOf(endDate), pizza.getName());
    }
}
