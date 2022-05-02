package org.oka.springcore.dao;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.oka.springcore.db.EventDB;
import org.oka.springcore.model.Event;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Setter
@Component
public class EventDAO {

    private EventDB eventDB;

    public Optional<Event> getById(final long id) {
        return eventDB.getEvents().stream().filter(u -> u.getId() == id).findFirst();
    }
}
