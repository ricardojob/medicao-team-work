package br.ufmg.engsoft.services;

import br.ufmg.engsoft.models.User;
import br.ufmg.engsoft.models.UserType;
import br.ufmg.engsoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistryService {
    @Autowired
    UserRepository userRepository;
    public User register(String username, String password, UserType userType) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setType(userType.toString());

        try {
            userRepository.add(newUser);
            return newUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}