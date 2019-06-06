package com.dietsheet.service;

import com.dietsheet.DAO.DayDAO;
import com.dietsheet.model.Day;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service("dayService")
public class DayServiceImpl implements Service<Day> {

    @Autowired
    private DayDAO dayDAO;

    @Override
    public Day findById(long id) {
        Day day = dayDAO.get(id);
        Hibernate.initialize(day.getMeals());
        return day;
    }

    @Override
    public Day findByName(String name) {
        return null;
    }

    @Override
    public void save(Day day) {
        dayDAO.save(day);
    }

    @Override
    public void update(Day day) {
        dayDAO.update(day);
    }

    @Override
    public void deleteById(long id) {
        dayDAO.delete(id);
    }

    @Override
    public List<Day> findAll() {
        return dayDAO.getAll();
    }

    @Override
    public void deleteAll() {
        dayDAO.deleteAll();
    }

    @Override
    public boolean isExist(Day day) {
        return dayDAO.get(day.getId()) != null;
    }
}

