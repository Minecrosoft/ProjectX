package twinrealm.blocks.crystaldesert;

import net.minecraft.world.IBlockAccess;
import twinrealm.blocks.TRBaseRock;
import twinrealm.worldgen.biomes.BiomeGenCrystalDesert;

/**
 * @author Ordinastie
 *
 */
public class CrystalRock extends TRBaseRock
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
