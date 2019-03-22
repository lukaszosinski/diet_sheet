package com.dietsheet.service;

import com.dietsheet.DAO.WeekDAO;
import com.dietsheet.model.Day;
import com.dietsheet.model.Week;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service("weekService")
public class WeekServiceImpl implements Service<Week> {

    @Autowired
    private WeekDAO weekDAO;

    @Autowired DayServiceImpl dayService;

    @Override
    public Week findById(long id) {
        return weekDAO.get(id);
    }

    @Override
    public Week findByName(String name) {
        return null;
    }

    @Override
    public void save(Week week) {
        if(!isExist(week)) {
            weekDAO.save(week);
            week.setDays(dayService.createDaysForWeek(week));
            weekDAO.update(week);
        }
    }

    @Override
    public void update(Week week) {
        weekDAO.update(week);
    }

    @Override
    public void deleteById(long id) {
        weekDAO.delete(id);
    }

    @Override
    public List<Week> findAll() {
        return weekDAO.getAll();
    }

    @Override
    public void deleteAll() {
        weekDAO.deleteAll();
    }

    @Override
    public boolean isExist(Week week) {
        return weekDAO.isExist(week);
    }

}
