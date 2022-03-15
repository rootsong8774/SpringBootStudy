package com.tacojdbctemplate.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Taco {

    private Integer id;
    private Date createdAt = new Date();

    private String name;
    private List<IngredientRef> ingredients = new ArrayList<>();
    private void addIngredient(Ingredient taco) {
        this.ingredients.add(new IngredientRef(taco.getId()));
    }
}
