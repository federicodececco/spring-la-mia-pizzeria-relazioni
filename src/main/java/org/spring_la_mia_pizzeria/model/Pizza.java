package org.spring_la_mia_pizzeria.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 18, message = "name must be of a length between 3 and 18 characters")
    @Column(nullable = false)
    @NotBlank(message = "name cannot be nor blank nor empty")
    private String name;

    @Lob
    private String description;

    private String photoUrl;

    @NotNull(message = "price cannot be empty")
    @Min(value = 0, message = "price cannot be negative")
    private Double price;

    @OneToMany(mappedBy = "pizza", cascade = { CascadeType.REMOVE })
    private List<Order> orders;

    @OneToMany(mappedBy = "pizza", cascade = { CascadeType.REMOVE })
    private List<SpecialOffer> specialOffers;

    @ManyToMany()
    @JoinTable(name = "ingredient_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    /* constructors */
    public Pizza(int id, String name, String description, String photoUrl, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
        this.price = price;
    }

    public Pizza() {

    }

    /* setters and getters */

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Double getPrice() {
        return price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {

        return String.format("id = '%s', name = '%s', price = '%.2f'", String.valueOf(id), name, price);
    }
}
