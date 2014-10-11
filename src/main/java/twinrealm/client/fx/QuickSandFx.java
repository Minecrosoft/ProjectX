package twinrealm.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import twinrealm.TwinRealm;
import twinrealm.blocks.TRBlocks;

/**
 * @author Ordinastie
 *
 */
public class QuickSandFx extends EntityFX
{
    private static ResourceLocation rl = new ResourceLocation(TwinRealm.MODID, "textures/fx/mudbubble.png");

    public QuickSandFx(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.particleMaxAge = 22;

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
    }

    @Override
    public void renderParticle(Tessellator t, float partialTick, float rotationX, float rotationXZ, float rotationZ, float rotationYZ, float rotationXY)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(rl);
        float scale = 0.2F;

        float u = 0;
        float U = 1;
        float v = (float) (particleAge / 2) / 11;
        float V = (float) (particleAge / 2 + 1) / 11;

        float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * rotationX - interpPosX);
        float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * rotationX - interpPosY);
        float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * rotationX - interpPosZ);
        //0.2 is needed for the bubble to appear at ground level
        y += 0.2F;

        t.setColorRGBA_I(TRBlocks.quickSand.colorMultiplier(null, (int) posX, (int) posY, (int) posZ), 0xFF);

        t.addVertexWithUV(x - rotationX * scale - rotationYZ * scale, y - rotationXZ * scale, z - rotationZ * scale - rotationXY * scale, U, V);
        t.addVertexWithUV(x - rotationX * scale + rotationYZ * scale, y + rotationXZ * scale, z - rotationZ * scale + rotationXY * scale, U, v);
        t.addVertexWithUV(x + rotationX * scale + rotationYZ * scale, y + rotationXZ * scale, z + rotationZ * scale + rotationXY * scale, u, v);
        t.addVertexWithUV(x + rotationX * scale - rotationYZ * scale, y - rotationXZ * scale, z + rotationZ * scale - rotationXY * scale, u, V);

        t.draw();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        t.startDrawingQuads();

    }

    public void spawn()
    {
        Minecraft.getMinecraft().effectRenderer.addEffect(this);
    }

    @Override
    public int getFXLayer()
    {
        return 1;
    }
}
