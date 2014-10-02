package twinrealm.client;

import twinrealm.TRProxy;
import twinrealm.blocks.crystaldesert.CrystalOre;
import twinrealm.renderer.CrystalOreRenderer;

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

}
