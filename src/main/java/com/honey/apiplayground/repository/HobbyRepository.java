package com.honey.apiplayground.repository;

import com.honey.apiplayground.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, Integer> {

    List<Hobby> findAllByHobbyIn(Iterable<String> hobbies);
}
