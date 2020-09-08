package com.ihusker.skills.listeners;

import com.ihusker.skills.Skills;
import com.ihusker.skills.resources.SkillType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;


public class FishingListener implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY || event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            Skills.getInstance().getSkillManager().update(event.getPlayer(), SkillType.FISHING);
        }
    }
}
