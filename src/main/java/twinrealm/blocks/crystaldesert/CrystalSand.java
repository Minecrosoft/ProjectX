
package twinrealm.blocks.crystaldesert;

import net.minecraft.world.IBlockAccess;
import twinrealm.blocks.TRBaseSand;

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
	    return 0xFBC78B;
        //return BiomeGenCrystalDesert.CrystalDesertColorizer.getCrystalColor(x, y, z);
	}
}
