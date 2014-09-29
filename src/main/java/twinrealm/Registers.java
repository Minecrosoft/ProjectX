/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Ordinastie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package twinrealm;

import static twinrealm.blocks.TRBlocks.*;
import net.minecraft.item.ItemBlock;
import twinrealm.blocks.TRBaseDirt;
import twinrealm.blocks.TRBaseRock;
import twinrealm.blocks.TRBaseSand;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Ordinastie
 *
 */
public class Registers
{
    private static String textureBase = TwinRealm.MODID + ":";

    public static void init()
    {
        redRock = new TRBaseRock().setBlockName("redRock").setBlockTextureName(textureBase + "redRock");
        GameRegistry.registerBlock(redRock, ItemBlock.class, "red_rock");

        redDirt = new TRBaseDirt().setBlockName("redDirt").setBlockTextureName(textureBase + "redDirt");
        GameRegistry.registerBlock(redDirt, ItemBlock.class, "red_dirt");

        yellowDirt = new TRBaseSand().setBlockName("yellowDirt").setBlockTextureName(textureBase + "yellowDirt");
        GameRegistry.registerBlock(yellowDirt, ItemBlock.class, "yellow_dirt");

        limestone = new TRBaseRock().setBlockName("limestone").setBlockTextureName(textureBase + "limestone");
        GameRegistry.registerBlock(limestone, ItemBlock.class, "limestone");
    }
}
