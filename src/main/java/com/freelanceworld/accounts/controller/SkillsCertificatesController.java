package com.freelanceworld.accounts.controller;
import com.freelanceworld.accounts.resource.certificate.CertificateResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import com.freelanceworld.accounts.domain.model.background.Certificate;
import com.freelanceworld.accounts.domain.service.CertificateService;
import com.freelanceworld.accounts.resource.certificate.SaveCertificateResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SkillsCertificatesController {
    @Autowired
    private CertificateService certificateService;

    @Autowired
    private ModelMapper mapper;
    @Operation(tags = "Certificates")
    @GetMapping("/skills/{skillId}/certificates/")
    public List<CertificateResource> getAllCertificatesBySkillId(@PathVariable Long skillId)
    {
        return certificateService.getAllCertificatesBySkillId(skillId).stream().map(this::convertToResource).collect(Collectors.toList());
    }
    @Operation(tags = "Certificates")
    @GetMapping("skills/{skillId}/certificates/{certificateId}")
    public CertificateResource getCertificateById(@PathVariable Long skillId, @PathVariable Long certificateId){
        return convertToResource(certificateService.getCertificateByIdAndSkillId(certificateId, skillId).get());
    }

    @Operation(tags = "Certificates")
    @PostMapping("/skills/{skillId}/certificates")
    public CertificateResource createCertificate(@PathVariable Long skillId, @Valid @RequestBody SaveCertificateResource resource){
        Certificate certificate = convertToEntity(resource);
        return convertToResource(certificateService.createCertificate(skillId, certificate));
    }
    @Operation(tags = "Certificates")
    @PutMapping("/certificates/{certificateId}")
    public CertificateResource updateCertificate(@PathVariable Long skillId, Long certificateId, @Valid @RequestBody SaveCertificateResource resource){
        Certificate certificate = convertToEntity(resource);
        return convertToResource(certificateService.updateCertificate(skillId,certificateId,certificate));
    }

    @Operation(tags = "Certificates")
    @DeleteMapping("/certificates/{certificateId}")
    public ResponseEntity<?> deleteCertificate(@PathVariable Long
                                                       skillId, Long certificateId){
        return certificateService.deleteCertificate(skillId, certificateId);
    }

    private Certificate convertToEntity(SaveCertificateResource resource){
        return mapper.map(resource, Certificate.class);
    }

    private CertificateResource convertToResource(Certificate entity){
        return mapper.map(entity, CertificateResource.class);
    }



}