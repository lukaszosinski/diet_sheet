package com.dietsheet.DAO;

import com.dietsheet.model.Meal;
import org.springframework.stereotype.Component;

@Component("mealDAO")
public class MealDAO extends AbstractDAO<Meal> {
    public MealDAO() {
        setClazz(Meal.class);
    }
}