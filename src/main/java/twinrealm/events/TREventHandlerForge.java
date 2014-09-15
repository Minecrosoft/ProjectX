package twinrealm.events;

import net.minecraftforge.common.MinecraftForge;

/**
 * Created by lukas on 15.09.14.
 */
public class TREventHandlerForge
{
    public void register()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
