package com.ihusker.skills.resources.managers;

import com.ihusker.skills.Skills;
import com.ihusker.skills.interfaces.IManager;
import com.ihusker.skills.resources.data.OptionData;
import com.ihusker.skills.utilities.JsonStorage;

public class OptionManager implements IManager {

    private OptionData data;

    @Override
    public void serialize() {
        JsonStorage.write(Skills.getInstance(), "options", data);
    }

    @Override
    public void deserialize() {
        OptionData optionData = JsonStorage.read(Skills.getInstance(), "options", OptionData.class);
        data = optionData == null ? new OptionData() : optionData;
    }

    public OptionData getData() {
        return data;
    }
}
