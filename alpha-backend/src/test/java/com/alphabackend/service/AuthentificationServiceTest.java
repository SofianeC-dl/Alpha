package com.alphabackend.service;

import com.alpha.generated.model.AuthRequest;
import com.alpha.generated.model.AuthResponse;
import com.alphabackend.UtilsTest.Utils;
import com.alphabackend.utils.JWTUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthentificationServiceTest {

    @InjectMocks
    private AuthentificationService authentificationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userDetailsService;

    @Mock
    private JWTUtil jwtUtil;

    @Test
    void testCreateAuthenticationTokenAndLoginSuccess() {
        // Data
        AuthRequest authRequestEntryTest = Utils.getAuthRequestTest();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationTokenEntryTest = Utils.getUsernamePasswordAuthenticationTokenTest();

        UserDetails userDetailsTest = Utils.getUserDetailsAdminTest();

        Authentication authenticationResultTest = Utils.getUsernamePasswordAuthenticationTokenTest();

        String jwtResultTest = "Test JWT";

        AuthResponse authResponseCompareTest = Utils.getAuthResponseTest();
        // When
        when(this.userDetailsService.loadUserByUsername(authRequestEntryTest.getUsername())).thenReturn(userDetailsTest);
        when(this.authenticationManager.authenticate(usernamePasswordAuthenticationTokenEntryTest)).thenReturn(authenticationResultTest);
        when(this.jwtUtil.generateToken(authRequestEntryTest.getUsername())).thenReturn(jwtResultTest);

        // Test
        AuthResponse authResponseResultTest = this.authentificationService.createAuthenticationTokenAndLogin(authRequestEntryTest);

        // Result
        assertEquals(authResponseCompareTest, authResponseResultTest);
     }

    @Test
    void testCreateAuthenticationTokenAndLoginFailure() {
        // Data
        AuthRequest authRequestEntryTest = Utils.getAuthRequestTest();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationTokenEntryTest = Utils.getUsernamePasswordAuthenticationTokenTest();
       // When
        when(this.authenticationManager.authenticate(usernamePasswordAuthenticationTokenEntryTest)).thenThrow(org.springframework.security.authentication.BadCredentialsException.class);

        // Test
        Exception exception = assertThrows(com.alphabackend.exception.BadCredentialsException.class, () -> {
            this.authentificationService.createAuthenticationTokenAndLogin(authRequestEntryTest);
        });

        // Result
        assertEquals("Bad credentials|Authentication|POST", exception.getMessage());
    }

}
