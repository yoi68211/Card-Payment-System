package com.os.user.repository;

import com.os.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    User findByEmail(String email);
}
