package org.oka.springcore.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.service.EventService;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_getEventsByTitle_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    EventService eventService;

    @Test
    void shouldCallEventService() {
        // Given
        String title = "a title";
        int pageSize = 34;
        int pageNum = 22;

        // When
        bookingFacadeImpl.getEventsByTitle(title, pageSize, pageNum);

        // Then
        verify(eventService).getEventsByTitle(title, pageSize, pageNum);
    }
}
