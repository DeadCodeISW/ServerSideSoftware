package com.freelanceworld.accounts.resource.Skill;
import com.freelanceworld.common.SkillLevel;
import lombok.Data;

@Data
public class SkillResource {
    private Long id;
    private String name;
    private String description;
    private SkillLevel level;
}
