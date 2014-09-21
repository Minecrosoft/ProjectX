package twinrealm.client;

import models.Model;
import models.loaders.MinecraftModelLoader;
import net.minecraft.util.ResourceLocation;
import twinrealm.TRProxy;
import twinrealm.TwinRealm;

/**
 * Created by lukas on 15.09.14.
 */
public class TRClientProxy implements TRProxy
{
    @Override
    public void registerRenderers()
    {
        Model model = MinecraftModelLoader.loadModelG3DJ(new ResourceLocation(TwinRealm.MODID, "models/Sphere.g3dj"));
        System.out.println("model = " + model);
    }

    @Override
    public void loadConfig(String configID)
    {

    }
}
