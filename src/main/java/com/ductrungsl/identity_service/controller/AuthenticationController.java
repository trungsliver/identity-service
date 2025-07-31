package com.ductrungsl.identity_service.controller;

import com.ductrungsl.identity_service.dto.request.ApiResponse;
import com.ductrungsl.identity_service.dto.request.AuthenticationRequest;
import com.ductrungsl.identity_service.dto.request.IntrospectRequest;
import com.ductrungsl.identity_service.dto.response.AuthenticationResponse;
import com.ductrungsl.identity_service.dto.response.IntrospectResponse;
import com.ductrungsl.identity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    private final RestClient.Builder builder;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result =authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
//                .result(AuthenticationResponse.builder()
//                        .authenticated(result)
//                        .build())
                .result(result)
                .build();

    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result =authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();

    }
}
