package softuni.repositories;


import softuni.entities.Tag;

import java.util.List;

public interface TagRepository {


    List<Tag> findTagByNames(List<String> names);

    Tag findByName(String name);
}
