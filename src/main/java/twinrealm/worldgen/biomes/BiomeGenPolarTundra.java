package twinrealm.worldgen.biomes;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by lukas on 17.09.14.
 */
public class BiomeGenPolarTundra extends BiomeGenBase
{
    public BiomeGenPolarTundra(int biomeID)
    {
        super(biomeID);

        setTemperatureRainfall(-0.5F, 0.4F);
        this.setHeight(height_MidPlains);

        this.theBiomeDecorator.treesPerChunk = 1;
        this.theBiomeDecorator.grassPerChunk = 1;
        this.theBiomeDecorator.mushroomsPerChunk = 1;
    }
}
