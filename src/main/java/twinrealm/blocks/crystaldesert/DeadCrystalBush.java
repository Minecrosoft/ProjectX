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

package twinrealm.blocks.crystaldesert;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import twinrealm.blocks.TRBlocks;
import twinrealm.creativetab.TRCreativeTabs;

/**
 * @author Ordinastie
 *
 */
public class DeadCrystalBush extends BlockDeadBush
{
    public DeadCrystalBush()
    {
        super();
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
        setCreativeTab(TRCreativeTabs.tabGeneral);
    }

    @Override
    protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == TRBlocks.crystalSand;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(this, 1, world.getBlockMetadata(x, y, z))));
    }
}
