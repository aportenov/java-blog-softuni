package softuni.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 50,  message = "Name must be between 5 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only Latin letters")
    @Column(name="full_name")
    private String fullName;

    @NotNull
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email")
    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @NotNull
    @Size(min = 3, max = 30, message = "Password must be between 3 and 30 characters")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
     joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
     inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public UserModel() {
        this.roles = new HashSet<>();
    }

    public UserModel(String fullName, String email, String password) {
        this.setFullName(fullName);
        this.setEmail(email);
        this.setPassword(password);
        this.roles = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.getRoles().add(role);
    }

    public boolean isAdmin() {
        return this.roles.stream().anyMatch(role -> role.getRole().equals("ADMIN"));
    }
}
