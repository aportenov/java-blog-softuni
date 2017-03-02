package softuni.services;

import softuni.entities.Role;
import softuni.entities.UserModel;
import softuni.repositories.RoleRepository;
import softuni.repositories.UserRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
@Local(UserService.class)
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

    @Override
    public void createUser(UserModel userModel) {
        this.userRepository.createUser(userModel);
    }

    @Override
    public UserModel findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public List<UserModel> findAll() {
        return this.userRepository.getAll();
    }

    @Override
    public boolean isAvailable(String email) {
        return this.userRepository.isEmailTaken(email);
    }

    @Override
    public UserModel findUserByEmail(String email) {
       return this.userRepository.findByUsername(email);
    }

    @Override
    public void updateUser(UserModel userModel) {
        this.userRepository.update(userModel);
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
