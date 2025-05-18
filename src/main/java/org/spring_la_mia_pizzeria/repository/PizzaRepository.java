package org.spring_la_mia_pizzeria.repository;

import java.util.List;

import org.spring_la_mia_pizzeria.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
     public List<Pizza> findByNameContaining(String name);

}