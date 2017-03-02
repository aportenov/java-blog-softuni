package softuni.repositories;

import softuni.entities.UserModel;
import java.util.List;

public interface UserRepository {


    void createUser(UserModel user);

    UserModel findByUsername(String email);

    UserModel findById(long id);

    boolean isEmailTaken(String email);

    List<UserModel> getAll();

    void update(UserModel userModel);
}
