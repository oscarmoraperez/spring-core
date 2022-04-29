package org.oka.springcore.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.dao.UserDAO;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserService_create_Test {
    @InjectMocks
    UserService userService;
    @Mock
    UserDAO userDAO;

    @Test
    void shouldCallUserDAO() {
        // Given
        User user = new UserImpl(1, "name", "name@domain.com");

        // When
        userService.createUser(user);

        // Then
        verify(userDAO).create(user);
    }
}
