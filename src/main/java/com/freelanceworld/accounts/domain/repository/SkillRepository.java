package com.freelanceworld.accounts.domain.repository;
import com.freelanceworld.accounts.domain.model.background.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByFreelancerId(Long freelancerId);
    Optional<Skill> findByIdAndFreelancerId (Long skillId, Long freelancerId);
}
