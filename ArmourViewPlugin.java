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
        description = "Overlays clothing items for fashionscape",
        enabledByDefault = false
)
@Slf4j
public class ArmourViewPlugin extends Plugin
{
    private int equippedHeadID, equippedTorsoID, equippedLegsID, equippedBootsID, equippedCapeID,
            equippedAmuletID, equippedWeaponID, equippedShieldID, equippedHandsID;

    private int equippedHairID = Integer.MIN_VALUE;
    private int[] items;

    private boolean hasNotChanged = true;

    @Inject
    private Client client;

    @Inject
    private ArmourViewConfig config;

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

        log.debug("shutdown initiated; restoring actual equipment");

        PlayerComposition comp = player.getPlayerComposition();
        int[] itemIDs = comp.getEquipmentIds();
        for (int i = 0; i < itemIDs.length; i++) {
            itemIDs[i] = items[i];
        }

        comp.setHash();
    }

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
            log.debug("player is null");
            return;
        }

        ItemContainer container = client.getItemContainer(InventoryID.EQUIPMENT);
        if (container == null) {
            log.debug("container is null");
            return;
        }

        Item[] equipped = container.getItems();
        equippedHeadID = equipped[EquipmentInventorySlot.HEAD.getSlotIdx()].getId();
        equippedTorsoID = equipped[EquipmentInventorySlot.BODY.getSlotIdx()].getId();
        equippedLegsID = equipped[EquipmentInventorySlot.LEGS.getSlotIdx()].getId();
        equippedBootsID = equipped[EquipmentInventorySlot.BOOTS.getSlotIdx()].getId();
        equippedCapeID = equipped[EquipmentInventorySlot.CAPE.getSlotIdx()].getId();
        equippedAmuletID = equipped[EquipmentInventorySlot.AMULET.getSlotIdx()].getId();
        equippedWeaponID = equipped[EquipmentInventorySlot.WEAPON.getSlotIdx()].getId();
        equippedShieldID = equipped[EquipmentInventorySlot.SHIELD.getSlotIdx()].getId();
        equippedHandsID = equipped[EquipmentInventorySlot.GLOVES.getSlotIdx()].getId();

        PlayerComposition comp = player.getPlayerComposition();

        int[] itemIDs = comp.getEquipmentIds();
        if (hasNotChanged) {
            items = new int[itemIDs.length];
            for (int i = 0; i < itemIDs.length; i++) {
                items[i] = itemIDs[i];
            }
            hasNotChanged = false;
        }

        if (equippedHairID == Integer.MIN_VALUE) {
            equippedHairID = comp.getKitId(KitType.HAIR); //itemIDs[KitType.HAIR.getIndex()];
            log.debug("hello!!!!");
            log.debug("hair is " + itemIDs[KitType.HAIR.getIndex()]);
            log.debug("hair kit id is " + comp.getKitId(KitType.HAIR));
        }

        if (config.headConfig()) {
            itemIDs[KitType.HEAD.getIndex()] = config.headIDConfig() + 512;
        }
        else {
            itemIDs[KitType.HEAD.getIndex()] = equippedHeadID + 512;
        }

        if (config.hairConfig()) {
            itemIDs[KitType.HAIR.getIndex()] = config.hairIDConfig() + 512;
        }
        else {
            itemIDs[KitType.HAIR.getIndex()] = equippedHairID + 512;
            log.debug("hair is " + itemIDs[KitType.HAIR.getIndex()]);
        }

        if (config.torsoConfig()) {
            itemIDs[KitType.TORSO.getIndex()] = config.torsoIDConfig() + 512;
        }
        else {
            itemIDs[KitType.TORSO.getIndex()] = equippedTorsoID + 512;
        }

        if (config.legsConfig()) {
            itemIDs[KitType.LEGS.getIndex()] = config.legsIDConfig() + 512;
        }
        else {
            itemIDs[KitType.LEGS.getIndex()] = equippedLegsID + 512;
        }

        if (config.bootsConfig()) {
            itemIDs[KitType.BOOTS.getIndex()] = config.bootsIDConfig() + 512;
        }
        else {
            itemIDs[KitType.BOOTS.getIndex()] = equippedBootsID + 512;
        }

        if (config.capeConfig()) {
            itemIDs[KitType.CAPE.getIndex()] = config.capeIDConfig() + 512;
        }
        else {
            itemIDs[KitType.CAPE.getIndex()] = equippedCapeID + 512;
        }

        if (config.amuletConfig()) {
            itemIDs[KitType.AMULET.getIndex()] = config.amuletIDConfig() + 512;
        }
        else {
            itemIDs[KitType.AMULET.getIndex()] = equippedAmuletID + 512;
        }

        if (config.weaponConfig()) {
            itemIDs[KitType.WEAPON.getIndex()] = config.weaponIDConfig() + 512;
        }
        else {
            itemIDs[KitType.WEAPON.getIndex()] = equippedWeaponID + 512;
        }

        if (config.shieldConfig()) {
            itemIDs[KitType.SHIELD.getIndex()] = config.shieldIDConfig() + 512;
        }
        else {
            itemIDs[KitType.SHIELD.getIndex()] = equippedShieldID + 512;
        }

        if (config.handsConfig()) {
            itemIDs[KitType.HANDS.getIndex()] = config.handsIDConfig() + 512;
        }
        else {
            itemIDs[KitType.HANDS.getIndex()] = equippedHandsID + 512;
        }

        comp.setHash();
    }

    @Provides
    ArmourViewConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ArmourViewConfig.class);
    }
}
