package twinrealm.configuration;

import net.minecraftforge.common.config.Configuration;
import twinrealm.TwinRealm;

/**
 * Created by lukas on 15.09.14.
 */
public class TRConfig
{
    public static void loadConfig(String configID)
    {
        if (configID == null || configID.equals(Configuration.CATEGORY_GENERAL))
        {
            
        }

        TwinRealm.proxy.loadConfig(configID);
    }
}
