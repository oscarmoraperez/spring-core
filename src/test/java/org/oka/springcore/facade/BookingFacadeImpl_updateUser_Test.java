package org.oka.springcore.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;
import org.oka.springcore.service.UserService;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_updateUser_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    UserService userService;

    @Test
    void shouldCallUserService() {
        // Given
        User user = new UserImpl(1, "name", "email");

        // When
        bookingFacadeImpl.updateUser(user);

        // Then
        verify(userService).updateUser(user);
    }
}
