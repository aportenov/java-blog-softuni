package softuni.repositories;


import softuni.entities.Role;
import softuni.entities.UserModel;

public interface RoleRepository {

    Role findByName(String name);

    Role save(Role role);
}
