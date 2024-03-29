package org.oka.springcore.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.dao.EventDAO;
import org.oka.springcore.dao.UserDAO;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.EventImpl;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventService_deleteEvent_Test {
    @InjectMocks
    EventService eventService;
    @Mock
    EventDAO eventDAO;

    @Test
    void shouldCallEventDAO() {
        // Given

        // When
        eventService.deleteEvent(1);

        // Then
        verify(eventDAO).delete(1);
    }

    @Test
    void shouldReturnEventDAOResult() {
        // Given

        when(eventDAO.delete(1)).thenReturn(true);
        // When
        boolean result = eventService.deleteEvent(1);

        // Then
        assertThat(result).isTrue();
    }
}
