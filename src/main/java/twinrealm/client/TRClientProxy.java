package twinrealm.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import twinrealm.TRProxy;
import twinrealm.blocks.crystaldesert.CrystalOre;
import twinrealm.renderer.CrystalOreRenderer;
import twinrealm.worldgen.biomes.BiomeGenCrystalDesert;

/**
 * Created by lukas on 15.09.14.
 */
public class TRClientProxy implements TRProxy
{
    @Override
    public void registerRenderers()
    {
        new CrystalOreRenderer().registerFor(CrystalOre.class);
    }

    @Override
    public void loadConfig(String configID)
    {

    }

	@Override
	public void registerReloadListeners()
	{
		IReloadableResourceManager rm = (IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager();
		rm.registerReloadListener(new BiomeGenCrystalDesert.CrystalDesertColorizer());
	}
}
