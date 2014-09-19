package twinrealm.worldgen.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by lukas on 17.09.14.
 */
public class BiomeGenCrystalDesert extends BiomeGenBase
{
    public BiomeGenCrystalDesert(int biomeID)
    {
        super(biomeID);

        setTemperatureRainfall(2.0F, 0.0F);
        setHeight(height_LowPlains);

        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 2;
        this.theBiomeDecorator.reedsPerChunk = 50;
        this.theBiomeDecorator.cactiPerChunk = 10;
        this.spawnableCreatureList.clear();
    }
}
