package softuni.services;

import softuni.entities.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {

    Set<Tag> setArticleTags(List<String> tagStr);
}
