package com.dietsheet.controller;



import com.dietsheet.model.Day;
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
public class DayController {

    @Autowired
    Service<Day> dayService;

    @RequestMapping(value = "/day/", method = RequestMethod.GET)
    public ResponseEntity<List<Day>> getAllDays() {
        List<Day> days = dayService.findAll();
        if(days.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(days, HttpStatus.OK);
    }

    @RequestMapping(value = "/day/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Day> getDay(@PathVariable("id") long id) {
        Day day = dayService.findById(id);
        if (day == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(day, HttpStatus.OK);
    }

    @RequestMapping(value = "/day/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Day> updateDay(@PathVariable("id") long id, @RequestBody Day day) {

        Day dayToUpdate = dayService.findById(id);


        if (dayToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        dayToUpdate.setMeals(day.getMeals());

        dayService.update(dayToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
