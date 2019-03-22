package com.dietsheet.controller;



import com.dietsheet.model.Week;
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
public class WeekController {

    @Autowired
    Service<Week> weekService;

    @RequestMapping(value = "/week/", method = RequestMethod.GET)
    public ResponseEntity<List<Week>> getAllWeeks() {
        List<Week> weeks = weekService.findAll();
        if(weeks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(weeks, HttpStatus.OK);
    }

    @RequestMapping(value = "/week/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Week> getWeek(@PathVariable("id") long id) {
        Week week = weekService.findById(id);
        if (week == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(week, HttpStatus.OK);
    }

    @RequestMapping(value = "/week/", method = RequestMethod.POST)
    public ResponseEntity<Void> createWeek(@RequestBody Week week, UriComponentsBuilder ucBuilder) {

        if (weekService.isExist(week)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        weekService.save(week);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/week/{id}").buildAndExpand(week.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    

    @RequestMapping(value = "/week/", method = RequestMethod.DELETE)
    public ResponseEntity<Week> deleteAllWeeks() {
        weekService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/week/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Week> deleteWeek(@PathVariable("id") long id) {
        Week week = weekService.findById(id);
        if (week == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        weekService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
