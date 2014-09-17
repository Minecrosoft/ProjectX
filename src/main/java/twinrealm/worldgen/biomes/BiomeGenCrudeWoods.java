package twinrealm.worldgen.biomes;

import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by lukas on 17.09.14.
 */
public class BiomeGenCrudeWoods extends BiomeGenBase
{
    public BiomeGenCrudeWoods(int biomeID)
    {
        super(biomeID);
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 2;

        this.func_76733_a(5159473);
        this.setTemperatureRainfall(0.7F, 0.8F);
    }
}
