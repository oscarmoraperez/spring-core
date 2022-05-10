package org.oka.springcore.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.db.EventDB;
import org.oka.springcore.model.Event;
import org.oka.springcore.model.EventImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventDAO_getByTitle_Test {
    @InjectMocks
    EventDAO eventDAO;
    @Mock
    EventDB eventDB;

    private final List<Event> EVENTS = List.of(
            EventImpl.builder().id(1).title("title_1").build(),
            EventImpl.builder().id(2).title("title_1").build(),
            EventImpl.builder().id(3).title("title_1").build(),
            EventImpl.builder().id(4).title("title_1").build(),
            EventImpl.builder().id(5).title("title_2").build(),
            EventImpl.builder().id(6).title("title_2").build(),
            EventImpl.builder().id(7).title("title_2").build());

    @Test
    void shouldReturnZeroEvents() {
        // Given

        when(eventDB.getEvents()).thenReturn(EVENTS);
        // When
        List<Event> actual = eventDAO.getByTitle("non-existing", 5, 12);

        // Then
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldReturnPageOneWithTwoEvents() {
        // Given

        when(eventDB.getEvents()).thenReturn(EVENTS);
        // When
        List<Event> actual = eventDAO.getByTitle("title_1", 2, 0);

        // Then
        assertThat(actual).containsExactly(
                EventImpl.builder().id(1).title("title_1").build(),
                EventImpl.builder().id(2).title("title_1").build());
    }

    @Test
    void shouldReturnPageTwoWithTwoEvents() {
        // Given

        when(eventDB.getEvents()).thenReturn(EVENTS);
        // When
        List<Event> actual = eventDAO.getByTitle("title_1", 2, 1);

        // Then
        assertThat(actual).containsExactly(
                EventImpl.builder().id(3).title("title_1").build(),
                EventImpl.builder().id(4).title("title_1").build());
    }

    @Test
    void shouldReturnPageOneWithOneEvent() {
        // Given

        when(eventDB.getEvents()).thenReturn(EVENTS);
        // When
        List<Event> actual = eventDAO.getByTitle("title_2", 2, 1);

        // Then
        assertThat(actual).containsExactly(EventImpl.builder().id(7).title("title_2").build());
    }
}
