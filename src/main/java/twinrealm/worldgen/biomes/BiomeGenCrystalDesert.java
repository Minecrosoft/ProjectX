package twinrealm.worldgen.biomes;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.*;

import java.io.IOException;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;
import twinrealm.TwinRealm;
import twinrealm.blocks.TRBlocks;

/**
 * Created by lukas on 17.09.14.
 */
public class BiomeGenCrystalDesert extends BiomeGenBase
{
    private int bushPerChunk = 4;
    private int crystalPerChunk = 16;
    private WorldGenDeadBush genBush;
    private WorldGenMinable genCrystal;


    public BiomeGenCrystalDesert(int biomeID)
    {
        super(biomeID);

        setTemperatureRainfall(2.0F, 0.0F);
        setHeight(height_HighPlateaus);

        this.topBlock = TRBlocks.crystalSand;
        this.fillerBlock = TRBlocks.crystalSand;

        this.spawnableCreatureList.clear();

        genCrystal = new WorldGenMinable(TRBlocks.crystalOre, 8, TRBlocks.crystalRock);
        genBush = new WorldGenDeadBush(TRBlocks.deadCrystalBush);
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metadatas, int x, int z, double stoneNoise)
    {
        Block top = topBlock;
        Block filler = fillerBlock;
        byte metadata = (byte) (this.field_150604_aj & 255);

        int fillerLeft = -1;
        int fillerHeight = (int) (stoneNoise / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int chunkX = x & 15;
        int chunkZ = z & 15;
        int k1 = blocks.length / 256;

        for (int y = 255; y >= 0; --y)
        {
            int blockIndex = (chunkZ * 16 + chunkX) * k1 + y;

            if (y <= 0 + rand.nextInt(5))
            {
                blocks[blockIndex] = Blocks.bedrock;
            }
            else
            {
                Block currentBlock = blocks[blockIndex];

                if (currentBlock != null && currentBlock.getMaterial() != Material.air)
                {
                    if (currentBlock == Blocks.stone)
                    {
                        blocks[blockIndex] = TRBlocks.crystalRock;

                        //first run down
                        if (fillerLeft == -1)
                        {
                            //noise said : no filler
                            if (fillerHeight <= 0)
                            {
                                top = null;
                                metadata = 0;
                                filler = Blocks.stone;
                            }
                            else if (y >= 59 && y <= 64)
                            {
                                top = this.topBlock;
                                metadata = (byte) (this.field_150604_aj & 255);
                                filler = this.fillerBlock;
                            }

                            if (y < 63 && (top == null || top.getMaterial() == Material.air))
                            {
                                if (this.getFloatTemperature(x, y, z) < 0.15F)
                                {
                                    top = Blocks.ice;
                                    metadata = 0;
                                }
                                else
                                {
                                    top = Blocks.water;
                                    metadata = 0;
                                }
                            }

                            fillerLeft = fillerHeight;

                            if (y >= 62)
                            {
                                blocks[blockIndex] = top;
                                metadatas[blockIndex] = metadata;
                            }
                            else if (y < 56 - fillerHeight)
                            {
                                top = null;
                                filler = Blocks.stone;
                                blocks[blockIndex] = Blocks.gravel;
                            }
                            else
                            {
                                blocks[blockIndex] = filler;
                            }
                        }
                        else if (fillerLeft > 0)
                        {
                            --fillerLeft;
                            blocks[blockIndex] = filler;

                            if (fillerLeft == 0 && filler == Blocks.sand)
                            {
                                fillerLeft = rand.nextInt(4) + Math.max(0, y - 63);
                                filler = Blocks.sandstone;
                            }
                        }
                    }
                }
                else
                {
                    fillerLeft = -1;
                }
            }
        }
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ)
    {
       generateDeadBushes(world, rand, chunkX, chunkZ);

       MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(world, rand, chunkX, chunkZ));

       generateCrystals(world, rand, chunkX, chunkZ);

       MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(world, rand, chunkX, chunkZ));

    }

    private void generateCrystals(World world, Random rand, int chunkX, int chunkZ)
    {
        if (!TerrainGen.generateOre(world, rand, genCrystal, chunkX, chunkZ, EventType.CUSTOM))
            return;

        int minHeight = 0, maxHeight = 96;
        for (int i = 0; i < crystalPerChunk; i++)
        {
            int x = chunkX + rand.nextInt(16);
            int y = rand.nextInt(maxHeight - minHeight) + minHeight;
            int z = chunkZ + rand.nextInt(16);
            genCrystal.generate(world, rand, x, y, z);
        }
    }

    private void generateDeadBushes(World world, Random rand, int chunkX, int chunkZ)
    {
        //copied from BiomeDecorator.genDecorations()
        if(!TerrainGen.decorate(world, rand, chunkX, chunkZ, DEAD_BUSH))
            return;

        for (int i = 0; i < bushPerChunk; i++)
        {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = rand.nextInt(world.getHeightValue(x, z) * 2);
            genBush.generate(world, rand, x, y, z);
        }
    }

    public static class CrystalDesertColorizer implements IResourceManagerReloadListener
    {
        private static final ResourceLocation resourceLocation = new ResourceLocation(TwinRealm.MODID
                + ":textures/colormap/crystaldesert.png");
        private static int[] colors = new int[256];

        public static int getCrystalColor(int x, int y, int z)
        {
            return colors[y];
        }

        @Override
        public void onResourceManagerReload(IResourceManager rm)
        {
            try
            {
                colors = TextureUtil.readImageData(rm, resourceLocation);
            }
            catch (IOException ioexception)
            {
                ;
            }
        }

    }
}
