package com.vibhanshu.qpassessment.repositories;

import com.vibhanshu.qpassessment.entities.Role;
import com.vibhanshu.qpassessment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
    public User findByRole(Role role);

}
