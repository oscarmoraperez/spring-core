package org.oka.springcore.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.db.UserDB;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDAO_getByEmail_Test {
    @InjectMocks
    UserDAO userDAO;
    @Mock
    UserDB userDB;

    @Test
    void shouldReturnUser() {
        // Given
        User user = new UserImpl(1, "John", "johndoe@doe.com");

        when(userDB.getUsers()).thenReturn(List.of(user));
        // When
        User actual = userDAO.getByEmail("johndoe@doe.com").orElseThrow();

        // Then
        assertThat(actual).isEqualTo(user);
    }

    @Test
    void shouldReturnEmpty() {
        // Given
        User user = new UserImpl(1, "John", "johndoe@doe.com");

        when(userDB.getUsers()).thenReturn(List.of(user));
        // When
        Optional<User> actual = userDAO.getByEmail("notexistentmail");

        // Then
        assertThat(actual).isEmpty();
    }
}
