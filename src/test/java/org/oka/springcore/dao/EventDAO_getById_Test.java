package org.oka.springcore.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.db.EventDB;
import org.oka.springcore.db.UserDB;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.EventImpl;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventDAO_getById_Test {
    @InjectMocks
    EventDAO eventDAO;
    @Mock
    EventDB eventDB;

    @Test
    void shouldReturnEvent() {
        // Given
        Event event = EventImpl.builder().id(2).title("title").date(LocalDate.now()).build();

        when(eventDB.getEvents()).thenReturn(List.of(event));
        // When
        Event actual = eventDAO.getById(2).orElseThrow();

        // Then
        assertThat(actual).isEqualTo(event);
    }

    @Test
    void shouldReturnEmpty() {
        // Given
        long userId = 33;
        Event event = EventImpl.builder().title("title").date(LocalDate.now()).build();

        when(eventDB.getEvents()).thenReturn(List.of(event));
        // When
        Optional<Event> actual = eventDAO.getById(userId);

        // Then
        assertThat(actual).isEmpty();
    }
}
