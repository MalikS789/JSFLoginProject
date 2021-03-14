package com.sparta.malik.JSFLoginProject.datastore;

import com.sparta.malik.JSFLoginProject.entities.UserEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

@Named
public class UserRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Collection<UserEntity> getAllUsers() {
        return entityManager.createNamedQuery("user.getAll", UserEntity.class).getResultList();
    }

    public UserEntity getUserByID(int id) {
        return entityManager.createNamedQuery("user.getById", UserEntity.class).setParameter("id", id).getSingleResult();
    }

    public UserEntity getUserByUsername(String username) {
        return entityManager.createNamedQuery("user.getByUsername", UserEntity.class).setParameter("username", username).getSingleResult();
    }

    public UserEntity getHighestIdUser() {
        return entityManager.createNamedQuery("user.getHighestIdUser", UserEntity.class).getResultList().get(0);
    }

    public void addUser(UserEntity user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.flush();
        entityManager.refresh(user);
        entityManager.getTransaction().commit();
    }

    public void updatePermission(String role, UserEntity user) {
        entityManager.getTransaction().begin();
        user.setUserType(role);
        entityManager.flush();
        entityManager.refresh(user);
        entityManager.getTransaction().commit();
    }
}
