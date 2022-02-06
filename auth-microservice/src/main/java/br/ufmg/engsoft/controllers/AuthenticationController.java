package br.ufmg.engsoft.controllers;
import br.ufmg.engsoft.models.User;
import br.ufmg.engsoft.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
class AuthenticationController {
    private AuthService authService;

    public AuthenticationController() {
        this.authService = new AuthService();
    }

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody Map<String, String> input) {
        return authService.authenticate(input.get("username"), input.get("password"));
    }

    @PostMapping("/is-teacher")
    public HashMap<String, Object> isTeacher(@RequestBody Map<String, String> input) {
        return authService.isTeacher(input.get("userId"));
    }
}