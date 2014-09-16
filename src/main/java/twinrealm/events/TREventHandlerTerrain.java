package twinrealm.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import twinrealm.worldgen.WorldTypeTwinRealm;
import twinrealm.worldgen.layer.GenLayerTwinRealm;
import twinrealm.worldgen.WorldProviderTwinRealm;

/**
 * Created by lukas on 16.09.14.
 */
public class TREventHandlerTerrain
{
    public void register()
    {
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void initBiomeGens(WorldTypeEvent.InitBiomeGens event)
    {
        if (event.worldType == WorldTypeTwinRealm.WORLD_TYPE)
        {
            event.newBiomeGens = GenLayerTwinRealm.initializeAllBiomeGenerators(event.seed, event.worldType);
        }
    }
}
