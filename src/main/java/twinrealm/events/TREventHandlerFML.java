package twinrealm.events;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import twinrealm.TwinRealm;
import twinrealm.configuration.TRConfig;

/**
 * Created by lukas on 15.09.14.
 */
public class TREventHandlerFML
{
    public void register()
    {
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent event)
    {
        if (event instanceof ConfigChangedEvent.OnConfigChangedEvent && event.modID.equals(TwinRealm.MODID))
        {
            TRConfig.loadConfig(event.configID);

            if (TwinRealm.config.hasChanged())
                TwinRealm.config.save();
        }
    }
}
