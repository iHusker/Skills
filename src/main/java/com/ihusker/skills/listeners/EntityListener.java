package com.ihusker.skills.listeners;

import com.ihusker.skills.Skills;
import com.ihusker.skills.resources.SkillType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EntityListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getHitEntity() instanceof Creature)) return;

        if (!(event.getEntity() instanceof Arrow)) return;
        Arrow arrow = (Arrow) event.getEntity();

        if (!(arrow.getShooter() instanceof Player)) return;
        Player player = (Player) arrow.getShooter();
        Skills.getInstance().getSkillManager().update(player, SkillType.ARCHERY);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        if (player == null) return;
        if(!(event.getEntity() instanceof Creature)) return;

        if (event.getEntity() instanceof Monster || event.getEntity() instanceof Player) {
            Skills.getInstance().getSkillManager().update(player, SkillType.COMBAT);
        }
    }
}
