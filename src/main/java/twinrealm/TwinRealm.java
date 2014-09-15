package twinrealm;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * Created by lukas on 15.09.14.
 */
@Mod(modid = TwinRealm.MODID, version = TwinRealm.VERSION, name = TwinRealm.NAME)
public class TwinRealm
{
    public static final String NAME = "Twin Realm";
    public static final String MODID = "trealm";
    public static final String VERSION = "0.0.1";

    @Mod.Instance(value = MODID)
    public static TwinRealm instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
