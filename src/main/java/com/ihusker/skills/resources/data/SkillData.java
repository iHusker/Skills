package com.ihusker.skills.resources.data;

import com.ihusker.skills.resources.managers.SkillManager;
import com.ihusker.skills.resources.SkillType;

import java.util.HashMap;
import java.util.Map;

public class SkillData {

    private final Map<SkillType, Float> skills = new HashMap<>();

    public boolean contains(SkillType skillType) {
        return skills.containsKey(skillType);
    }

    public float getLevel(SkillType skillType) {
        return (float) (SkillManager.CONSTANT * Math.sqrt(skills.get(skillType)));
    }

    public float getExperience(SkillType skillType) {
        return (float) Math.pow(getLevel(skillType) / SkillManager.CONSTANT, 2);
    }

    public float getExperienceNeeded(SkillType skillType) {
        return (float) Math.pow(((int) getLevel(skillType) + 1.0f)  / SkillManager.CONSTANT, 2);
    }

    public boolean addExperience(SkillType skillType, float experience) {
        skills.merge(skillType, experience, Float::sum);
        return getExperience(skillType) + experience >= getExperienceNeeded(skillType);
    }
}
