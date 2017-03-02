package softuni.repositories;


import softuni.entities.UserModel;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Stateless
@Local(UserRepository.class)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserModel findById(long id) {
        Query query = this.entityManager.createQuery("SELECT u FROM UserModel AS u WHERE u.id = :id");
        query.setParameter("id", id);
        UserModel userModel = null;
        if (query.getResultList().size() > 0) {
            userModel = (UserModel) query.getSingleResult();
        }

        return userModel;
    }

    @Override
    public boolean isEmailTaken(String email) {
        Query query = this.entityManager.createQuery("SELECT u FROM UserModel AS u JOIN u.roles AS r WHERE u.email =:email");
        query.setParameter("email", email);
        UserModel userModel = null;
        if (query.getResultList().size() > 0) {
            return false;
        }

        return true;
    }

    @Override
    public List<UserModel> getAll() {
        Query query = this.entityManager.createQuery("SELECT u FROM UserModel AS u ");
        List<UserModel> userModels = null;
        if (query.getResultList().size() > 0) {
            userModels = query.getResultList();
        }

        return Collections.unmodifiableList(userModels);
    }

    @Override
    public void update(UserModel userModel) {
        Query query = this.entityManager.createQuery("update UserModel as u set u.email = :email, " +
                "                                           u.fullName = :fullname, " +
                "                                           u.password = :password where u.id = :id");
        query.setParameter("id", userModel.getId());
        query.setParameter("name", userModel.getFullName());
        query.setParameter("email", userModel.getEmail());
        query.executeUpdate();
    }

    @Override
    public void createUser(UserModel user) {
        this.entityManager.persist(user);
    }

    @Override
    public UserModel findByUsername(String email) {
        Query query = this.entityManager.createQuery("select u from UserModel as u where u.email = :email");
        query.setParameter("email", email);
        UserModel user = null;
        if (query.getResultList().size() > 0) {
            user = (UserModel) query.getSingleResult();
        }

        return user;

    }
}
