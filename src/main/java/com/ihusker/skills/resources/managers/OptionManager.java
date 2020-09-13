package com.ihusker.skills.resources.managers;

import com.ihusker.skills.Skills;
import com.ihusker.skills.resources.data.OptionData;
import com.ihusker.skills.utilities.JsonStorage;
import org.bukkit.entity.Player;

public class OptionManager {

    private OptionData data;

    public void deserialize() {
        OptionData optionData = JsonStorage.read(Skills.getInstance(), "options", OptionData.class);

        if(optionData == null) {
            optionData = new OptionData();
            JsonStorage.write(Skills.getInstance(), "options", optionData);
        }

        data = optionData;
    }

    public OptionData getData() {
        return data;
    }
}
