package net.enesimran.TWIDDA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.enesimran.TWIDDA.dto.UserDTO;
import net.enesimran.TWIDDA.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String Email);
    User findByEmail(String Email);
    @Query("SELECT new net.enesimran.TWIDDA.dto.UserDTO(u.id, u.email, u.username) FROM User u WHERE u.email = ?1")
    UserDTO getUserDetailsByEmail(String email);
}