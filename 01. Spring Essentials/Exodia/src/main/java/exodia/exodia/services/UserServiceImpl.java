package exodia.exodia.services;

import exodia.exodia.entities.User;
import exodia.exodia.models.service.UserServiceModel;
import exodia.exodia.parser.ModelParser;
import exodia.exodia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private ModelParser modelParser;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelParser modelParser, UserRepository userRepository) {
        this.modelParser = modelParser;
        this.userRepository = userRepository;
    }

    @Override
    public UserServiceModel createUser(UserServiceModel userServiceModel) {
       User user = this.modelParser.convert(userServiceModel, User.class);
        this.userRepository.save(user);
        return userServiceModel;
    }

    @Override
    public UserServiceModel getUserByName(String username) {
        return this.modelParser
                .convert(this.userRepository.findByUsername(username), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getAllUsers() {
        return this.userRepository.findAll().stream()
                .map(u -> this.modelParser.convert(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }


}
