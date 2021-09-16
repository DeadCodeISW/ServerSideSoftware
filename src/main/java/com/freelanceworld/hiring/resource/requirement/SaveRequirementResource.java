package com.freelanceworld.hiring.resource.requirement;

import com.freelanceworld.common.SkillLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveRequirementResource {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private SkillLevel level;
}
