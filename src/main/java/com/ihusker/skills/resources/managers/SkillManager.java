package com.ihusker.skills.resources.managers;

import com.ihusker.skills.Skills;
import com.ihusker.skills.interfaces.IManager;
import com.ihusker.skills.resources.data.SkillData;
import com.ihusker.skills.resources.SkillType;
import com.ihusker.skills.utilities.Chat;
import com.ihusker.skills.utilities.JsonStorage;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkillManager implements IManager {

    private final Map<UUID, SkillData> data = new HashMap<>();

    public static final float CONSTANT = 0.1125f;

    public void deserialize(UUID uuid) {
        SkillData skillData = JsonStorage.read(Skills.getInstance(), uuid.toString(), SkillData.class);
        data.put(uuid, skillData == null ? new SkillData() : skillData);
    }

    public void serialize(UUID uuid) {
        JsonStorage.write(Skills.getInstance(), uuid.toString(), data.get(uuid));
        data.remove(uuid);
    }

    @Override
    public void serialize() {
        Skills.getInstance().getServer().getOnlinePlayers().forEach(player -> serialize(player.getUniqueId()));
    }

    @Override
    public void deserialize() {
        Skills.getInstance().getServer().getOnlinePlayers().forEach(player -> deserialize(player.getUniqueId()));
    }

    public static double money(float experience) {
        return Math.pow(CONSTANT, 3.2f) * experience;
    }

    public void update(Player player, SkillType skillType) {
        update(player, skillType, 1);
    }

    public void update(Player player, SkillType skillType, int amount) {
        SkillData skillData = data.get(player.getUniqueId());

        if(skillData.addExperience(skillType, Skills.getInstance().getOptionManager().getData().getExperience(skillType) * amount)) {
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            player.sendTitle(
                    Chat.color(skillType.getColor() + skillType.getName()),
                    Chat.color(Chat.levelUp(skillData, skillType)),
                    20, 40, 20
            );
        } else {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Chat.level(skillType, skillData)));
        }

        Skills.getInstance().getEconomy().depositPlayer(player, money(skillData.getExperience(skillType)));
    }

    public SkillData getData(Player player) {
        return data.get(player.getUniqueId());
    }
}
