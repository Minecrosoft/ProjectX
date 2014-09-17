package twinrealm.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import twinrealm.blocks.TRBlocks;

/**
 * Created by lukas on 17.09.14.
 */
public final class TRCreativeTabs
{
    public static CreativeTabs tabGeneral;

    public static void init()
    {
        tabGeneral = new CreativeTabs("trealm")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(TRBlocks.redRock);
            }
        };
    }
}
