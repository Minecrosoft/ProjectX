package twinrealm.events;

import cpw.mods.fml.common.FMLCommonHandler;

/**
 * Created by lukas on 15.09.14.
 */
public class TREventHandlerFML
{
    public void register()
    {
        FMLCommonHandler.instance().bus().register(this);
    }
}
