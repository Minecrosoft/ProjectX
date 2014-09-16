package twinrealm.worldgen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;

/**
 * Created by lukas on 16.09.14.
 */
public class GenLayerTwinRealm
{
    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType)
    {
        boolean flag = false;
        GenLayerIsland genlayerisland = new GenLayerIsland(1L);
        GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
        GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(50L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(70L, genlayeraddisland);
        GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);

//        GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
//        genlayeraddisland = new GenLayerAddIsland(3L, genlayeraddsnow);
        genlayeraddisland = new GenLayerAddIsland(3L, genlayerremovetoomuchocean);

        GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland, GenLayerEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
        genlayerzoom = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(4L, genlayerzoom);

//        GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
//        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddisland);

        GenLayer genlayer2 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        byte b0 = 4;

        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer2, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
        GenLayer aGenLayer = worldType.getBiomeLayer(seed, genlayer2);

        GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayerHills genlayerhills = new GenLayerHills(1000L, aGenLayer, genlayer1);
        genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer = GenLayerZoom.magnify(1000L, genlayer, b0);
        GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        aGenLayer = new GenLayerRareBiome(1001L, genlayerhills);

        for (int j = 0; j < b0; ++j)
        {
            aGenLayer = new GenLayerZoom((long)(1000 + j), aGenLayer);

            if (j == 0)
            {
                aGenLayer = new GenLayerAddIsland(3L, aGenLayer);
            }

            if (j == 1)
            {
                aGenLayer = new GenLayerShore(1000L, aGenLayer);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, aGenLayer);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);
        return new GenLayer[] {genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
    }
}
