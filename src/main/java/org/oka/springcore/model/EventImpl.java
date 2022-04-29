package org.oka.springcore.model;

import lombok.Data;

import java.util.Date;

@Data
public class EventImpl implements Event {
    private long id;
    private String title;
    private Date date;
}
