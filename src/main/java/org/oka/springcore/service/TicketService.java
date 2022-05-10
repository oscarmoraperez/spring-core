package org.oka.springcore.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.oka.springcore.dao.EventDAO;
import org.oka.springcore.dao.TicketDAO;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.Ticket;
import org.oka.springcore.model.TicketImpl;
import org.oka.springcore.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Setter
@Slf4j
public class TicketService {
    @Autowired
    private TicketDAO ticketDAO;

    public Ticket bookTicket(final long userId, final long eventId, final int place, final Ticket.Category category) {
        TicketImpl ticket = TicketImpl.builder()
                .userId(userId)
                .eventId(eventId)
                .place(place)
                .category(category)
                .build();
        return ticketDAO.bookTicket(ticket);
    }

    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        return ticketDAO.getBookedTickets(user, pageSize, pageNum);
    }

    public List<Ticket> getBookedTickets(final Event event, final int pageSize, final int pageNum) {
        return ticketDAO.getBookedTickets(event, pageSize, pageNum);
    }

    public boolean cancelTicket(final long ticketId) {
        return ticketDAO.cancelTicket(ticketId);
    }
}
