package twinrealm.worldgen.layer;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import twinrealm.worldgen.biomes.BiomeManagerTwinRealm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 16.09.14.
 */
public class GenLayerTRBiome extends GenLayer
{
    private List<BiomeManager.BiomeEntry> desertBiomes = new ArrayList<BiomeManager.BiomeEntry>();
    private List<BiomeManager.BiomeEntry> warmBiomes = new ArrayList<BiomeManager.BiomeEntry>();
    private List<BiomeManager.BiomeEntry> coolBiomes = new ArrayList<BiomeManager.BiomeEntry>();
    private List<BiomeManager.BiomeEntry> icyBiomes = new ArrayList<BiomeManager.BiomeEntry>();

    public GenLayerTRBiome(long seed, GenLayer parent, WorldType worldType)
    {
        super(seed);

        this.parent = parent;

        this.desertBiomes.addAll(BiomeManagerTwinRealm.desertBiomes);
        this.warmBiomes.addAll(BiomeManagerTwinRealm.warmBiomes);
        this.coolBiomes.addAll(BiomeManagerTwinRealm.coolBiomes);
        this.icyBiomes.addAll(BiomeManagerTwinRealm.icyBiomes);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth)
    {
        int[] parentValues = this.parent.getInts(x, z, width, depth);
        int[] biomeValues = IntCache.getIntCache(width * depth);

        for (int zPlus = 0; zPlus < depth; ++zPlus)
        {
            for (int xPlus = 0; xPlus < width; ++xPlus)
            {
                this.initChunkSeed((long)(xPlus + x), (long)(zPlus + z));

                int index = xPlus + zPlus * width;

                int parentValue = parentValues[index];
                int specialBiomeBit = (parentValue & 0xf00) >> 8;
                parentValue &= ~0xf00;

                if (isBiomeOceanic(parentValue))
                {
                    biomeValues[index] = parentValue;
                }
                else if (parentValue == BiomeGenBase.mushroomIsland.biomeID)
                {
//                    biomeValues[index] = parentValue;
                    biomeValues[index] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.warmBiomes, (int)(this.nextLong(WeightedRandom.getTotalWeight(this.warmBiomes) / 10) * 10))).biome.biomeID;
                }
                else if (parentValue == 1)
                {
//                    if (specialBiomeBit > 0)
//                    {
//                        if (this.nextInt(3) == 0)
//                        {
//                            biomeValues[index] = BiomeGenBase.mesaPlateau.biomeID;
//                        }
//                        else
//                        {
//                            biomeValues[index] = BiomeGenBase.mesaPlateau_F.biomeID;
//                        }
//                    }
//                    else
                    {
                        biomeValues[index] = ((BiomeManager.BiomeEntry) WeightedRandom.getItem(this.desertBiomes, (int) (this.nextLong(WeightedRandom.getTotalWeight(this.desertBiomes) / 10) * 10))).biome.biomeID;
                    }
                }
                else if (parentValue == 2)
                {
//                    if (specialBiomeBit > 0)
//                    {
//                        biomeValues[index] = BiomeGenBase.jungle.biomeID;
//                    }
//                    else
                    {
                        biomeValues[index] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.warmBiomes, (int)(this.nextLong(WeightedRandom.getTotalWeight(this.warmBiomes) / 10) * 10))).biome.biomeID;
                    }
                }
                else if (parentValue == 3)
                {
//                    if (specialBiomeBit > 0)
//                    {
//                        biomeValues[index] = BiomeGenBase.megaTaiga.biomeID;
//                    }
//                    else
                    {
                        biomeValues[index] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.coolBiomes, (int)(this.nextLong(WeightedRandom.getTotalWeight(this.coolBiomes) / 10) * 10))).biome.biomeID;
                    }
                }
                else if (parentValue == 4)
                {
                    biomeValues[index] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.icyBiomes, (int)(this.nextLong(WeightedRandom.getTotalWeight(this.icyBiomes) / 10) * 10))).biome.biomeID;
                }
                else
                {
//                    biomeValues[index] = BiomeGenBase.mushroomIsland.biomeID;
                    biomeValues[index] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.warmBiomes, (int)(this.nextLong(WeightedRandom.getTotalWeight(this.warmBiomes) / 10) * 10))).biome.biomeID;
                }
            }
        }

        return biomeValues;
    }
}