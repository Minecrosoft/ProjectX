
package twinrealm;

import static twinrealm.blocks.TRBlocks.*;
import static twinrealm.items.TRItems.*;
import net.minecraft.item.ItemBlock;
import twinrealm.blocks.BlockHillGrass;
import twinrealm.blocks.TRBaseDirt;
import twinrealm.blocks.TRBaseRock;
import twinrealm.blocks.TRBaseSand;
import twinrealm.blocks.crystaldesert.CrystalOre;
import twinrealm.blocks.crystaldesert.CrystalRock;
import twinrealm.blocks.crystaldesert.CrystalSand;
import twinrealm.blocks.crystaldesert.DeadCrystalBush;
import twinrealm.blocks.crystaldesert.QuickSand;
import twinrealm.items.crystaldesert.CrystalItem;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Ordinastie
 *
 */
public class Registers
{
    private static String textureBase = TwinRealm.MODID + ":";

    public static void init()
    {
        redRock = new TRBaseRock().setBlockName("redRock").setBlockTextureName(textureBase + "redRock");
        GameRegistry.registerBlock(redRock, ItemBlock.class, "red_rock");

        redDirt = new TRBaseDirt().setBlockName("redDirt").setBlockTextureName(textureBase + "redDirt");
        GameRegistry.registerBlock(redDirt, ItemBlock.class, "red_dirt");

        yellowDirt = new TRBaseSand().setBlockName("yellowDirt").setBlockTextureName(textureBase + "yellowDirt");
        GameRegistry.registerBlock(yellowDirt, ItemBlock.class, "yellow_dirt");

        limestone = new TRBaseRock().setBlockName("limestone").setBlockTextureName(textureBase + "limestone");
        GameRegistry.registerBlock(limestone, ItemBlock.class, "limestone");

        hillDirt = new TRBaseDirt().setBlockName("hillDirt").setBlockTextureName(textureBase + "hillDirt");
        GameRegistry.registerBlock(hillDirt, ItemBlock.class, "hill_dirt");

        hillGrass = (BlockHillGrass) new BlockHillGrass().setBlockName("hillGrass").setBlockTextureName(textureBase + "hillGrass");
        GameRegistry.registerBlock(hillGrass, ItemBlock.class, "hill_grass");

        registerDesertCrystal();
    }


    public static void registerDesertCrystal()
    {
        //blocks
        crystalSand = new CrystalSand().setBlockName("crystalSand").setBlockTextureName(textureBase + "crystalSand");
        GameRegistry.registerBlock(crystalSand, "crystalSand");

        crystalRock = new CrystalRock().setBlockName("crystalRock").setBlockTextureName(textureBase + "crystalRock");
        GameRegistry.registerBlock(crystalRock, "crystalRock");

        crystalOre = new CrystalOre().setBlockName("crystalOre");
        GameRegistry.registerBlock(crystalOre, "crystalOre");

        quickSand = new QuickSand().setBlockName("quickSand").setBlockTextureName(textureBase + "quickSand");
        GameRegistry.registerBlock(quickSand, "quickSand");

        deadCrystalBush = new DeadCrystalBush().setBlockName("deadCrystalBush").setBlockTextureName(textureBase + "deadCrystalBush");
        GameRegistry.registerBlock(deadCrystalBush, "deadCrystalBush");

        //items
        crystalItem = new CrystalItem().setUnlocalizedName("crystalItem").setTextureName(textureBase + "crystalItem");
        GameRegistry.registerItem(crystalItem, "crystalItem");
    }

}
