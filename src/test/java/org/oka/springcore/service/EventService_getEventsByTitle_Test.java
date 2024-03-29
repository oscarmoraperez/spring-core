package org.oka.springcore.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.dao.EventDAO;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.EventImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventService_getEventsByTitle_Test {
    @InjectMocks
    EventService eventService;
    @Mock
    EventDAO eventDAO;

    @Test
    public void shouldCallEventDAO() {
        // Given
        String title = "title";
        int pageNum = 22;
        int pageSize = 66;

        // When
        eventService.getEventsByTitle(title, pageSize, pageNum);

        // Then
        verify(eventDAO).getByTitle(title, pageSize, pageNum);
    }

    @Test
    public void shouldReturnListOfEvents() {
        // Given
        String title = "title";
        int pageNum = 22;
        int pageSize = 66;
        Event event = EventImpl.builder().title("title").build();

        when(eventDAO.getByTitle(title, pageSize, pageNum)).thenReturn(List.of(event));
        // When
        List<Event> eventsByTitle = eventService.getEventsByTitle(title, pageSize, pageNum);

        // Then
        assertThat(eventsByTitle).containsAll(List.of(event));
    }
}
