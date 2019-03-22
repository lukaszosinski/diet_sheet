package com.dietsheet.DAO;

import com.dietsheet.model.Week;
import org.hibernate.Session;
import org.springframework.stereotype.Component;


@Component("weekDAO")
public class WeekDAO extends AbstractDAO<Week> {
    public WeekDAO() {
        setClazz(Week.class);
    }

    public boolean isExist(Week week) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Week where startDayDate=:startDayDate")
                    .setParameter("startDayDate", week.getStartDayDate())
                    .uniqueResult() != null;

        }
    }
}