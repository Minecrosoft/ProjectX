package twinrealm.blocks.crystaldesert;

import net.minecraft.world.IBlockAccess;
import twinrealm.blocks.TRBaseSand;
import twinrealm.worldgen.biomes.BiomeGenCrystalDesert;

/**
 * @author Ordinastie
 *
 */
public class CrystalSand extends TRBaseSand
{
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
}
