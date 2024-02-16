package net.enesimran.TWIDDA.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.enesimran.TWIDDA.dto.AuthResponse;
import net.enesimran.TWIDDA.dto.UserDTO;
import net.enesimran.TWIDDA.dto.UserExistence;
import net.enesimran.TWIDDA.model.Password;
import net.enesimran.TWIDDA.model.User;
import net.enesimran.TWIDDA.security.JwtUtil;
import net.enesimran.TWIDDA.service.AuthenticationService;
import net.enesimran.TWIDDA.service.PasswordService;
import net.enesimran.TWIDDA.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthenticationService authService;
    private final UserService userService;
    private final PasswordService passwordService;
    private final JwtUtil jwt;

    @Autowired
    public AuthController(AuthenticationService authService, UserService userService, PasswordService passwordService, JwtUtil jwt) {
        this.authService = authService;
        this.userService = userService;
        this.passwordService = passwordService;
        this.jwt = jwt;
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        UserDTO authedUserObject = authService.loginUser(email, password);
        if (authedUserObject != null) {
            String jwtToken = jwt.generateToken(authedUserObject.getId());
            return ResponseEntity.ok(new AuthResponse(jwtToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Login fehlgeschlagen. Bitte prüfen Sie Ihre Eingabe.");
        } 
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        User newUser = new User();
        UserExistence existence = userService.checkUserAvailability(username, email, newUser.getId());
        if (existence.isMailExists() || existence.isUsernameExists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("MailExists", String.valueOf(existence.isMailExists()));
            headers.add("NameExists", String.valueOf(existence.isUsernameExists()));
            return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers)
            .body("Benutzername oder E-Mail-Adresse bereits vorhanden.");
        } 

        while (existence.isIdExists()) {
            newUser.setId(UUID.randomUUID().toString());
            existence.setIdExists(userService.doesUserExistById(newUser.getId()));
        }

        Password newPassword = new Password();
        newPassword.setHash(passwordService.hashPassword(password));
        newPassword.setUserid(newUser.getId());
        passwordService.savePassword(newPassword);

        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(newPassword);
        User returnUser = userService.addUser(newUser);

        return ResponseEntity.ok().body(returnUser);

    }


}
