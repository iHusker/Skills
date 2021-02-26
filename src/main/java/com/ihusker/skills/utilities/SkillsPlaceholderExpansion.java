package com.ihusker.skills.utilities;

import com.ihusker.skills.resources.SkillType;
import com.ihusker.skills.resources.data.SkillData;
import com.ihusker.skills.resources.managers.SkillManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SkillsPlaceholderExpansion extends PlaceholderExpansion {


    private final SkillManager skillManager;

    public SkillsPlaceholderExpansion(SkillManager skillManager) {
        this.skillManager = skillManager;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "peacefulskills";
    }

    @Override
    public @NotNull String getAuthor() {
        return "iHusker";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }


    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {

        if(params.equals("level")) {
            return String.valueOf(skillManager.getAverage(player));
        }

        if(params.contains("type")) {
            SkillData skillData = skillManager.getData(player);
            if(skillData != null) {
                String information;
                for (SkillType skillType : SkillType.values()) {
                    if (skillData.contains(skillType)) {
                        information = Chat.level(skillType, skillData);
                    } else {
                        information = skillType.getColor() + skillType.getName() + ": &7Not Learned.";
                    }

                    if (params.contains(skillType.getName().toLowerCase())) {
                        return information;
                    }
                }
            }
        }

        return null;
    }
}
