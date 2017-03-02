package softuni.services;

import softuni.entities.Role;
import softuni.entities.UserModel;
import softuni.repositories.RoleRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Stateless
@Local(RoleService.class)
public class RoleServiceImpl implements RoleService{

    @Inject
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void updateRoles(UserModel userModel, String isAdmin, String isUser) {
        Set<Role> roles = new HashSet<>();
        if (isAdmin != null) {
            roles.add(this.roleRepository.findByName("ADMIN"));
        }
        if (isUser != null) {
            roles.add(this.roleRepository.findByName("USER"));
        }
        userModel.setRoles(roles);
    }
}
