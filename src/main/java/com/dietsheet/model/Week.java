package com.dietsheet.model;


import com.dietsheet.serializer.LocalDateDeserializer;
import com.dietsheet.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "week")
public class Week {

    @Id
    @Column(name = "week_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name =  "start_day_date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDayDate;

    @OneToMany(
            mappedBy = "week",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Day> days = new ArrayList<>();

    public Week() {
    }

    public Week(LocalDate startDayDate, List<Day> days) {
        this.startDayDate = startDayDate;
        this.days = days;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDayDate() {
        return startDayDate;
    }

    public void setStartDayDate(LocalDate startDayDate) {
        this.startDayDate = startDayDate;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
