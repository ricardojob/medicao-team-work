package br.ufmg.engsoft.services;

import br.ufmg.engsoft.models.User;
import br.ufmg.engsoft.models.UserType;
import br.ufmg.engsoft.repository.UserRepository;
import org.springframework.security.access.*;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AuthService {
    private UserRepository userRepository;

    public AuthService() {
        this.userRepository = new UserRepository();
    }

    public HashMap<String, Object> authenticate(String username, String password) {
        List<User> users = userRepository.findByUsernameAndPassword(username, password);
        HashMap<String, Object> response = new HashMap<String, Object>();

        if (users.size() == 0)
            throw new InsufficientAuthenticationException("401 returned");

        response.put("userId", users.get(0).getId());
        return response;
    }

    public HashMap<String, Object> isTeacher(String userId) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("isTeacher", userRepository.isUserFromType(userId, UserType.TEACHER.toString()));

        return response;
    }
}