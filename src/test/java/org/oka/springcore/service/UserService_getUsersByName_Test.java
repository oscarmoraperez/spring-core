package org.oka.springcore.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.dao.UserDAO;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserService_getUsersByName_Test {
    @InjectMocks
    UserService userService;
    @Mock
    UserDAO userDAO;

    @Test
    void shouldCallUserDAO() {
        // Given

        when(userDAO.getByName(anyString(), anyInt(), anyInt()))
                .thenReturn(List.of(new UserImpl(1, "Jose", "jose.canseco@aa.com")));
        // When
        userService.getUsersByName("name", 4, 6);

        // Then
        verify(userDAO).getByName("name", 4, 6);
    }
}
