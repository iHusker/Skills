package com.ihusker.skills.listeners;

import com.ihusker.skills.Skills;
import com.ihusker.skills.resources.SkillType;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftListener implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(player.getGameMode() != GameMode.SURVIVAL) return;

        ItemStack itemStack = event.getInventory().getResult();
        if(itemStack == null) return;

        Skills.getInstance().getSkillManager().update(player, SkillType.CRAFTING);
    }
}
