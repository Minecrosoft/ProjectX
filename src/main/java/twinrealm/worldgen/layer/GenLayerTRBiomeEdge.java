package twinrealm.worldgen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

/**
 * Created by lukas on 16.09.14.
 */
public class GenLayerTRBiomeEdge extends GenLayer
{
    public GenLayerTRBiomeEdge(long p_i45475_1_, GenLayer p_i45475_3_)
    {
        super(p_i45475_1_);
        this.parent = p_i45475_3_;
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth)
    {
        int[] parentValues = this.parent.getInts(x - 1, z - 1, width + 2, depth + 2);
        int[] biomeValues = IntCache.getIntCache(width * depth);

        for (int zPlus = 0; zPlus < depth; ++zPlus)
        {
            for (int xPlus = 0; xPlus < width; ++xPlus)
            {
                this.initChunkSeed((long)(xPlus + x), (long)(zPlus + z));
                int parentValue = parentValues[xPlus + 1 + (zPlus + 1) * (width + 2)];

                if (!this.replaceBiomeEdgeIfNecessary(parentValues, biomeValues, xPlus, zPlus, width, parentValue, BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID) && !this.replaceBiomeEdgeCompletely(parentValues, biomeValues, xPlus, zPlus, width, parentValue, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mesa.biomeID) && !this.replaceBiomeEdgeCompletely(parentValues, biomeValues, xPlus, zPlus, width, parentValue, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesa.biomeID) && !this.replaceBiomeEdgeCompletely(parentValues, biomeValues, xPlus, zPlus, width, parentValue, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.taiga.biomeID))
                {
                    int valZNeg;
                    int valXPos;
                    int valXNeg;
                    int valZPos;

                    if (parentValue == BiomeGenBase.desert.biomeID)
                    {
                        valZNeg = parentValues[xPlus + 1 + (zPlus + 1 - 1) * (width + 2)];
                        valXPos = parentValues[xPlus + 1 + 1 + (zPlus + 1) * (width + 2)];
                        valXNeg = parentValues[xPlus + 1 - 1 + (zPlus + 1) * (width + 2)];
                        valZPos = parentValues[xPlus + 1 + (zPlus + 1 + 1) * (width + 2)];

                        if (valZNeg != BiomeGenBase.icePlains.biomeID && valXPos != BiomeGenBase.icePlains.biomeID && valXNeg != BiomeGenBase.icePlains.biomeID && valZPos != BiomeGenBase.icePlains.biomeID)
                        {
                            biomeValues[xPlus + zPlus * width] = parentValue;
                        }
                        else
                        {
                            biomeValues[xPlus + zPlus * width] = BiomeGenBase.extremeHillsPlus.biomeID;
                        }
                    }
                    else if (parentValue == BiomeGenBase.swampland.biomeID)
                    {
                        valZNeg = parentValues[xPlus + 1 + (zPlus + 1 - 1) * (width + 2)];
                        valXPos = parentValues[xPlus + 1 + 1 + (zPlus + 1) * (width + 2)];
                        valXNeg = parentValues[xPlus + 1 - 1 + (zPlus + 1) * (width + 2)];
                        valZPos = parentValues[xPlus + 1 + (zPlus + 1 + 1) * (width + 2)];

                        if (valZNeg != BiomeGenBase.desert.biomeID && valXPos != BiomeGenBase.desert.biomeID && valXNeg != BiomeGenBase.desert.biomeID && valZPos != BiomeGenBase.desert.biomeID && valZNeg != BiomeGenBase.coldTaiga.biomeID && valXPos != BiomeGenBase.coldTaiga.biomeID && valXNeg != BiomeGenBase.coldTaiga.biomeID && valZPos != BiomeGenBase.coldTaiga.biomeID && valZNeg != BiomeGenBase.icePlains.biomeID && valXPos != BiomeGenBase.icePlains.biomeID && valXNeg != BiomeGenBase.icePlains.biomeID && valZPos != BiomeGenBase.icePlains.biomeID)
                        {
                            if (valZNeg != BiomeGenBase.jungle.biomeID && valZPos != BiomeGenBase.jungle.biomeID && valXPos != BiomeGenBase.jungle.biomeID && valXNeg != BiomeGenBase.jungle.biomeID)
                            {
                                biomeValues[xPlus + zPlus * width] = parentValue;
                            }
                            else
                            {
                                biomeValues[xPlus + zPlus * width] = BiomeGenBase.jungleEdge.biomeID;
                            }
                        }
                        else
                        {
                            biomeValues[xPlus + zPlus * width] = BiomeGenBase.plains.biomeID;
                        }
                    }
                    else
                    {
                        biomeValues[xPlus + zPlus * width] = parentValue;
                    }
                }
            }
        }

        return biomeValues;
    }

    private boolean replaceBiomeEdgeIfNecessary(int[] parentValues, int[] biomeValues, int x, int z, int width, int currentValue, int sourceBiome, int replaceBiome)
    {
        if (!compareBiomesById(currentValue, sourceBiome))
        {
            return false;
        }
        else
        {
            int k1 = parentValues[x + 1 + (z + 1 - 1) * (width + 2)];
            int l1 = parentValues[x + 1 + 1 + (z + 1) * (width + 2)];
            int i2 = parentValues[x + 1 - 1 + (z + 1) * (width + 2)];
            int j2 = parentValues[x + 1 + (z + 1 + 1) * (width + 2)];

            if (this.canBiomesBeNeighbors(k1, sourceBiome) && this.canBiomesBeNeighbors(l1, sourceBiome) && this.canBiomesBeNeighbors(i2, sourceBiome) && this.canBiomesBeNeighbors(j2, sourceBiome))
            {
                biomeValues[x + z * width] = currentValue;
            }
            else
            {
                biomeValues[x + z * width] = replaceBiome;
            }

            return true;
        }
    }

    private boolean replaceBiomeEdgeCompletely(int[] parentValues, int[] biomeValues, int x, int z, int width, int currentValue, int sourceBiome, int replaceBiome)
    {
        if (currentValue != sourceBiome)
        {
            return false;
        }
        else
        {
            int k1 = parentValues[x + 1 + (z + 1 - 1) * (width + 2)];
            int l1 = parentValues[x + 1 + 1 + (z + 1) * (width + 2)];
            int i2 = parentValues[x + 1 - 1 + (z + 1) * (width + 2)];
            int j2 = parentValues[x + 1 + (z + 1 + 1) * (width + 2)];

            if (compareBiomesById(k1, sourceBiome) && compareBiomesById(l1, sourceBiome) && compareBiomesById(i2, sourceBiome) && compareBiomesById(j2, sourceBiome))
            {
                biomeValues[x + z * width] = currentValue;
            }
            else
            {
                biomeValues[x + z * width] = replaceBiome;
            }

            return true;
        }
    }

    private boolean canBiomesBeNeighbors(int biome1, int biome2)
    {
        if (compareBiomesById(biome1, biome2))
        {
            return true;
        }
        else if (BiomeGenBase.getBiome(biome1) != null && BiomeGenBase.getBiome(biome2) != null)
        {
            BiomeGenBase.TempCategory tempcategory = BiomeGenBase.getBiome(biome1).getTempCategory();
            BiomeGenBase.TempCategory tempcategory1 = BiomeGenBase.getBiome(biome2).getTempCategory();
            return tempcategory == tempcategory1 || tempcategory == BiomeGenBase.TempCategory.MEDIUM || tempcategory1 == BiomeGenBase.TempCategory.MEDIUM;
        }
        else
        {
            return false;
        }
    }
}