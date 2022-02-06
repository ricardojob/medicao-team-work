package br.ufmg.engsoft.controllers;
import br.ufmg.engsoft.models.User;
import br.ufmg.engsoft.models.UserType;
import br.ufmg.engsoft.services.AuthService;
import br.ufmg.engsoft.services.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
class RegistryController {
    private RegistryService registryService;

    public RegistryController() {
        this.registryService = new RegistryService();
    }

    @PostMapping("/register")
    public HashMap<String, Object> login(@RequestBody Map<String, String> input) {
        return registryService.register(input.get("username"), input.get("password"), UserType.valueOf(input.get("userType")));
    }
}