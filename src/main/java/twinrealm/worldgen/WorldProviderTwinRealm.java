package twinrealm.worldgen;

import net.minecraft.world.WorldProvider;

/**
 * Created by lukas on 15.09.14.
 */
public class WorldProviderTwinRealm extends WorldProvider
{
    @Override
    protected void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerTwinRealm();
    }

    @Override
    public String getDimensionName()
    {
        return "TwinRealm";
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return true;
    }
}
