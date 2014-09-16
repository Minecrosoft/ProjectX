package twinrealm.worldgen;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;

/**
 * Created by lukas on 15.09.14.
 */
public class WorldProviderTwinRealm extends WorldProvider
{
    @Override
    protected void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManager(worldObj.getSeed(), WorldTypeTwinRealm.WORLD_TYPE);
    }

    @Override
    public String getDimensionName()
    {
        return "Twin Realm";
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return true;
    }
}
