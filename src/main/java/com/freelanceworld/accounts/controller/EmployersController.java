package com.freelanceworld.accounts.controller;

import com.freelanceworld.accounts.domain.model.Employer;
import com.freelanceworld.accounts.domain.service.EmployerService;
import com.freelanceworld.accounts.resource.employer.EmployerResource;
import com.freelanceworld.accounts.resource.employer.SaveEmployerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployersController {
    private final EmployerService employerService;
    private final ModelMapper mapper;

    @Autowired
    public EmployersController(EmployerService employerService, ModelMapper mapper) {
        this.employerService = employerService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get Employers", description = "Get All Employers",
            tags = "Employers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Employers",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/employers")
    public List<EmployerResource> getAllEmployers(@RequestParam(required = false) String firstname,
                                                  @RequestParam(required = false) String lastname,
                                                  @RequestParam(required = false) String companyName){
        if (firstname != null ) {
            return employerService.getEmployersByFirstname(firstname)
                    .stream().map(this::convertToResource).collect(Collectors.toList());

        } else if(lastname != null){
             return employerService.getEmployersByLastname(lastname)
                     .stream().map(this::convertToResource).collect(Collectors.toList());
        } else if(companyName != null){
            return employerService.getEmployerByCompanyName(companyName)
                    .stream().map(this::convertToResource).collect(Collectors.toList());
        }
        return employerService.getAllEmployers()
                .stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Operation(summary = "Get Employer By Id", description = "Get EmployerById",
            tags = "Employers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve an employer with the given Id",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/employers/{employerId}/")
    public EmployerResource getEmployerById(@PathVariable Long employerId){
        Optional<Employer> employer = employerService.getEmployerById(employerId);
        return employer.map(this::convertToResource).orElse(null);
    }

    @Operation(summary = "Post Employer", description = "Post Employer",
            tags = "Employers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should create an employer ",
            content = @Content(mediaType = "application/json"))})
    @PostMapping("/employers")
    public EmployerResource createEmployer(@Valid @RequestBody SaveEmployerResource resource){
        Employer employer = convertToEntity(resource);
        employer.setCreatedAt(new Date());
        return convertToResource(employerService.createEmployer(employer));
    }
    @Operation(summary = "Put Employer", description = "Put Employer",
            tags = "Employers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should update an employer ",
            content = @Content(mediaType = "application/json"))})
    @PutMapping("/employers/{employerId}")
    public EmployerResource updateEmployer(@PathVariable Long employerId, @Valid @RequestBody SaveEmployerResource resource){
        Employer employer = convertToEntity(resource);
        employer.setUpdatedAt(new Date());
        return convertToResource(employerService.updateEmployer(employerId, employer));
    }
    @Operation(summary = "Put Employer", description = "Put Employer",
            tags = "Employers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve an updated employer ",
            content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/employers/{employerId}")
    public ResponseEntity<?> deleteEmployer(@PathVariable Long employerId){
        return employerService.deleteEmployer(employerId);
    }

    private Employer convertToEntity(SaveEmployerResource resource){
        return mapper.map(resource, Employer.class);
    }

    private EmployerResource convertToResource(Employer entity){
        return mapper.map(entity, EmployerResource.class);
    }


}
