package com.jtrio.zagzag.user;

import com.jtrio.zagzag.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByName(String name);

    User findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    boolean existsByEmail(String email);//Boolean
//    boolean existsById(Long id);//Boolean
}
