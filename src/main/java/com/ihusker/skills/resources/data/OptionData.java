package com.ihusker.skills.resources.data;

import com.ihusker.skills.resources.DisplayType;
import com.ihusker.skills.resources.SkillType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class OptionData {

    private final Map<SkillType, Float> experiences = new HashMap<>();
    private final DisplayType displayType;

    public OptionData() {
        Arrays.asList(SkillType.values()).forEach(skillType -> experiences.put(skillType, skillType.getExperience()));
        displayType = DisplayType.ACTION_BAR;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public float getExperience(SkillType skillType) {
        return experiences.get(skillType);
    }
}
