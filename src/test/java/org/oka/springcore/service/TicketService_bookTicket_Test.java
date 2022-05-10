package org.oka.springcore.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.dao.TicketDAO;
import org.oka.springcore.model.Ticket;
import org.oka.springcore.model.TicketImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.oka.springcore.model.Ticket.Category.PREMIUM;

@ExtendWith(MockitoExtension.class)
public class TicketService_bookTicket_Test {
    @InjectMocks
    TicketService ticketService;
    @Mock
    TicketDAO ticketDAO;

    @Test
    public void shouldCallDAO() {
        // Given

        // When
        ticketService.bookTicket(1, 2, 4, PREMIUM);

        // Then
        verify(ticketDAO).bookTicket(TicketImpl.builder().userId(1).eventId(2).place(4).category(PREMIUM).build());
    }

    @Test
    public void shouldReturnTicket() {
        // Given
        Ticket persisted = TicketImpl.builder().userId(1).eventId(2).place(4).category(PREMIUM).build();

        when(ticketDAO.bookTicket(any())).thenReturn(persisted);
        // When
        Ticket actual = ticketService.bookTicket(1, 2, 4, PREMIUM);

        // Then
        assertThat(actual).isEqualTo(persisted);
    }
}
