package com.dietsheet.DAO;

import com.dietsheet.model.Day;
import org.springframework.stereotype.Component;

@Component("dayDAO")
public class DayDAO extends AbstractDAO<Day> {
    public DayDAO() {
        setClazz(Day.class);
    }
}