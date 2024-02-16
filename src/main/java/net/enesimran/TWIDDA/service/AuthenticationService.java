package net.enesimran.TWIDDA.service;

import org.springframework.stereotype.Service;

import net.enesimran.TWIDDA.dto.UserDTO;
import net.enesimran.TWIDDA.dto.UserExistence;
import net.enesimran.TWIDDA.model.Password;

@Service
public class AuthenticationService {

    private final PasswordService passwordService;
    private final UserService userService;

    public AuthenticationService(PasswordService passwordService, UserService userService) {
        this.passwordService = passwordService;
        this.userService = userService;
    }

    public UserDTO loginUser(String mail, String pass) {
        UserExistence existence = userService.checkUserAvailability(mail);
        if (existence.isMailExists()) {
            UserDTO requestedUser = userService.getUserDTOByMail(mail);
            Password requestedPassword = passwordService.getPasswordByUserId(requestedUser.getId());
            if (passwordService.checkPassword(pass, requestedPassword.getHash())) {
                return requestedUser;
            }
        }
        return null;
    }
}
