package org.oka.springcore.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.db.TicketDB;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.EventImpl;
import org.oka.springcore.model.Ticket;
import org.oka.springcore.model.TicketImpl;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springcore.model.Ticket.Category.BAR;

@ExtendWith(MockitoExtension.class)
public class TicketDAO_getBookedTicketsByEvent_Test {
    @InjectMocks
    TicketDAO ticketDAO;
    @Mock
    TicketDB ticketDB;
    @Mock
    Pageable<Ticket> pageable;

    @Test
    void shouldCallTicketDB() {
        // Given
        Event event = new EventImpl(3, "title", LocalDate.now().plusDays(4));

        // When
        ticketDAO.getBookedTickets(event, 1, 1);

        // Then
        verify(ticketDB).getTickets();
    }

    @Test
    void shouldCallPageable() {
        // Given
        Event event = new EventImpl(3, "title", LocalDate.now().plusDays(4));
        Ticket ticket = new TicketImpl(1, 3, 1, BAR, 44);

        when(ticketDB.getTickets()).thenReturn(List.of(ticket));
        // When
        ticketDAO.getBookedTickets(event, 1, 5);

        // Then
        verify(pageable).paginate(List.of(ticket), 1, 5);
    }

    @Test
    void shouldReturnTickets() {
        // Given
        Event event = new EventImpl(3, "title", LocalDate.now().plusDays(4));
        Ticket ticket = new TicketImpl(1, 3, 1, BAR, 44);

        when(ticketDB.getTickets()).thenReturn(List.of(ticket));
        when(pageable.paginate(List.of(ticket), 1, 5)).thenReturn(List.of(ticket));
        // When
        List<Ticket> actual = ticketDAO.getBookedTickets(event, 1, 5);

        // Then
        assertThat(actual).containsExactly(ticket);
    }
}
