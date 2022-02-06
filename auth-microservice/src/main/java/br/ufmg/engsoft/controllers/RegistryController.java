package br.ufmg.engsoft.controllers;
import br.ufmg.engsoft.models.User;
import br.ufmg.engsoft.models.UserType;
import br.ufmg.engsoft.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class RegistryController {
    private AuthService authService;

    public RegistryController() {
        this.authService = new AuthService();
    }
    @PostMapping("/register")
    public User login(String username, String password, UserType userType) {
        return authService.authenticate(username, password);
    }
}