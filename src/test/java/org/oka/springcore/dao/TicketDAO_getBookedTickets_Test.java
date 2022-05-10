package org.oka.springcore.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.db.TicketDB;
import org.oka.springcore.model.Ticket;
import org.oka.springcore.model.TicketImpl;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.oka.springcore.model.Ticket.Category.*;

@ExtendWith(MockitoExtension.class)
public class TicketDAO_getBookedTickets_Test {
    @InjectMocks
    TicketDAO ticketDAO;
    @Mock
    TicketDB ticketDB;

    private final List<Ticket> TICKETS = List.of(
            new TicketImpl(1, 1, 1, BAR, 55),
            new TicketImpl(2, 2, 1, PREMIUM, 33),
            new TicketImpl(3, 3, 1, STANDARD, 44),
            new TicketImpl(4, 4, 1, STANDARD, 77),
            new TicketImpl(5, 5, 2, BAR, 88),
            new TicketImpl(6, 6, 2, PREMIUM, 99),
            new TicketImpl(7, 7, 2, STANDARD, 22));

    @Test
    void shouldReturnZeroTickets() {
        // Given
        User user = UserImpl.builder().id(8).name("Jose").build();

        when(ticketDB.getTickets()).thenReturn(TICKETS);
        // When
        List<Ticket> actual = ticketDAO.getBookedTickets(user, 1, 1);

        // Then
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldReturnPageOneWithTwoTickets() {
        // Given
        User user = UserImpl.builder().id(1).name("Jose").build();

        when(ticketDB.getTickets()).thenReturn(TICKETS);
        // When
        List<Ticket> actual = ticketDAO.getBookedTickets(user, 2, 0);

        // Then
        assertThat(actual).containsExactly(
                new TicketImpl(1, 1, 1, BAR, 55),
                new TicketImpl(2, 2, 1, PREMIUM, 33));
    }

    @Test
    void shouldReturnPageTwoWithTwoUsers() {
        // Given
        User user = UserImpl.builder().id(1).name("Jose").build();

        when(ticketDB.getTickets()).thenReturn(TICKETS);
        // When
        List<Ticket> actual = ticketDAO.getBookedTickets(user, 2, 1);

        // Then
        assertThat(actual).containsExactly(
                new TicketImpl(3, 3, 1, STANDARD, 44),
                new TicketImpl(4, 4, 1, STANDARD, 77));
    }

    @Test
    void shouldReturnPageOneWithOneTicket() {
        // Given
        User user = UserImpl.builder().id(2).name("Jose").build();

        when(ticketDB.getTickets()).thenReturn(TICKETS);
        // When
        List<Ticket> actual = ticketDAO.getBookedTickets(user, 2, 1);

        // Then
        assertThat(actual).containsExactly(
                new TicketImpl(7, 7, 2, STANDARD, 22)
        );
    }
}
