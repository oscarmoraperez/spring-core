package org.oka.springcore.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.EventImpl;
import org.oka.springcore.model.Ticket;
import org.oka.springcore.model.TicketImpl;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;
import org.oka.springcore.service.TicketService;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springcore.model.Ticket.Category.BAR;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_getBookedTicketsByEvent_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacade;
    @Mock
    TicketService ticketService;

    @Test
    public void shouldCallTicketService() {
        // Given
        Event event = EventImpl.builder().title("title").date(now()).build();

        // When
        bookingFacade.getBookedTickets(event, 11, 22);

        // Then
        verify(ticketService).getBookedTickets(event, 11, 22);
    }

    @Test
    public void shouldReturnListOfTicket() {
        // Given
        Event event = EventImpl.builder().title("title").date(now()).build();
        Ticket ticket = TicketImpl.builder().userId(1).eventId(3).place(66).category(BAR).build();

        when(ticketService.getBookedTickets(event, 33, 44)).thenReturn(List.of(ticket));
        // When
        List<Ticket> bookedTickets = bookingFacade.getBookedTickets(event, 33, 44);

        // Then
        assertThat(bookedTickets).contains(ticket);
    }
}
