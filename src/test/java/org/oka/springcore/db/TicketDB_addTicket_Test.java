package org.oka.springcore.db;

import org.junit.jupiter.api.Test;
import org.oka.springcore.model.TicketImpl;

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
}
