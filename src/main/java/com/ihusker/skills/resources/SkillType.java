package com.ihusker.skills.resources;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum SkillType {
    Archery(ChatColor.DARK_GREEN, 12.0f, null),
    COMBAT(ChatColor.RED, 7.8f, null),
    FISHING(ChatColor.BLUE,  0.0f, null),
    WOODCUTTING(ChatColor.GREEN, 6.4f, new HashSet<>(Arrays.asList(
            Material.ACACIA_LOG,
            Material.BIRCH_LOG,
            Material.DARK_OAK_LOG,
            Material.JUNGLE_LOG,
            Material.OAK_LOG,
            Material.SPRUCE_LOG
    ))),
    MINING(ChatColor.DARK_AQUA, 5.0f, new HashSet<>(Arrays.asList(
            Material.IRON_ORE,
            Material.COAL_ORE,
            Material.STONE,
            Material.GOLD_ORE,
            Material.DIAMOND_ORE,
            Material.REDSTONE_ORE,
            Material.EMERALD_ORE,
            Material.LAPIS_ORE
    ))),
    EXCAVATION(ChatColor.YELLOW, 4.2f, new HashSet<>(Arrays.asList(
            Material.DIRT,
            Material.COARSE_DIRT,
            Material.SAND,
            Material.GRAVEL,
            Material.GRASS_BLOCK
    ))),
    SMELTING(ChatColor.AQUA, 6.8f, null),
    CRAFTING(ChatColor.GOLD, 0.25f, null);

    private final ChatColor color;
    private final float experience;
    private final Set<Material> materials;

    SkillType(ChatColor color, float experience, Set<Material> materials) {
        this.color = color;
        this.experience = experience;
        this.materials = materials;
    }

    public ChatColor getColor() {
        return color;
    }

    public float getExperience() {
        return experience;
    }

    public Set<Material> getMaterials() {
        return Collections.unmodifiableSet(materials);
    }

    public String getName() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
}
