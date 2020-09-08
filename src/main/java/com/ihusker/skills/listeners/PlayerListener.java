package com.ihusker.skills.listeners;

import com.ihusker.skills.Skills;
import com.ihusker.skills.utilities.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(Chat.color("&a+ &7" + event.getPlayer().getName()));
        Skills.getInstance().getSkillManager().deserialize(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(Chat.color("&c- &7" + event.getPlayer().getName()));
        Skills.getInstance().getSkillManager().serialize(event.getPlayer().getUniqueId());
    }
}
