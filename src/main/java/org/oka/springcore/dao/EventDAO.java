package org.oka.springcore.dao;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.oka.springcore.db.EventDB;
import org.oka.springcore.model.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Setter
@Component
public class EventDAO {

    private EventDB eventDB;

    public Optional<Event> getById(final long id) {
        return eventDB.getEvents().stream().filter(u -> u.getId() == id).findFirst();
    }

    public List<Event> getByTitle(final String title, final int pageSize, final int pageNum) {
        int init = pageNum * pageSize;
        int end = pageNum * pageSize + pageSize;
        List<Event> events = eventDB.getEvents().stream().filter(u -> u.getTitle().equals(title)).collect(toList());

        init = init > events.size() ? 0 : init;
        end = Math.min(end, events.size());

        return events.subList(init, end);
    }

    public List<Event> getByDate(LocalDate day, int pageSize, int pageNum) {
        int init = pageNum * pageSize;
        int end = pageNum * pageSize + pageSize;
        List<Event> events = eventDB.getEvents().stream().filter(u -> u.getDate().equals(day)).collect(toList());

        init = init > events.size() ? 0 : init;
        end = Math.min(end, events.size());

        return events.subList(init, end);
    }

    public Event create(Event event) {
        return eventDB.addEvent(event);
    }

    public Event update(Event event) {
        Event eventToUpdate = eventDB.getEvents().stream().filter(e -> e.getId() == event.getId()).findFirst().orElseThrow();
        eventToUpdate.setTitle(event.getTitle());
        eventToUpdate.setDate(event.getDate());

        return event;
    }

    public boolean delete(final long eventId) {
        Optional<Event> eventToDelete = eventDB.getEvents().stream().filter(e -> e.getId() == eventId).findFirst();
        if (eventToDelete.isEmpty()) {
            return false;
        }
        return eventDB.getEvents().remove(eventToDelete.get());
    }
}
