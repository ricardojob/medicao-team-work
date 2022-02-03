package br.ufmg.engsoft.services;

import br.ufmg.engsoft.models.User;
import br.ufmg.engsoft.models.UserType;
import br.ufmg.engsoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    public User authenticate(String username, String password) {
        List<User> users = userRepository.findByUsernameAndPassword(username, password);

        return users.size() == 1 ? users.get(0) : null;
    }

    public boolean isTeacher(Integer userId) {
        return userRepository.isUserFromType(userId, UserType.TEACHER.toString());
    }
}