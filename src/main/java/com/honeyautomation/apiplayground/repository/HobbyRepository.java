package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, Integer> {

    List<Hobby> findAllByHobbyIn(Iterable<String> hobbies);
}
