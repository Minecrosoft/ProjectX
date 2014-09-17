package twinrealm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import twinrealm.creativetab.TRCreativeTabs;

/**
 * Created by Kunii on 9/16/2014.
 */
public class TRBaseRock extends Block
{
    public TRBaseRock()
    {
        super(Material.rock);
        this.setCreativeTab(TRCreativeTabs.tabGeneral);
        this.setHardness(1.5F);
        this.setResistance(10F);

        setStepSound(soundTypePiston);
    }
}
