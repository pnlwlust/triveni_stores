package com.proha.TriveniStores.controller;

import com.proha.TriveniStores.domain.RegistrationToken;
import com.proha.TriveniStores.domain.User;
import com.proha.TriveniStores.repository.RegistrationTokenRepository;
import com.proha.TriveniStores.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private UserManagementService userManagementService;

    @Autowired
    RegistrationTokenRepository registrationTokenRepository;

    @GetMapping("/sign-in")
    String signIn() {

        return "sign-in";
    }

    @GetMapping("/sign-up")
    String signUp() {

        return "sign-up";
    }

    @PostMapping("/sign-up")
    String signUp(User user) {

        userManagementService.saveUser(user);

        return "redirect:/sign-in";
    }

    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<RegistrationToken> registeredToken = Optional.ofNullable(registrationTokenRepository.findByToken(token));
        registeredToken.ifPresent(userManagementService::confirmUser);

        return "/sign-in";
    }
}
