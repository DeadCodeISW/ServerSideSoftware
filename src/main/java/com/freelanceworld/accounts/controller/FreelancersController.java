package com.freelanceworld.accounts.controller;
import com.freelanceworld.accounts.resource.freelancer.FreelancerResource;
import com.freelanceworld.accounts.resource.freelancer.UpdateFreelancerResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import com.freelanceworld.accounts.domain.model.Freelancer;
import com.freelanceworld.accounts.domain.service.FreelancerService;
import com.freelanceworld.accounts.resource.freelancer.SaveFreelancerResource;
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
public class FreelancersController {
    @Autowired
    private  FreelancerService freelancerService;

    @Autowired
    private  ModelMapper mapper;



    @Operation(tags = "Freelancers")
    @GetMapping("/freelancers")
    public List<FreelancerResource> getAllFreelancers(@RequestParam(required = false) String firstname,
                                                      @RequestParam(required = false) String lastname,
                                                      @RequestParam(required = false) String profession){
        if (firstname != null ) {
            return freelancerService.getFreelancersByFirstname(firstname).stream()
                    .map(this::convertToResource).collect(Collectors.toList());
        } else if(lastname != null){
            return freelancerService.getFreelancersByLastname(lastname)
                    .stream().map(this::convertToResource).collect(Collectors.toList());
        } else if(profession != null){
            return freelancerService.getFreelancersByProfession(profession)
                    .stream().map(this::convertToResource).collect(Collectors.toList());
        }
        return freelancerService.getAllFreelancers()
                .stream().map(this::convertToResource).collect(Collectors.toList());
    }
    @Operation(tags = "Freelancers")
    @GetMapping("/freelancers/{freelancerId}")
    public FreelancerResource getFreelancerById(@PathVariable Long freelancerId) {
        Optional<Freelancer> freelancer = freelancerService.getFreelancerById(freelancerId);
        return freelancer.map(this::convertToResource).orElse(null);
    }
    @Operation(tags = "Freelancers")
    @PostMapping("/freelancers")
    public FreelancerResource createFreelancer(@Valid @RequestBody SaveFreelancerResource resource){
        Freelancer freelancer = convertToEntity(resource);
        return convertToResource(freelancerService.createFreelancer(freelancer));
    }

    @Operation(tags = "Freelancers")
    @PutMapping("/freelancers/{freelancerId}")
    public FreelancerResource updateFreelancer(@PathVariable Long freelancerId, @Valid @RequestBody UpdateFreelancerResource resource){
        Freelancer freelancer = convertUpdateResourceToEntity(resource);
        freelancer.setUpdatedAt(new Date());
        return convertToResource(freelancerService.updateFreelancer(freelancerId, freelancer));
    }

    @Operation(tags = "Freelancers")
    @DeleteMapping("/freelancers/{freelancerId}")
    public ResponseEntity<?> deleteFreelancer(@PathVariable Long freelancerId){
        return freelancerService.deleteFreelancer(freelancerId);
    }

    private Freelancer convertToEntity(SaveFreelancerResource resource){
        return mapper.map(resource, Freelancer.class);
    }

    private Freelancer convertUpdateResourceToEntity(UpdateFreelancerResource resource){
        return mapper.map(resource, Freelancer.class);
    }

    private FreelancerResource convertToResource(Freelancer entity){
        return mapper.map(entity, FreelancerResource.class);
    }


}
