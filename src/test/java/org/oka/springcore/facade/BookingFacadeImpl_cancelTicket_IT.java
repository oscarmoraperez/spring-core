package org.oka.springcore.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oka.springcore.db.TicketDB;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.EventImpl;
import org.oka.springcore.model.Ticket;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.oka.springcore.model.Ticket.Category.BAR;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-configuration.xml")
public class BookingFacadeImpl_cancelTicket_IT {
    @Autowired
    BookingFacade bookingFacade;
    @Autowired
    TicketDB ticketDB;

    @Test
    public void shouldCancelBookedTicket() {
        // Given
        User user = UserImpl.builder().name("Peter").email("peter@domain.com").build();
        User persistedUser = bookingFacade.createUser(user);
        Event event = EventImpl.builder().date(LocalDate.now()).title("King Lion").build();
        Event persistedEvent = bookingFacade.createEvent(event);
        Ticket persistedTicket = bookingFacade.bookTicket(persistedUser.getId(), persistedEvent.getId(), 55, BAR);

        // When
        assertThat(ticketDB.getTickets()).contains(persistedTicket);
        bookingFacade.cancelTicket(persistedTicket.getId());

        // Then
        assertThat(ticketDB.getTickets()).doesNotContain(persistedTicket);
    }
}
