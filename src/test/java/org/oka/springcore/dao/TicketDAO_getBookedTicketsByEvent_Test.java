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
import static org.mockito.Mockito.when;
import static org.oka.springcore.model.Ticket.Category.*;

@ExtendWith(MockitoExtension.class)
public class TicketDAO_getBookedTicketsByEvent_Test {
    @InjectMocks
    TicketDAO ticketDAO;
    @Mock
    TicketDB ticketDB;

    private final List<Ticket> TICKETS = List.of(
            new TicketImpl(1, 1, 2, BAR, 55),
            new TicketImpl(2, 1, 4, PREMIUM, 33),
            new TicketImpl(3, 1, 6, STANDARD, 44),
            new TicketImpl(4, 1, 8, STANDARD, 77),
            new TicketImpl(5, 2, 10, BAR, 88),
            new TicketImpl(6, 2, 12, PREMIUM, 99),
            new TicketImpl(7, 2, 14, STANDARD, 22));

    @Test
    void shouldReturnZeroTickets() {
        // Given
        Event event = EventImpl.builder().id(5).title("A title").date(LocalDate.now()).build();

        when(ticketDB.getTickets()).thenReturn(TICKETS);
        // When
        List<Ticket> actual = ticketDAO.getBookedTickets(event, 1, 1);

        // Then
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldReturnPageOneWithTwoTickets() {
        // Given
        Event event = EventImpl.builder().id(1).title("A title").date(LocalDate.now()).build();

        when(ticketDB.getTickets()).thenReturn(TICKETS);
        // When
        List<Ticket> actual = ticketDAO.getBookedTickets(event, 2, 0);

        // Then
        assertThat(actual).containsExactly(
                new TicketImpl(1, 1, 2, BAR, 55),
                new TicketImpl(2, 1, 4, PREMIUM, 33));
    }

    @Test
    void shouldReturnPageTwoWithTwoTickets() {
        // Given
        Event event = EventImpl.builder().id(2).title("A title").date(LocalDate.now()).build();

        when(ticketDB.getTickets()).thenReturn(TICKETS);
        // When
        List<Ticket> actual = ticketDAO.getBookedTickets(event, 2, 0);

        // Then
        assertThat(actual).containsExactly(
                new TicketImpl(5, 2, 10, BAR, 88),
                new TicketImpl(6, 2, 12, PREMIUM, 99));
    }

    @Test
    void shouldReturnPageOneWithOneTicket() {
        // Given
        Event event = EventImpl.builder().id(2).title("A title").date(LocalDate.now()).build();

        when(ticketDB.getTickets()).thenReturn(TICKETS);
        // When
        List<Ticket> actual = ticketDAO.getBookedTickets(event, 2, 1);

        // Then
        assertThat(actual).containsExactly(
                new TicketImpl(7, 2, 14, STANDARD, 22)
        );
    }
}
