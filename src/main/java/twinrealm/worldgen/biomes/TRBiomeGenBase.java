package twinrealm.worldgen.biomes;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by lukas on 17.09.14.
 */
public final class TRBiomeGenBase
{
    public static BiomeGenBase wildflowerHills;

    public static BiomeGenBase crystalDesert;

    public static BiomeGenBase polarTundra;

    public static BiomeGenBase swamp;

    public static BiomeGenBase ocean;
    public static BiomeGenBase deepOcean;

    public static void init()
    {
        wildflowerHills = (new BiomeGenWildflowerHills(130)).setColor(0x56621).setBiomeName("Wildflower Hills");

        crystalDesert = (new BiomeGenCrystalDesert(131)).setColor(0xfa9418).setBiomeName("Crystal Desert");

        polarTundra = (new BiomeGenPolarTundra(132)).setColor(0x31554a).setBiomeName("Polar Tundra");

        swamp = (new BiomeGenTRSwamp(133)).setColor(0x7f9b2).setBiomeName("Swamps of Yung");

        ocean = (new BiomeGenTROcean(134)).setColor(0x70).setBiomeName("Twin Ocean");
        deepOcean = (new BiomeGenTROcean(135)).setColor(0x70).setBiomeName("Deep Twin sOcean");
    }
}
