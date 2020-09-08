package com.ihusker.skills.utilities;

import com.ihusker.skills.resources.data.SkillData;
import com.ihusker.skills.resources.managers.SkillManager;
import com.ihusker.skills.resources.SkillType;
import org.bukkit.ChatColor;

public class Chat {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String level(SkillType skillType, SkillData skillData) {
        int level = (int) skillData.getLevel(skillType);
        float experience = skillData.getExperience(skillType);
        float experienceNeeded = skillData.getExperienceNeeded(skillType);
        double money = Math.round(SkillManager.money(experience) * 100.0) / 100.0;

        String prefix = skillType.getColor() + skillType.getName();
        String experiences = (int) experience + "&7/&f" + (int) experienceNeeded;

        return color(prefix + ": &7Lvl " + level + " &8| &f" + experiences + " exp &8| &a+ $" + money);
    }

    public static String levelUp(SkillData skillData, SkillType skillType) {
        return "&7You have leveled up to &f" + ((int) skillData.getLevel(skillType) + 1) + "&7";
    }
}
