
package twinrealm.blocks.crystaldesert;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import twinrealm.TwinRealm;
import twinrealm.blocks.TRBaseRock;
import twinrealm.renderer.CrystalOreRenderer;
import twinrealm.worldgen.biomes.BiomeGenCrystalDesert;

/**
 * @author Ordinastie
 *
 */
public class CrystalOre extends TRBaseRock
{
    public static int renderId;

    private IIcon overlays[];

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        super.registerBlockIcons(register);
        int n = 5;
        overlays = new IIcon[n + 1];
        for(int i = 1; i < n + 1; i++)
            overlays[i] = register.registerIcon(TwinRealm.MODID + ":crystalOre_overlay" + i);
    }

    public IIcon getIconOverlay(int metadata)
    {
        if(metadata < 1 || metadata > overlays.length)
            metadata = 1;
        return overlays[metadata];
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
    public int getRenderBlockPass()
    {
        return 1;
    }


    @Override
    public boolean canRenderInPass(int pass)
    {
        CrystalOreRenderer.renderPass = pass;
        return true;
    }

    @Override
    public int getRenderType()
    {
      return renderId;
    }
}
