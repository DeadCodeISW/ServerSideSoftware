package com.freelanceworld.accounts.resource.Skill;

import com.freelanceworld.common.SkillLevel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class SaveSkillResource {
    @NotNull
    @NotBlank
    @Size(min = 2,max = 20)
    private String name;
    @Size(min = 2,max = 200)
    private String description;
    @NotNull
    private SkillLevel level;
}
