package org.oka.springcore.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.service.EventService;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_getEventsForDay_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    EventService eventService;

    @Test
    void shouldCallEventService() {
        // Given
        LocalDate date = LocalDate.parse("2020-05-05");
        int pageSize = 34;
        int pageNum = 22;

        // When
        bookingFacadeImpl.getEventsForDay(date, pageSize, pageNum);

        // Then
        verify(eventService).getEventsForDay(date, pageSize, pageNum);
    }
}
