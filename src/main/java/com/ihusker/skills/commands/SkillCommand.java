package com.ihusker.skills.commands;

import com.ihusker.skills.resources.data.SkillData;
import com.ihusker.skills.resources.managers.SkillManager;
import com.ihusker.skills.resources.SkillType;
import com.ihusker.skills.utilities.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkillCommand implements CommandExecutor {

    private final SkillManager skillManager;

    public SkillCommand(SkillManager skillManager) {
        this.skillManager = skillManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            SkillData skillData = skillManager.getData(player);

            player.sendMessage("");
            player.sendMessage(Chat.color("  &fYour Skills:"));
            player.sendMessage("");
            String information;
            for(SkillType skillType : SkillType.values()) {
                if(skillData.contains(skillType)) {
                    information = Chat.level(skillType, skillData);
                } else {
                    information = skillType.getColor() + skillType.getName() + ": &7Not Learned.";
                }

                player.sendMessage("  " + Chat.color(information));
            }
            player.sendMessage("");
        }
        return true;
    }
}
