package org.oka.springcore.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.dao.TicketDAO;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.EventImpl;
import org.oka.springcore.model.Ticket;
import org.oka.springcore.model.TicketImpl;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springcore.model.Ticket.Category.BAR;

@ExtendWith(MockitoExtension.class)
public class TicketService_getBookedTicketsByEvent_Test {
    @InjectMocks
    TicketService ticketService;
    @Mock
    TicketDAO ticketDAO;

    @Test
    public void shouldCallDAO() {
        // Given
        Event event = EventImpl.builder().title("title").date(LocalDate.now()).build();

        // When
        ticketService.getBookedTickets(event, 55, 99);

        // Then
        verify(ticketDAO).getBookedTickets(event, 55, 99);
    }

    @Test
    public void shouldReturnTickets() {
        // Given
        Event event = EventImpl.builder().title("title").date(LocalDate.now()).build();
        Ticket ticket = TicketImpl.builder().userId(1).eventId(44).place(5).category(BAR).build();

        when(ticketDAO.getBookedTickets(event, 44, 22)).thenReturn(List.of(ticket));
        // When
        List<Ticket> actual = ticketService.getBookedTickets(event, 44, 22);

        // Then
        assertThat(actual).containsExactly(ticket);
    }
}
