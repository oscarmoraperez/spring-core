package org.oka.springcore.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.oka.springcore.dao.EventDAO;
import org.oka.springcore.model.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Setter
@Slf4j
public class EventService {
    @Autowired
    private EventDAO eventDAO;

    public Event getEventById(final long eventId) {
        return eventDAO.getById(eventId).orElseThrow(() -> new RuntimeException("Event not found (byId: " + eventId + ")"));
    }

    public List<Event> getEventsByTitle(final String title, final int pageSize, final int pageNum) {
        return eventDAO.getByTitle(title, pageSize, pageNum);
    }

    public List<Event> getEventsForDay(final LocalDate day, final int pageSize, final int pageNum) {
        return eventDAO.getByDate(day, pageSize, pageNum);
    }

    public Event createEvent(final Event event) {
        log.info("Creating event: " + event);
        return eventDAO.create(event);
    }

    public Event updateEvent(final Event event) {
        return eventDAO.update(event);
    }

    public boolean deleteEvent(final long eventId) {
        return eventDAO.delete(eventId);
    }
}
