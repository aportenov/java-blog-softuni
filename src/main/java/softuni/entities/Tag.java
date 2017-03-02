package softuni.entities;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name", unique = true)
    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
