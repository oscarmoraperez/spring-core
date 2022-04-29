package org.oka.springcore.model;

import lombok.Data;

@Data
public class TicketImpl implements Ticket {
    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;
}
