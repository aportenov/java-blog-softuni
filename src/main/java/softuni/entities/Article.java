package softuni.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 30,message = "title size must be more than 30 chars")
    private String title;

    @NotNull
    @Size(min = 50, message = "content size must be more than 50 chars")
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "articles_tags",
            joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags;

    @ManyToOne(targetEntity = UserModel.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private UserModel user;

    public Article() {
        this.setTags(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getTagsAsString() {
        return this.tags.stream()
                .map(Tag::getName)
                .collect(Collectors.joining(", "));
    }

    public String getShortContent() {
        if (this.content.length() > 100) {
            return this.content.substring(0,100).concat("...");
        }

        return this.content;
    }
}
