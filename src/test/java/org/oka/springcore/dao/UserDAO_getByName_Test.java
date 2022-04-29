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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDAO_getByName_Test {
    @InjectMocks
    UserDAO userDAO;
    @Mock
    UserDB userDB;

    private final List<User> USERS = List.of(
            new UserImpl(1, "John", "johndoe@doe.com"),
            new UserImpl(2, "John", "johndoe2@doe.com"),
            new UserImpl(3, "John", "johndoe3@doe.com"),
            new UserImpl(4, "John", "johndoe4@doe.com"),
            new UserImpl(5, "Jose", "jose@domain.com"),
            new UserImpl(6, "Jose", "jose2@domain.com"),
            new UserImpl(7, "Jose", "jose3@domain.com"));

    @Test
    void shouldReturnZeroUsers() {
        // Given

        when(userDB.getUsers()).thenReturn(USERS);
        // When
        List<User> actual = userDAO.getByName("non-existing", 5, 12);

        // Then
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldReturnPageOneWithTwoUsers() {
        // Given

        when(userDB.getUsers()).thenReturn(USERS);
        // When
        List<User> actual = userDAO.getByName("John", 2, 0);

        // Then
        assertThat(actual).containsExactly(
                new UserImpl(1, "John", "johndoe@doe.com"),
                new UserImpl(2, "John", "johndoe2@doe.com"));
    }

    @Test
    void shouldReturnPageTwoWithTwoUsers() {
        // Given

        when(userDB.getUsers()).thenReturn(USERS);
        // When
        List<User> actual = userDAO.getByName("John", 2, 1);

        // Then
        assertThat(actual).containsExactly(
                new UserImpl(3, "John", "johndoe3@doe.com"),
                new UserImpl(4, "John", "johndoe4@doe.com"));
    }

    @Test
    void shouldReturnPageOneWithOneUsers() {
        // Given

        when(userDB.getUsers()).thenReturn(USERS);
        // When
        List<User> actual = userDAO.getByName("Jose", 2, 1);

        // Then
        assertThat(actual).containsExactly(new UserImpl(7, "Jose", "jose3@domain.com"));
    }
}
