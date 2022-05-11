package org.oka.springcore.db;

import org.junit.jupiter.api.Test;
import org.oka.springcore.model.Ticket;
import org.oka.springcore.model.TicketImpl;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.oka.springcore.model.Ticket.Category.BAR;

public class TicketDB_addTicket_Test {
    TicketDB ticketDB = new TicketDB();

    @Test
    public void shouldAddTicket() {
        // Given
        TicketImpl ticket = TicketImpl.builder().userId(1).eventId(43).place(55).category(BAR).build();

        // When
        ticketDB.addTicket(ticket);

        // Then
        assertThat(ticketDB.getTickets()).contains(ticket);
    }

    @Test
    public void shouldReturnTicketWithId() {
        // Given
        Ticket ticket = TicketImpl.builder().userId(1).eventId(3).category(BAR).build();

        // When
        Ticket persisted = ticketDB.addTicket(ticket);

        // Then
        assertThat(persisted).isNotNull();
        assertThat(persisted.getId()).isGreaterThan(1);
    }
}
