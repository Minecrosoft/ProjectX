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

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twinrealm.blocks.TRBaseSand;
import twinrealm.blocks.TRBlocks;
import twinrealm.client.fx.QuickSandFx;
import twinrealm.worldgen.biomes.BiomeGenCrystalDesert;

/**
 * @author Ordinastie
 *
 */
public class QuickSand extends TRBaseSand
{
    @Override
    public void registerBlockIcons(IIconRegister register)
    {

    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return TRBlocks.crystalSand.getIcon(side, metadata);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        entity.motionX *= 0.3D;
        if(entity instanceof EntityLivingBase)
            entity.motionY = 0.074D;
        else
            entity.motionY = 0.03D;
        entity.motionZ *= 0.3D;
    }

    @Override
    public boolean isToolEffective(String type, int metadata)
    {
        return type.equals("shovel");
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        if (world.getBlock(x, y + 1, z).getMaterial() == Material.air && !world.getBlock(x, y + 1, z).isOpaqueCube())
        {
            if (rand.nextInt(50) == 0)
            {
                double dx = x + rand.nextFloat();
                double dy = y + 1;
                double dz = z + rand.nextFloat();
                QuickSandFx fx =new QuickSandFx(world, dx, dy, dz);
                fx.spawn();


                //world.spawnParticle("lava", dx, dy, dz, 0.0D, 0.0D, 0.0D);
                world.playSound(dx, dy, dz, "liquid.lavapop", 0.1F + rand.nextFloat() * 0.2F, 0.6F, false);
            }
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }


    @Override
    public int getRenderColor(int metadata)
    {
        return 0xFBC78B;
    }

    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        return BiomeGenCrystalDesert.CrystalDesertColorizer.getCrystalColor(x, y, z);
    }


    @Override
    public int getLightOpacity()
    {
        return 0;
    }

}
