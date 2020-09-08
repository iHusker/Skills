package com.ihusker.skills.listeners;

import com.ihusker.skills.Skills;
import com.ihusker.skills.resources.SkillType;
import com.ihusker.skills.utilities.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(player.getGameMode() != GameMode.SURVIVAL) return;

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        Material material = itemStack.getType();
        if(material == Material.AIR) return;

        SkillType skillType;
        skillType = null;

        // WOODCUTTING
        if(ItemUtils.IsAxe(material)) skillType = SkillType.WOODCUTTING;
        else if(ItemUtils.IsPickaxe(material)) skillType = SkillType.MINING;
        else if(ItemUtils.IsShovel(material)) skillType = SkillType.EXCAVATION;

        if(skillType == null) return;
        if(skillType.getMaterials().contains(event.getBlock().getType())) {
            Skills.getInstance().getSkillManager().update(player, skillType);
        }
    }
}
