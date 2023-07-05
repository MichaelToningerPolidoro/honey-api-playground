package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);
    boolean existsByNickName(String nickName);
    User findByEmail(String email);
}
