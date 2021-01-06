/*
 * Copyright (c) 2021, Josephine Nguyen <https://github.com/jsngn>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.runelite.client.plugins.armourview;

import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Config;

@ConfigGroup("armourview")
public interface ArmourViewConfig extends Config
{
    @ConfigItem(
            position = 1,
            keyName = "headConfig",
            name = "Change Head Item",
            description = "Would you like to try on a head slot item?"
    )
    default boolean headConfig() { return true; }

    @ConfigItem(
            position = 2,
            keyName = "headIDConfig",
            name = "Head Slot Item ID",
            description = "ID of a head slot equipment"
    )
    default int headIDConfig() { return 1048; } // white party hat

    @ConfigItem(
            position = 3,
            keyName = "hairConfig",
            name = "Change Hair Item",
            description = "Would you like to try on a hair slot item?"
    )
    default boolean hairConfig() { return true; }

    @ConfigItem(
            position = 4,
            keyName = "hairIDConfig",
            name = "Hair Slot Item ID",
            description = "ID of a hair slot equipment"
    )
    default int hairIDConfig() { return 23886; } // crystal helm basic

    @ConfigItem(
            position = 5,
            keyName = "torsoConfig",
            name = "Change Torso Item",
            description = "Would you like to try on a torso slot item?"
    )
    default boolean torsoConfig() { return true; }

    @ConfigItem(
            position = 6,
            keyName = "torsoIDConfig",
            name = "Torso Slot Item ID",
            description = "ID of a torso slot equipment"
    )
    default int torsoIDConfig() { return 1833; } // desert shirt

    @ConfigItem(
            position = 7,
            keyName = "legsConfig",
            name = "Change Legs Item",
            description = "Would you like to try on a legs slot item?"
    )
    default boolean legsConfig() { return true; }

    @ConfigItem(
            position = 8,
            keyName = "legsIDConfig",
            name = "Legs Slot Item ID",
            description = "ID of a legs slot equipment"
    )
    default int legsIDConfig() { return 6390; } // desert legs

    @ConfigItem(
            position = 9,
            keyName = "bootsConfig",
            name = "Change Boots Item",
            description = "Would you like to try on a boots slot item?"
    )
    default boolean bootsConfig() { return true; }

    @ConfigItem(
            position = 10,
            keyName = "bootsIDConfig",
            name = "Boots Slot Item ID",
            description = "ID of a boots slot equipment"
    )
    default int bootsIDConfig() { return 1837; } // desert boots

    @ConfigItem(
            position = 11,
            keyName = "capeConfig",
            name = "Change Cape Item",
            description = "Would you like to try on a cape slot item?"
    )
    default boolean capeConfig() { return true; }

    @ConfigItem(
            position = 12,
            keyName = "capeIDConfig",
            name = "Cape Slot Item ID",
            description = "ID of a cape slot equipment"
    )
    default int capeIDConfig() { return 1052; } // cape of legends

    @ConfigItem(
            position = 13,
            keyName = "amuletConfig",
            name = "Change Amulet Item",
            description = "Would you like to try on an amulet slot item?"
    )
    default boolean amuletConfig() { return true; }

    @ConfigItem(
            position = 14,
            keyName = "amuletIDConfig",
            name = "Amulet Slot Item ID",
            description = "ID of a amulet slot equipment"
    )
    default int amuletIDConfig() { return 1654; } // gold necklace

    @ConfigItem(
            position = 15,
            keyName = "weaponConfig",
            name = "Change Weapon Item",
            description = "Would you like to try on a weapon slot item?"
    )
    default boolean weaponConfig() { return true; }

    @ConfigItem(
            position = 16,
            keyName = "weaponIDConfig",
            name = "Weapon Slot Item ID",
            description = "ID of a weapon slot equipment"
    )
    default int weaponIDConfig() { return 1279; } // iron sword

    @ConfigItem(
            position = 17,
            keyName = "shieldConfig",
            name = "Change Shield Item",
            description = "Would you like to try on a shield slot item?"
    )
    default boolean shieldConfig() { return true; }

    @ConfigItem(
            position = 18,
            keyName = "shieldIDConfig",
            name = "Shield Slot Item ID",
            description = "ID of a shield slot equipment"
    )
    default int shieldIDConfig() { return 1177; } // steel sq shield

    @ConfigItem(
            position = 19,
            keyName = "handsConfig",
            name = "Change Hands Item",
            description = "Would you like to try on a hands slot item?"
    )
    default boolean handsConfig() { return true; }

    @ConfigItem(
            position = 20,
            keyName = "handsIDConfig",
            name = "Hands Slot Item ID",
            description = "ID of a hands slot equipment"
    )
    default int handsIDConfig() { return 2902; } // grey gloves
}
