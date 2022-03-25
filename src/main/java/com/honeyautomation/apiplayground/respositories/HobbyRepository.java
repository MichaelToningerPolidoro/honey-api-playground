package com.honeyautomation.apiplayground.respositories;

import com.honeyautomation.apiplayground.entities.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Integer> {
}
