package net.enesimran.TWIDDA.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.enesimran.TWIDDA.model.User;
import net.enesimran.TWIDDA.service.PasswordService;
import net.enesimran.TWIDDA.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, PasswordService passwordService) {
        this.userService = userService;
    }

    @GetMapping("/info") 
    public ResponseEntity<?> getUserInfo() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> returnObject = userService.getFullUserById(userId);

        if (returnObject != null) {
            return ResponseEntity.ok(returnObject);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    
}
