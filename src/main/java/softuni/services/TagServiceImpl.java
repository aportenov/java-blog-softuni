package softuni.services;

import softuni.entities.Tag;
import softuni.repositories.TagRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
@Local(TagService.class)
public class TagServiceImpl implements TagService {

    @Inject
    private TagRepository tagRepository;

    @Override
    public Set<Tag> setArticleTags(List<String> tagStr) {
        Set<Tag> tags = new HashSet<>();
        for (String tagName : tagStr) {
            Tag tag = tagRepository.findByName(tagName);
            tags.add(tag);
        }

        return tags;
    }
}
