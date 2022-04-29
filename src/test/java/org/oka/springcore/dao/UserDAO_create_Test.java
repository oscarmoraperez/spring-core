package org.oka.springcore.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.db.UserDB;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDAO_create_Test {
    @InjectMocks
    UserDAO userDAO;
    @Mock
    UserDB userDB;

    @Test
    void shouldCreateUser() {
        // Given
        User user = new UserImpl(1, "John", "johndoe@doe.com");
        User user2 = new UserImpl(2, "Jose", "jose@doe.com");
        User user3 = new UserImpl(3, "Doe", "doe@doe.com");
        List<User> usersMocked = new ArrayList<>();
        usersMocked.add(user);
        usersMocked.add(user2);

        when(userDB.getUsers()).thenReturn(usersMocked);
        // When
        User actual = userDAO.create(user3);

        // Then
        assertThat(actual).isEqualTo(new UserImpl(2, "Doe", "doe@doe.com"));
    }
}
