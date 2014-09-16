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
        int[] aint = this.parent.getInts(x, z, width, depth);
        int[] aint1 = IntCache.getIntCache(width * depth);

        for (int i1 = 0; i1 < depth; ++i1)
        {
            for (int j1 = 0; j1 < width; ++j1)
            {
                this.initChunkSeed((long)(j1 + x), (long)(i1 + z));
                int k1 = aint[j1 + i1 * width];
                int l1 = (k1 & 3840) >> 8;
                k1 &= -3841;

                if (isBiomeOceanic(k1))
                {
                    aint1[j1 + i1 * width] = k1;
                }
                else if (k1 == BiomeGenBase.mushroomIsland.biomeID)
                {
                    aint1[j1 + i1 * width] = k1;
                }
                else if (k1 == 1)
                {
                    if (l1 > 0)
                    {
                        if (this.nextInt(3) == 0)
                        {
                            aint1[j1 + i1 * width] = BiomeGenBase.mesaPlateau.biomeID;
                        }
                        else
                        {
                            aint1[j1 + i1 * width] = BiomeGenBase.mesaPlateau_F.biomeID;
                        }
                    }
                    else
                    {
                        aint1[j1 + i1 * width] = ((BiomeManager.BiomeEntry) WeightedRandom.getItem(this.desertBiomes, (int) (this.nextLong(WeightedRandom.getTotalWeight(this.desertBiomes) / 10) * 10))).biome.biomeID;
                    }
                }
                else if (k1 == 2)
                {
                    if (l1 > 0)
                    {
                        aint1[j1 + i1 * width] = BiomeGenBase.jungle.biomeID;
                    }
                    else
                    {
                        aint1[j1 + i1 * width] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.warmBiomes, (int)(this.nextLong(WeightedRandom.getTotalWeight(this.warmBiomes) / 10) * 10))).biome.biomeID;
                    }
                }
                else if (k1 == 3)
                {
                    if (l1 > 0)
                    {
                        aint1[j1 + i1 * width] = BiomeGenBase.megaTaiga.biomeID;
                    }
                    else
                    {
                        aint1[j1 + i1 * width] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.coolBiomes, (int)(this.nextLong(WeightedRandom.getTotalWeight(this.coolBiomes) / 10) * 10))).biome.biomeID;
                    }
                }
                else if (k1 == 4)
                {
                    aint1[j1 + i1 * width] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.icyBiomes, (int)(this.nextLong(WeightedRandom.getTotalWeight(this.icyBiomes) / 10) * 10))).biome.biomeID;
                }
                else
                {
                    aint1[j1 + i1 * width] = BiomeGenBase.mushroomIsland.biomeID;
                }
            }
        }

        return aint1;
    }
}