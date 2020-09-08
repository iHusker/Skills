package com.ihusker.skills;

import com.ihusker.skills.commands.SkillCommand;
import com.ihusker.skills.listeners.*;
import com.ihusker.skills.resources.managers.OptionManager;
import com.ihusker.skills.resources.managers.SkillManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Skills extends JavaPlugin {

    private static Skills instance;
    private Economy economy;

    private final OptionManager optionManager = new OptionManager();
    private final SkillManager skillManager = new SkillManager();

    @Override
    public void onEnable() {
        instance = this;

        if(setupEconomy()) {
            PluginCommand pluginCommand = getCommand("skills");
            if (pluginCommand != null) pluginCommand.setExecutor(new SkillCommand(skillManager));

            registerListeners(
                    new BlockListener(),
                    new CraftListener(),
                    new EntityListener(),
                    new FishingListener(),
                    new FurnaceListener(),
                    new PlayerListener()
            );

            optionManager.deserialize();
            skillManager.deserialize();
        } else {
            getLogger().info("Disabling plugin as vault needs to be installed/enabled.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @EventHandler
    public void onDisable() {
        if(economy != null) skillManager.serialize();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        economy = rsp.getProvider();
        return true;
    }


    private void registerListeners(Listener... listeners) {
        Arrays.asList(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    public static Skills getInstance() {
        return instance;
    }

    public Economy getEconomy() {
        return economy;
    }

    public OptionManager getOptionManager() {
        return optionManager;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }
}
