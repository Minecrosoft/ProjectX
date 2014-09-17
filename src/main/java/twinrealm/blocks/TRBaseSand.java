package twinrealm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import twinrealm.creativetab.TRCreativeTabs;

/**
 * Created by Kunii on 9/16/2014.
 */
public class TRBaseSand extends Block
{
    public TRBaseSand()
    {
        super(Material.sand);
        this.setCreativeTab(TRCreativeTabs.tabGeneral);
        this.setHardness(.5F);
        this.setResistance(.25F);
    }
}
