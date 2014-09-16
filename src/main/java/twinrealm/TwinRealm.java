package twinrealm;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;
import twinrealm.configuration.TRConfig;
import twinrealm.events.TREventHandlerFML;
import twinrealm.events.TREventHandlerForge;
import twinrealm.events.TREventHandlerTerrain;
import twinrealm.server.commands.CommandSetDimension;
import twinrealm.worldgen.TRDimensions;
import twinrealm.worldgen.WorldProviderTwinRealm;

/**
 * Created by lukas on 15.09.14.
 */
@Mod(modid = TwinRealm.MODID, version = TwinRealm.VERSION, name = TwinRealm.NAME, guiFactory = "twinrealm.gui.TRConfigGuiFactory")
public class TwinRealm
{
    public static final String NAME = "Twin Realm";
    public static final String MODID = "trealm";
    public static final String VERSION = "0.0.1";

    @Mod.Instance(value = MODID)
    public static TwinRealm instance;

    public static Logger logger;

    public static Configuration config;

    @SidedProxy(clientSide = "twinrealm.client.TRClientProxy", serverSide = "twinrealm.server.TRServerProxy")
    public static TRProxy proxy;

    public static TREventHandlerForge eventHandlerForge;
    public static TREventHandlerFML eventHandlerFML;
    public static TREventHandlerTerrain eventHandlerTerrain;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        TRConfig.loadConfig(null);
        config.save();

        eventHandlerForge = new TREventHandlerForge();
        eventHandlerForge.register();

        eventHandlerFML = new TREventHandlerFML();
        eventHandlerFML.register();

        eventHandlerTerrain = new TREventHandlerTerrain();
        eventHandlerTerrain.register();

        TRDimensions.twinRealm = DimensionManager.getNextFreeDimId();
        DimensionManager.registerProviderType(TRDimensions.twinRealmProviderType, WorldProviderTwinRealm.class, true);
        DimensionManager.registerDimension(TRDimensions.twinRealm, TRDimensions.twinRealmProviderType);
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandSetDimension());
    }
}