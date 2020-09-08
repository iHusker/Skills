package com.ihusker.skills.listeners;

import com.ihusker.skills.Skills;
import com.ihusker.skills.resources.SkillType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;

public class FurnaceListener implements Listener {

    @EventHandler
    public void onExtract(FurnaceExtractEvent event) {
        Skills.getInstance().getSkillManager().update(event.getPlayer(), SkillType.SMELTING, event.getItemAmount());
    }
}
