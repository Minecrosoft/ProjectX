package twinrealm.worldgen;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;

/**
 * Created by lukas on 15.09.14.
 */
public class WorldChunkManagerTwinRealm extends WorldChunkManagerHell // TODO Implement
{
    public WorldChunkManagerTwinRealm()
    {
        super(BiomeGenBase.desert, 0.0f);
    }
}
