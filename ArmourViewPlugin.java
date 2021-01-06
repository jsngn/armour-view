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

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@PluginDescriptor(
        name = "Armour View",
        description = "Try on equipment for fashionscape",
        enabledByDefault = false
)
@Slf4j
public class ArmourViewPlugin extends Plugin
{
    private int[] items; // array of item IDs of everything user has equipped upon using plugin

    private boolean hasNotChanged = true; // whether user "changed" their equipment with plugin yet; only true at start

    @Inject
    private Client client;

    @Inject
    private ArmourViewConfig config;

    /**
     * Restores user's actual equipment upon shuttin down plugin
     */
    @Override
    protected void shutDown() throws Exception
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

        log.debug("Shutdown initiated; restoring actual equipment");

        PlayerComposition comp = player.getPlayerComposition();
        int[] itemIDs = comp.getEquipmentIds();
        for (int i = 0; i < itemIDs.length; i++) {
            itemIDs[i] = items[i];
        }

        comp.setHash();
    }

    /**
     * Equips player model with specified clothing from config
     */
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
            log.debug("Player is null");
            return;
        }

        ItemContainer container = client.getItemContainer(InventoryID.EQUIPMENT);
        if (container == null) {
            log.debug("Container is null");
            return;
        }

        PlayerComposition comp = player.getPlayerComposition();

        int[] itemIDs = comp.getEquipmentIds(); // get all player's local equipment IDs

        /* keep record of user's equipment before they've changed anything
        * otherwise change their items as they specify */
        if (hasNotChanged) {
            items = new int[itemIDs.length];
            for (int i = 0; i < itemIDs.length; i++) {
                items[i] = itemIDs[i];
            }
            hasNotChanged = false;
        }
        else {
            // unequipping some local items results in normal-looking player model
            for (int i = 0; i < itemIDs.length; i++) {
                itemIDs[i] = items[i];
            }

            if (config.headConfig()) {
                itemIDs[KitType.HEAD.getIndex()] = config.headIDConfig() + 512;
            }

            if (config.hairConfig()) {
                itemIDs[KitType.HAIR.getIndex()] = config.hairIDConfig() + 512;
            }

            if (config.torsoConfig()) {
                itemIDs[KitType.TORSO.getIndex()] = config.torsoIDConfig() + 512;
            }

            if (config.legsConfig()) {
                itemIDs[KitType.LEGS.getIndex()] = config.legsIDConfig() + 512;
            }

            if (config.bootsConfig()) {
                itemIDs[KitType.BOOTS.getIndex()] = config.bootsIDConfig() + 512;
            }

            if (config.capeConfig()) {
                itemIDs[KitType.CAPE.getIndex()] = config.capeIDConfig() + 512;
            }

            if (config.amuletConfig()) {
                itemIDs[KitType.AMULET.getIndex()] = config.amuletIDConfig() + 512;
            }

            if (config.weaponConfig()) {
                itemIDs[KitType.WEAPON.getIndex()] = config.weaponIDConfig() + 512;
            }

            if (config.shieldConfig()) {
                itemIDs[KitType.SHIELD.getIndex()] = config.shieldIDConfig() + 512;
            }

            if (config.handsConfig()) {
                itemIDs[KitType.HANDS.getIndex()] = config.handsIDConfig() + 512;
            }

            comp.setHash();
        }
    }

    @Provides
    ArmourViewConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ArmourViewConfig.class);
    }
}
