package com.freelanceworld.accounts.domain.repository;

import com.freelanceworld.accounts.domain.model.background.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findAllBySkillId( Long skillId);
    Optional<Certificate> findByIdAndSkillId (Long certificateId, Long skillId);
}
