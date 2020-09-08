package com.ihusker.skills.resources.data;

import com.ihusker.skills.resources.SkillType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OptionData {

    public final Map<SkillType, Float> experiences = new HashMap<>();

    public OptionData() {
        Arrays.asList(SkillType.values()).forEach(skillType -> experiences.put(skillType, skillType.getExperience()));
    }

    public float getExperience(SkillType skillType) {
        return experiences.get(skillType);
    }
}
