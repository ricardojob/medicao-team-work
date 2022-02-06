package br.ufmg.engsoft.services;

import br.ufmg.engsoft.models.User;
import br.ufmg.engsoft.models.UserType;
import br.ufmg.engsoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RegistryService {
    private UserRepository userRepository;

    public RegistryService() {
        this.userRepository = new UserRepository();
    }

    public HashMap<String, Object> register(String username, String password, UserType userType) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setType(userType.toString());

        HashMap<String, Object> response = new HashMap<String, Object>();

        try {
            userRepository.add(newUser);
            response.put("userId", newUser.getId());
            return response;
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            return response;
        }
    }
}