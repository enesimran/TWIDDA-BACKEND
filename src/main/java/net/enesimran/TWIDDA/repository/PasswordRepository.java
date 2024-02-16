package net.enesimran.TWIDDA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.enesimran.TWIDDA.model.Password;

@Repository
public interface PasswordRepository extends JpaRepository<Password, String>{
    
}
