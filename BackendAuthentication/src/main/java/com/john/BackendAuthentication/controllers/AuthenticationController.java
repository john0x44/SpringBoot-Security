package com.john.BackendAuthentication.controllers;

import com.john.BackendAuthentication.models.ApplicationUser;
import com.john.BackendAuthentication.models.LoginResponseDTO;
import com.john.BackendAuthentication.models.RegistrationDTO;
import com.john.BackendAuthentication.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // allow to map endpoints to servlets in backend
@RequestMapping("/auth") // Map specific endpoint
@CrossOrigin("*")

public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){ // We are expecting data from this push request
        return authenticationService.registerUser(body.getUsername(),body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(),body.getPassword());
    }
}
