package org.oka.springcore.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.dao.UserDAO;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserService_delete_Test {
    @InjectMocks
    UserService userService;
    @Mock
    UserDAO userDAO;

    @Test
    void shouldCallUserDAO() {
        // Given
        User user = new UserImpl(1, "name", "name@domain.com");

        // When
        userService.deleteUser(1);

        // Then
        verify(userDAO).delete(1);
    }
}
