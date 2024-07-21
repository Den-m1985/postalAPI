package com.example.repositiry;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * @return  User by id
     */
    Optional<User> findById(String userId);

}
