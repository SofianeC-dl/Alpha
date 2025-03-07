package com.alphabackend.service;

import com.alpha.generated.model.AuthRequest;
import com.alpha.generated.model.AuthResponse;
import com.alphabackend.model.entity.ParamsError;
import com.alphabackend.model.enum_model.ErrorTextEnum;
import com.alphabackend.model.enum_model.NameObject;
import com.alphabackend.model.enum_model.TypeRequestHttpEnum;
import com.alphabackend.utils.JWTUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Builder
@Service
@Slf4j
public class AuthentificationService {

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    private UserService userDetailsService;

    public AuthResponse createAuthenticationTokenAndLogin(AuthRequest authRequest){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        log.warn("PASSWORD : {}", bCryptPasswordEncoder.encode(authRequest.getPassword()));

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch(BadCredentialsException e) {
            throw new com.alphabackend.exception.BadCredentialsException(
                    ParamsError.builder()
                            .errorText(ErrorTextEnum.BAD_CREDENTIALS)
                            .labelObject(NameObject.AUTHENTICATION_MAJ)
                            .typeRequestHttp(TypeRequestHttpEnum.POST_REQUEST)
                            .build());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwt);

        return authResponse;
    }

}
