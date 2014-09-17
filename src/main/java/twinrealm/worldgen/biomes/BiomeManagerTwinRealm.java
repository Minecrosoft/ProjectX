package twinrealm.worldgen.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.BiomeManager.*;

/**
 * Created by lukas on 16.09.14.
 */
public class BiomeManagerTwinRealm
{
    public static List<BiomeEntry> desertBiomes = new ArrayList<>();
    public static List<BiomeEntry> warmBiomes = new ArrayList<>();
    public static List<BiomeEntry> coolBiomes = new ArrayList<>();
    public static List<BiomeEntry> icyBiomes = new ArrayList<>();

    public static List<BiomeGenBase> oceanBiomes = new ArrayList<>();

    static
    {
        warmBiomes.add(new BiomeEntry(TRBiomeGenBase.crudeWoods, 10));
//        warmBiomes.add(new BiomeEntry(BiomeGenBase.roofedForest, 10));
//        warmBiomes.add(new BiomeEntry(BiomeGenBase.extremeHills, 10));
//        warmBiomes.add(new BiomeEntry(BiomeGenBase.plains, 10));
//        warmBiomes.add(new BiomeEntry(BiomeGenBase.birchForest, 10));
//        warmBiomes.add(new BiomeEntry(BiomeGenBase.swampland, 10));

        coolBiomes.add(new BiomeEntry(TRBiomeGenBase.crudeWoods, 10));
//        coolBiomes.add(new BiomeEntry(BiomeGenBase.extremeHills, 10));
//        coolBiomes.add(new BiomeEntry(BiomeGenBase.taiga, 10));
//        coolBiomes.add(new BiomeEntry(BiomeGenBase.plains, 10));

        icyBiomes.add(new BiomeEntry(BiomeGenBase.icePlains, 30));
//        icyBiomes.add(new BiomeEntry(BiomeGenBase.coldTaiga, 10));

        oceanBiomes.add(BiomeGenBase.ocean);
        oceanBiomes.add(BiomeGenBase.deepOcean);
//        oceanBiomes.add(BiomeGenBase.frozenOcean);

        desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.desert, 30));
        desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.savanna, 20));
//            desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.plains, 10));
    }
}
