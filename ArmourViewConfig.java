package net.runelite.client.plugins.armourview;

import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("armourview")
public interface ArmourViewConfig extends Config {
    @ConfigItem(
            position = 1,
            keyName = "clothingIDConfig",
            name = "Clothing Item ID",
            description = "Input the ID of a desired clothing item"
    )
    default int clothingIDConfig() { return 1; }
}
