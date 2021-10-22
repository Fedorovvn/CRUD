package web.dao;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void add(User user) {
            em.persist(user);
    }

    @Transactional
    @Override
    public User getUser(Long id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
            em.remove(getUser(id));
    }


    @Override
    public List<User> listUsers() {
        return em.createQuery("from User").getResultList();
    }


    @Transactional
    @Override
    public void changeNickname(Long id, String newNickname){
        System.out.println(id);
        getUser(id).setNickname(newNickname);
    }

    @Transactional
    @Override
    public void changeEmail(Long id, String newEmail){
        System.out.println(id);
        getUser(id).setEmail(newEmail);
    }
}
