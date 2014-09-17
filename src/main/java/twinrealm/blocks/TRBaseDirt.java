package twinrealm.blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Kunii on 9/16/2014.
 */
public class TRBaseDirt extends Block
{
    public TRBaseDirt()
    {
        super(Material.ground);
        this.setCreativeTab(CreativeTabs.tabBlock); //TODO: Change this to the proper thingything tab - Words are hard, mkay?
    }
}
