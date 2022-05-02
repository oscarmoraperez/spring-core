package org.oka.springcore.model;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by maksym_govorischev.
 */
public interface Event {
    /**
     * Event id. UNIQUE.
     *
     * @return Event Id
     */
    long getId();

    void setId(long id);

    String getTitle();

    void setTitle(String title);

    LocalDate getDate();

    void setDate(LocalDate date);

    String toString();
}
