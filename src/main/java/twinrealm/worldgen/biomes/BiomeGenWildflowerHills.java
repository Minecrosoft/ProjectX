package twinrealm.worldgen.biomes;

import twinrealm.blocks.TRBlocks;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by lukas on 17.09.14.
 */
public class BiomeGenWildflowerHills extends BiomeGenBase
{
    public BiomeGenWildflowerHills(int biomeID)
    {
        super(biomeID);

        this.setColor(5271632);
        this.setTemperatureRainfall(0.8F, 0.4F);
        this.setHeight(height_LowHills);

        this.theBiomeDecorator.treesPerChunk = 2;
        this.theBiomeDecorator.flowersPerChunk = 12;
        this.theBiomeDecorator.mushroomsPerChunk = 1;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 5;

        topBlock = TRBlocks.hillGrass;
        fillerBlock = TRBlocks.hillDirt;
    }
}
