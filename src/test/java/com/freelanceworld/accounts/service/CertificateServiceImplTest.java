package com.freelanceworld.accounts.service;

import com.freelanceworld.accounts.domain.model.background.Certificate;
import com.freelanceworld.accounts.domain.model.background.Skill;
import com.freelanceworld.accounts.domain.repository.CertificateRepository;
import com.freelanceworld.accounts.domain.repository.FreelancerRepository;
import com.freelanceworld.accounts.domain.repository.SkillRepository;
import com.freelanceworld.accounts.domain.service.CertificateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CertificateServiceImplTest {
    @MockBean
    private CertificateRepository certificateRepository;
    @Autowired
    private CertificateService certificateService;
    @MockBean
    private SkillRepository skillRepository;
    @MockBean
    private FreelancerRepository freelancerRepository;
    @TestConfiguration
    static class CertificateServiceTestConfiguration{

        @Bean
        public CertificateService certificateService(){
            return new CertificateServiceImpl();
        }
    }

    @Test
    void whenGetCertificateBySkillIdAndCertificateIdWithValidIdThenReturnsCertificate() {
        //Arrange
        Long id = 1L;
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName("angular");

        Certificate certificate = new Certificate();
        certificate.setId(id);
        certificate.setCertificate_url("http://esteesuncertificado.com");
        when(certificateRepository.findByIdAndSkillId(id,id)).thenReturn(Optional.of(certificate));
        //Act
        Optional<Certificate> foundCertificate = certificateService.getCertificateByIdAndSkillId(id,id);
        //Assert
        assertThat(foundCertificate.get().getId()).isEqualTo(id);
    }

    @Test
    void whenGetAllCertificatesBySkillIdWithValidIdThenReturnsCertificates(){
        //Arrange
        Long id = 1L;
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName("angular");
        List<Certificate> certificateList = new ArrayList<Certificate>();
        Certificate certificate = new Certificate();
        certificate.setId(id);
        certificate.setSkill(skill);
        certificate.setCertificate_url("http://esteesuncertificado.com");
        certificateList.add(certificate);

        Certificate certificate2 = new Certificate();
        certificate2.setId(id);
        certificate2.setSkill(skill);
        certificate2.setCertificate_url("http://esteesuncertificado.com");
        certificateList.add(certificate2);
        when(certificateRepository.findAllBySkillId(id)).thenReturn(certificateList);
        //Act
        List<Certificate> foundCertificates = certificateService.getAllCertificatesBySkillId(id);
        //Assert
        assertThat(foundCertificates.get(0).getSkill().getId()).isEqualTo(id);
        assertThat(foundCertificates.size()).isEqualTo(2);
    }
}
