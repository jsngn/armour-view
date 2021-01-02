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

import lombok.Lombok;
import net.runelite.api.ItemID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArmourViewOverlay extends WidgetItemOverlay {
    private final ItemManager itemManager;
    private final ArmourViewConfig config;
    ArrayList<Integer> items;

    @Inject
    private ArmourViewOverlay(ItemManager itemManager, ArmourViewConfig config)
    {
        showOnEquipment();
        showOnInventory();
        showOnBank();

        this.itemManager = itemManager;
        this.config = config;

        items = new ArrayList<>();
        ItemID IDListExhaustive = new ItemID();
        for (Field field : IDListExhaustive.getClass().getDeclaredFields()) {
            //field.setAccessible(true); // if you want to modify private fields
            System.out.println(field.getName());
        }
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem)
    {
        BufferedImage replacement = null;
        int ID = config.clothingIDConfig();
        replacement = itemManager.getImage(ID);
        if (replacement != null) {
            Rectangle bounds = widgetItem.getCanvasBounds();
            graphics.drawImage(replacement, (int) bounds.getX(), (int) bounds.getY(), null);
            System.out.println("replacement attempted");
        }
    }
}
