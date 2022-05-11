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

@RequiredArgsConstructor
@Setter
@Component
public class TicketDAO {
    private TicketDB ticketDB;

    public Ticket bookTicket(final TicketImpl ticket) {
        return ticketDB.addTicket(ticket);
    }

    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        int init = pageNum * pageSize;
        int end = pageNum * pageSize + pageSize;
        List<Ticket> tickets = ticketDB.getTickets().stream().filter(t -> t.getUserId() == user.getId()).collect(toList());

        init = init > tickets.size() ? 0 : init;
        end = Math.min(end, tickets.size());

        return tickets.subList(init, end);
    }

    public List<Ticket> getBookedTickets(final Event event, int pageSize, int pageNum) {
        int init = pageNum * pageSize;
        int end = pageNum * pageSize + pageSize;
        List<Ticket> tickets = ticketDB.getTickets().stream().filter(t -> t.getEventId() == event.getId()).collect(toList());

        init = init > tickets.size() ? 0 : init;
        end = Math.min(end, tickets.size());

        return tickets.subList(init, end);
    }

    public boolean cancelTicket(final long ticketId) {
        Optional<Ticket> ticketToDelete = ticketDB.getTickets().stream().filter(t -> t.getId() == ticketId).findFirst();
        if (ticketToDelete.isEmpty()) {
            return false;
        }
        return ticketDB.getTickets().remove(ticketToDelete.get());
    }
}
