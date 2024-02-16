package net.enesimran.TWIDDA.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.enesimran.TWIDDA.dto.UserDTO;
import net.enesimran.TWIDDA.dto.UserExistence;
import net.enesimran.TWIDDA.model.User;
import net.enesimran.TWIDDA.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);;
        }
    }

    public UserDTO getUserDTOByMail(String mail) {
        return userRepository.getUserDetailsByEmail(mail);
    }

    public Optional<User> getFullUserById(String userId) {
        return userRepository.findById(userId);
    }

    public boolean doesUserExistById(String userId) {
        return userRepository.existsById(userId);
    }

    @Transactional
    public UserExistence checkUserAvailability(String username, String mail, String id) {
        UserExistence existence = new UserExistence();
        existence.setUsernameExists(userRepository.existsByUsername(username));
        existence.setMailExists(userRepository.existsByEmail(mail));
        existence.setIdExists(userRepository.existsById(id));
        return existence;
    }

    public UserExistence checkUserAvailability(String mail) {
        UserExistence existence = checkUserAvailability("", mail, "");
        existence.setIdExists(false);
        existence.setUsernameExists(false);
        return existence;
    }

}
