package com.alphabackend.controller;

import com.alpha.generated.api.AuthentificationApi;
import com.alpha.generated.model.AuthRequest;
import com.alpha.generated.model.AuthResponse;
import com.alphabackend.service.AuthentificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class AuthenticationController implements AuthentificationApi{

    private final AuthentificationService authentificationService;

    @Override
    public ResponseEntity<AuthResponse> _createAuthenticationTokenAndLogin(AuthRequest authRequest) {
        AuthResponse authResponseResult = authentificationService.createAuthenticationTokenAndLogin(authRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(authResponseResult.getToken())
                .toUri();

        return ResponseEntity.created(location).body(authResponseResult);
    }
}
