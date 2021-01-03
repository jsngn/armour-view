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

import javax.inject.Inject;

import com.google.inject.Provides;
import net.runelite.api.*;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.kit.KitType;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@PluginDescriptor(
        name = "Armour View",
        description = "Overlays clothing items for fashionscape"
)
@Slf4j
public class ArmourViewPlugin extends Plugin
{
    private static final Set<Integer> overlays = new HashSet<>();

    @Inject
    private Client client;

    @Inject
    private ArmourViewConfig config;

    @Inject
    private OverlayManager overlayManager;

    @Subscribe
    public void onBeforeRender(BeforeRender r)
    {
        if (client.getGameState() != GameState.LOGGED_IN)
        {
            return;
        }

        Player player = client.getLocalPlayer();
        if (player == null)
        {
            return;
        }

        PlayerComposition comp = player.getPlayerComposition();
        int[] itemIDs = comp.getEquipmentIds();
        itemIDs[KitType.HAIR.getIndex()] = config.headIDConfig() + 512;
        itemIDs[KitType.TORSO.getIndex()] = config.torsoIDConfig() + 512;
        itemIDs[KitType.LEGS.getIndex()] = config.legsIDConfig() + 512;
        itemIDs[KitType.BOOTS.getIndex()] = config.bootsIDConfig() + 512;
        itemIDs[KitType.CAPE.getIndex()] = config.capeIDConfig() + 512;
        itemIDs[KitType.AMULET.getIndex()] = config.amuletIDConfig() + 512;
        itemIDs[KitType.WEAPON.getIndex()] = config.weaponIDConfig() + 512;
        itemIDs[KitType.SHIELD.getIndex()] = config.shieldIDConfig() + 512;
        itemIDs[KitType.HANDS.getIndex()] = config.handsIDConfig() + 512;

        comp.setHash();
    }

    @Provides
    ArmourViewConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ArmourViewConfig.class);
    }
}
