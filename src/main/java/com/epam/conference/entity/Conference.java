package com.epam.conference.entity;

import java.util.Date;

public class Conference implements Identifiable {

    public static final String TABLE = "conference";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String IS_RELEVANT = "is_relevant";

    private final Long id;
    private final String name;
    private final Date startDate;
    private final Date endDate;
    private final boolean isRelevant;

    public Conference(Long id, String name, Date startDate, Date endDate, boolean isRelevant) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isRelevant = isRelevant;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isRelevant() {
        return isRelevant;
    }

}