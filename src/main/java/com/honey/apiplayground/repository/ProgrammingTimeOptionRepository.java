package com.honey.apiplayground.repository;

import com.honey.apiplayground.domain.ProgrammingTimeOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingTimeOptionRepository extends JpaRepository<ProgrammingTimeOption, Integer> {

    ProgrammingTimeOption findByProgrammingTime(String programmingTime);
}
