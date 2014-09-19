package twinrealm.worldgen.biomes;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by lukas on 17.09.14.
 */
public class BiomeGenTROcean extends BiomeGenBase
{
    public BiomeGenTROcean(int biomeID)
    {
        super(biomeID);

        this.spawnableCreatureList.clear();
    }

    public BiomeGenBase.TempCategory getTempCategory()
    {
        return BiomeGenBase.TempCategory.OCEAN;
    }
}
