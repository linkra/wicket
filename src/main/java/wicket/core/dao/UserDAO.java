package wicket.core.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import wicket.core.entity.User;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findById(Long id) {
        return get(id);
    }

    public User findBySurname(String surname) {
        return uniqueResult(namedQuery("wicket.core.entity.User.findBySurname"));
    }

    public long create(User user) {
        return persist(user).getUserid();
    }

    public List<User> findAll() {
        return list(namedQuery("wicket.core.entity.User.findAll"));
    }
}
