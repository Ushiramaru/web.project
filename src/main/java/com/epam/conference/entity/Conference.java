package com.epam.conference.entity;

import java.time.LocalDateTime;

public class Conference implements Identifiable {

    public final static String TABLE = "conference";
    public final static String ID = "id";
    public final static String NAME = "name";
    public final static String START_DATE = "start_date";
    public final static String END_DATE = "end_date";
    public final static String IS_RELEVANT = "is_relevant";

    private final Long id;
    private final String name;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final boolean isRelevant;

    public Conference(Long id, String name, LocalDateTime startDate, LocalDateTime endDate, boolean isRelevant) {
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public boolean isRelevant() {
        return isRelevant;
    }

}