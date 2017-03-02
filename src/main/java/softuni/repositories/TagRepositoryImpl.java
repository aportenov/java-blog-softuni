package softuni.repositories;

import softuni.entities.Tag;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
@Local(TagRepository.class)
public class TagRepositoryImpl implements TagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tag> findTagByNames(List<String> names) {
        List<Tag> tags = null;
        Query query = this.entityManager.createQuery("select t from Tag as t " +
                "where t.name = :name");
        query.setParameter("name", names);
        if (query.getResultList().size() > 0) {
            tags = query.getResultList();
        } else {
            tags = new ArrayList<>();
            for (String name : names) {
                Tag tag = new Tag();
                tag.setName(name);
                this.entityManager.persist(tag);
                tags.add(tag);
            }
        }

        return Collections.unmodifiableList(tags);
    }

    @Override
    public Tag findByName(String name) {
        Tag tag = null;
        Query query = this.entityManager.createQuery("select t from Tag as t " +
                "where t.name = :name");
        query.setParameter("name", name);
        if (query.getResultList().size() > 0) {
            tag = (Tag) query.getSingleResult();
        } else {
            tag = new Tag();
            tag.setName(name);
            this.entityManager.persist(tag);
        }

        return tag;
    }
}
