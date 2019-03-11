package exodia.exodia.services;

import exodia.exodia.models.service.UserServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    UserServiceModel createUser(UserServiceModel userServiceModel);

    UserServiceModel getUserByName(String username);

    List<UserServiceModel> getAllUsers();

}
