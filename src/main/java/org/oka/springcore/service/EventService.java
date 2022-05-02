package org.oka.springcore.service;

import lombok.Setter;
import org.oka.springcore.dao.EventDAO;
import org.oka.springcore.model.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Setter
public class EventService {
    @Autowired
    private EventDAO eventDAO;

    public Event getEventById(final long eventId) {
        return eventDAO.getById(eventId).orElseThrow(() -> new RuntimeException("Event not found (byId: " + eventId + ")"));
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return List.of();
    //    return eventDAO.getByTitle();
    }


}
