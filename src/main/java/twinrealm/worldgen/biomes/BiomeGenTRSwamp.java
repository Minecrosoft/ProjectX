package twinrealm.worldgen.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by lukas on 17.09.14.
 */
public class BiomeGenTRSwamp extends BiomeGenBase
{
    public BiomeGenTRSwamp(int biomeID)
    {
        super(biomeID);

        setHeight(height_PartiallySubmerged);
        setTemperatureRainfall(0.8F, 0.9F);

        this.theBiomeDecorator.treesPerChunk = 2;
        this.theBiomeDecorator.flowersPerChunk = 1;
        this.theBiomeDecorator.deadBushPerChunk = 1;
        this.theBiomeDecorator.mushroomsPerChunk = 8;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.clayPerChunk = 1;
        this.theBiomeDecorator.waterlilyPerChunk = 4;
        this.theBiomeDecorator.sandPerChunk2 = 0;
        this.theBiomeDecorator.sandPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 5;
        this.waterColorMultiplier = 14745518;

//        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 1, 1, 1));

        this.flowers.clear();
        this.addFlower(Blocks.red_flower, 1, 10);
    }
}
