package net.enesimran.TWIDDA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.enesimran.TWIDDA.model.Password;
import net.enesimran.TWIDDA.repository.PasswordRepository;

@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.passwordRepository = passwordRepository;
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String rawPass, String encodedPass) {
        return passwordEncoder.matches(rawPass, encodedPass);
    }

    public Password savePassword(Password passwordObject) {
        return passwordRepository.save(passwordObject);
    }

    public Password getPasswordByUserId(String userId) {
        return passwordRepository.getReferenceById(userId);
    }

}
