package br.ufmg.engsoft.controllers;
import br.ufmg.engsoft.models.User;
import br.ufmg.engsoft.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class AuthenticationController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public User login(String username, String password) {
        return authService.authenticate(username, password);
    }

    @PostMapping("/is-teacher")
    public Boolean isTeacher(Integer userId) {
        return authService.isTeacher(userId);
    }
}