
package twinrealm.blocks.crystaldesert;

import net.minecraft.world.IBlockAccess;
import twinrealm.blocks.TRBaseRock;

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
	    return 0xFBC78B;
		//return BiomeGenCrystalDesert.CrystalDesertColorizer.getCrystalColor(x, y, z);
	}

}
