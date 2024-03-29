package org.oka.springcore.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.EventImpl;
import org.oka.springcore.service.EventService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_getEventById_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    EventService eventService;

    @Test
    void shouldCallEventService() {
        // Given
        long eventId = 13;

        when(eventService.getEventById(anyLong())).thenReturn(new EventImpl(1, "Jose", LocalDate.now()));
        // When
        bookingFacadeImpl.getEventById(eventId);

        // Then
        verify(eventService).getEventById(eventId);
    }

    @Test
    void shouldReturnEvent() {
        // Given
        Event event = new EventImpl(1, "Jose", LocalDate.now());

        when(eventService.getEventById(anyLong())).thenReturn(event);
        // When
        Event actual = bookingFacadeImpl.getEventById(1);

        // Then
        assertThat(actual).isEqualTo(event);
    }
}
