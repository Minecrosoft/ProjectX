package twinrealm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import twinrealm.creativetab.TRCreativeTabs;

/**
 * Created by Kunii on 9/16/2014.
 */
public class TRBaseDirt extends Block
{
    public TRBaseDirt()
    {
        super(Material.ground);
        this.setCreativeTab(TRCreativeTabs.tabGeneral);
    }
}
