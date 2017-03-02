package softuni.repositories;

import softuni.entities.Role;
import softuni.entities.UserModel;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(RoleRepository.class)
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Role findByName(String name) {
        Query query;
        query = this.entityManager.createQuery("select r from Role as r where r.name = :name");
        query.setParameter("name", name);

        if (query.getResultList().isEmpty()) {
            return null;
        }
        return (Role) query.getSingleResult();
    }

    @Override
    public Role save(Role role) {
        this.entityManager.persist(role);
        this.entityManager.flush();
        return role;
    }

}