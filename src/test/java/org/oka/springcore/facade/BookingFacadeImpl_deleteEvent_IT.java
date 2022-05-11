package org.oka.springcore.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oka.springcore.db.EventDB;
import org.oka.springcore.db.UserDB;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.EventImpl;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-configuration.xml")
public class BookingFacadeImpl_deleteEvent_IT {
    @Autowired
    BookingFacade bookingFacade;
    @Autowired
    EventDB eventDB;

    @Test
    public void shouldDeleteEvent() {
        // Given
        Event event = EventImpl.builder().title("title").date(now()).build();
        Event persisted = bookingFacade.createEvent(event);
        assertThat(eventDB.getEvents()).contains(event);

        // When
        bookingFacade.deleteEvent(persisted.getId());

        // Then
        assertThat(eventDB.getEvents()).doesNotContain(persisted);
    }
}
