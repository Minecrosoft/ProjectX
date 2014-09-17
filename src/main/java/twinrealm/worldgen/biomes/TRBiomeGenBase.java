package twinrealm.worldgen.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;

import static net.minecraft.world.biome.BiomeGenBase.*;

/**
 * Created by lukas on 17.09.14.
 */
public final class TRBiomeGenBase
{
    public static BiomeGenBase crudeWoods;

    public static void init()
    {
        crudeWoods = (new BiomeGenCrudeWoods(130)).setColor(353825).setBiomeName("Crude Woods");
    }
}
