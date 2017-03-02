package softuni.services;


import softuni.entities.UserModel;

import java.util.List;

public interface UserService {

    void createUser(UserModel userModel);

    UserModel findById(Long id);

    List<UserModel> findAll();

    boolean isAvailable(String email);

    UserModel findUserByEmail(String email);


    void updateUser(UserModel userModel);

    void updateRoles(UserModel userModel, String isAdmin, String isUser);
}
