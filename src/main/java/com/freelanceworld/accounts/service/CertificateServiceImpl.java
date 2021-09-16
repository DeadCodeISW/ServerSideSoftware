package com.freelanceworld.accounts.service;

import com.freelanceworld.accounts.domain.model.background.Certificate;
import com.freelanceworld.accounts.domain.repository.CertificateRepository;
import com.freelanceworld.accounts.domain.repository.SkillRepository;
import com.freelanceworld.accounts.domain.service.CertificateService;
import com.freelanceworld.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Certificate> getAllCertificatesBySkillId(Long skillId) {
        return certificateRepository.findAllBySkillId(skillId);
    }

    @Override
    public Optional<Certificate> getCertificateByIdAndSkillId(Long certificateId, Long skillId) {
        return certificateRepository.findByIdAndSkillId(certificateId, skillId);
    }

    @Override
    public Certificate createCertificate(Long skillId, Certificate certificate) {
        return skillRepository.findById(skillId).map(skill -> {
            certificate.setSkill(skill);
            return certificateRepository.save(certificate);
        })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Skill", "Id", skillId
                ));
    }

    @Override
    public Certificate updateCertificate(Long skillId, Long certificateId, Certificate certificateDetails) {
        if(!skillRepository.existsById(skillId))
            throw new ResourceNotFoundException(
                    "skill","Id", skillId
            );
        return certificateRepository.findById(certificateId)
                .map(certificate -> {
                    certificate.setCertificate_url(certificateDetails.getCertificate_url());
                    return certificateRepository.save(certificate);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "certificate","Id", certificateId
                ));
    }

    @Override
    public ResponseEntity<?> deleteCertificate(Long skillId, Long certificateId) {
        if(!skillRepository.existsById(skillId))
            throw new ResourceNotFoundException(
                    "skill","Id", skillId
            );
        return certificateRepository.findById(certificateId)
                .map(certificate -> {
                    certificateRepository.delete(certificate);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "certificate","Id", certificateId
                ));
    }
}
