package com.dietsheet.controller;



import com.dietsheet.model.Meal;
import com.dietsheet.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class MealController {

    @Autowired
    Service<Meal> mealService;

    @RequestMapping(value = "/meal/", method = RequestMethod.GET)
    public ResponseEntity<List<Meal>> getAllMeals() {
        List<Meal> meals = mealService.findAll();
        if(meals.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @RequestMapping(value = "/meal/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> getMeal(@PathVariable("id") long id) {
        Meal meal = mealService.findById(id);
        if (meal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(meal, HttpStatus.OK);
    }

    @RequestMapping(value = "/meal/", method = RequestMethod.POST)
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal, UriComponentsBuilder ucBuilder) {

        if (mealService.isExist(meal)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        mealService.save(meal);
        return new ResponseEntity<>(meal, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/meal/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Meal> updateMeal(@PathVariable("id") long id, @RequestBody Meal meal) {

        Meal mealToUpdate = mealService.findById(id);

        if (mealToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        mealToUpdate.setName(meal.getName());
        mealToUpdate.setIngredients(meal.getIngredients());

        mealService.update(mealToUpdate);
        return new ResponseEntity<>(mealToUpdate, HttpStatus.OK);
    }

    @RequestMapping(value = "/meal/", method = RequestMethod.DELETE)
    public ResponseEntity<Meal> deleteAllMeals() {
        mealService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/meal/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Meal> deleteMeal(@PathVariable("id") long id) {
        Meal meal = mealService.findById(id);
        if (meal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        mealService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
