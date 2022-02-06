package br.ufmg.engsoft.repository;

import br.ufmg.engsoft.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


public class UserRepository {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public UserRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("user_pu");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public User add(User user) throws Exception {
        List<User> existingUserWithSameProperties = this.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existingUserWithSameProperties.size() > 0)
            throw new Exception("Usuário já existe!");

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    public List findByUsernameAndPassword(String username, String password) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2");
        query.setParameter(1, username);
        query.setParameter(2, password);
        return query.getResultList();
    }

    public boolean isUserFromType(Integer userId, String type) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = ?1 AND u.type = ?2");
        query.setParameter(1, userId);
        query.setParameter(2, type);
        return !query.getResultList().isEmpty();
    }

    public void close() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }
}
