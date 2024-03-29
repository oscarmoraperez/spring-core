package org.oka.springcore.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oka.springcore.db.UserDB;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-configuration.xml")
public class BookingFacadeImpl_createUser_IT {
    @Autowired
    BookingFacade bookingFacade;
    @Autowired
    UserDB userDB;

    @Test
    public void shouldCreateUser() {
        // Given
        User user = UserImpl.builder().name("Peter").email("peter@domain.com").build();

        // When
        User persisted = bookingFacade.createUser(user);

        // Then
        assertThat(userDB.getUsers()).contains(persisted);
    }
}
