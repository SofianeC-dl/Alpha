package com.alphabackend.service;

import com.alphabackend.UtilsTest.Utils;
import com.alphabackend.model.entity.UserEntity;
import com.alphabackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void testLoadUserByUsernameSuccess() {
        // Data
        String usernameTest = "Test User";

        UserEntity userEntityTest = Utils.getUserEntityAdminTest();

        UserDetails userDetailsCompareTest = Utils.getUserDetailsAdminTest();

        // When
        when(this.userRepository.findByUsername(usernameTest)).thenReturn(Optional.of(userEntityTest));

        // Test
        UserDetails userDetailsResultTest = this.userService.loadUserByUsername(usernameTest);

        // Result
        assertEquals(userDetailsCompareTest, userDetailsResultTest);
    }

    @Test
    void testLoadUserByUsernameFailure() {
        // Data
        String usernameTest = "Test User";

        // When
        when(this.userRepository.findByUsername(usernameTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            this.userService.loadUserByUsername(usernameTest);
        });

        // Result
        assertEquals("User not found: Test User", exception.getMessage());
    }
}
