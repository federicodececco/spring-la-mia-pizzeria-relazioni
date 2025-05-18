package org.spring_la_mia_pizzeria.repository;

import org.spring_la_mia_pizzeria.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {

}
