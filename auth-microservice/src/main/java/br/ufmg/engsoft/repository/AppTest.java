package br.ufmg.engsoft.repository;

import br.ufmg.engsoft.models.User;

import java.util.List;

public class AppTest {
    public static void main(String args[]) {
        User user = new User();
        user.setUsername("user");
        user.setPassword("teste");

        UserRepository userRepository = new UserRepository();

        try {
            userRepository.add(user);
            System.out.println("Usuário adicionado! \n \n \n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List haveFound = userRepository.findByUsernameAndPassword("user", "teste");

        System.out.println("Have found user! \n \n \n");
        System.out.println(haveFound);
    }
}
