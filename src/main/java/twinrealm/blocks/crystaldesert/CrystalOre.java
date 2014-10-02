package twinrealm.blocks.crystaldesert;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twinrealm.TwinRealm;
import twinrealm.blocks.TRBaseRock;
import twinrealm.blocks.TRBlocks;
import twinrealm.renderer.CrystalOreRenderer;

/**
 * @author Ordinastie
 *
 */
public class CrystalOre extends TRBaseRock
{
    public static int renderId;

    private IIcon overlays[];
    private int variants = 5;

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        overlays = new IIcon[variants];
        for (int i = 0; i < variants; i++)
            overlays[i] = register.registerIcon(TwinRealm.MODID + ":crystalOre_overlay" + i);
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return TRBlocks.crystalRock.getIcon(side, metadata);
    }

    public IIcon getIconOverlay(int metadata)
    {
        if (metadata >= variants)
            metadata = 0;
        return overlays[metadata];
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
       world.setBlockMetadataWithNotify(x, y, z, world.rand.nextInt(variants), 2);
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        return world.rand.nextInt(variants);
    }

    @Override
    public int getRenderColor(int metadata)
    {
        return TRBlocks.crystalRock.getRenderColor(metadata);
    }

    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        return TRBlocks.crystalRock.colorMultiplier(world, x, y, z);
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
