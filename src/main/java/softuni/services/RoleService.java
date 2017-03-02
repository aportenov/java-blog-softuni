package softuni.services;


import softuni.entities.Role;
import softuni.entities.UserModel;

public interface RoleService {

    Role findByName(String name);

    Role save(Role role);

    void updateRoles(UserModel userModel, String isAdmin, String isUser);
}
