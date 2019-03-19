package com.dietsheet.service;

import com.dietsheet.DAO.MealDAO;
import com.dietsheet.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service("mealService")
public class MealServiceImpl implements Service<Meal> {

    @Autowired
    private MealDAO mealDAO;

    @Override
    public Meal findById(long id) {
        return mealDAO.get(id);
    }

    @Override
    public Meal findByName(String name) {
        return null;
    }

    @Override
    public void save(Meal meal) {
        mealDAO.save(meal);
    }

    @Override
    public void update(Meal meal) {
        mealDAO.update(meal);
    }

    @Override
    public void deleteById(long id) {
        mealDAO.delete(id);
    }

    @Override
    public List<Meal> findAll() {
        return mealDAO.getAll();
    }

    @Override
    public void deleteAll() {
        mealDAO.deleteAll();
    }

    @Override
    public boolean isExist(Meal meal) {
        return mealDAO.get(meal.getId()) != null;
    }
}
