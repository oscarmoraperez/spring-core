package org.oka.springcore.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oka.springcore.model.UserImpl;
import org.oka.springcore.service.UserService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeImpl_getUserByEmail_Test {
    @InjectMocks
    BookingFacadeImpl bookingFacadeImpl;
    @Mock
    UserService userService;

    @Test
    void shouldCallUserService() {
        // Given
        String email = "email";

        when(userService.getUserByEmail(anyString())).thenReturn(new UserImpl(1, "Jose", "jose.canseco@aa.com"));
        // When
        bookingFacadeImpl.getUserByEmail(email);

        // Then
        verify(userService).getUserByEmail(email);
    }
}
