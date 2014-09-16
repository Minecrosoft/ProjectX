package twinrealm.worldgen;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;
import twinrealm.worldgen.layer.GenLayerTRBiome;
import twinrealm.worldgen.layer.GenLayerTRBiomeEdge;

/**
 * Created by lukas on 16.09.14.
 */
public class WorldTypeTwinRealm extends WorldType
{
    public static final WorldTypeTwinRealm WORLD_TYPE = new WorldTypeTwinRealm("twinRealm");

    public WorldTypeTwinRealm(String name)
    {
        super(name);
    }

    @Override
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new GenLayerTRBiome(200L, parentLayer, this);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerTRBiomeEdge(1000L, ret);
        return ret;
    }
}
