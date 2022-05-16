package org.oka.springcore.dao;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.oka.springcore.db.TicketDB;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.Ticket;
import org.oka.springcore.model.TicketImpl;
import org.oka.springcore.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Setter
public class TicketDAO {

    private TicketDB ticketDB;
    private Pageable<Ticket> pageable;

    public Ticket bookTicket(final TicketImpl ticket) {
        return ticketDB.addTicket(ticket);
    }

    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        List<Ticket> tickets = ticketDB.getTickets().stream()
                .filter(t -> t.getUserId() == user.getId())
                .collect(toList());

        return pageable.paginate(tickets, pageSize, pageNum);
    }

    public List<Ticket> getBookedTickets(final Event event, int pageSize, int pageNum) {
        List<Ticket> tickets = ticketDB.getTickets().stream()
                .filter(t -> t.getEventId() == event.getId())
                .collect(toList());

        return pageable.paginate(tickets, pageSize, pageNum);
    }

    public boolean cancelTicket(final long ticketId) {
        Optional<Ticket> ticketToDelete = ticketDB.getTickets().stream()
                .filter(t -> t.getId() == ticketId)
                .findFirst();
        if (ticketToDelete.isEmpty()) {
            return false;
        }
        return ticketDB.getTickets().remove(ticketToDelete.get());
    }
}