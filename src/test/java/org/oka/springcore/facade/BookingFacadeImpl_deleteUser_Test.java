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
public class BookingFacadeImpl_deleteUser_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    UserService userService;

    @Test
    void shouldCallUserService() {
        // Given
        long userId = 1;

        // When
        bookingFacadeImpl.deleteUser(userId);

        // Then
        verify(userService).deleteUser(userId);
    }
}
