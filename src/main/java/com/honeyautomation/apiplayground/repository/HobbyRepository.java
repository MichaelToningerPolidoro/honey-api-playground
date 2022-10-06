package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Integer> {
}
