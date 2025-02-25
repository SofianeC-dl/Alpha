package com.alphabackend.controller;

import com.alpha.generated.api.AuthentificationApi;
import com.alpha.generated.model.AuthRequest;
import com.alpha.generated.model.AuthResponse;
import com.alphabackend.service.AuthentificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController implements AuthentificationApi{

    private final AuthentificationService authentificationService;

    @Override
    public ResponseEntity<AuthResponse> _createAuthenticationTokenAndLogin(AuthRequest authRequest){
        return ResponseEntity.ok(authentificationService.createAuthenticationTokenAndLogin(authRequest));
    }
}
