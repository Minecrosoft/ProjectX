package twinrealm.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import models.Model;
import models.ModelRenderer;
import models.animation.Animator;
import models.loaders.MinecraftModelLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import twinrealm.TwinRealm;

/**
 * Created by lukas on 15.09.14.
 */
public class TREventHandlerForge
{
    Model model;
    Animator animator;

    public TREventHandlerForge()
    {
        model = MinecraftModelLoader.loadModelG3DJ(new ResourceLocation(TwinRealm.MODID, "models/SquishCube.g3dj"), TwinRealm.textureBase + TwinRealm.pathModTextures);
        animator = new Animator(model.animationForID("Default Take"), true);
    }

    public void register()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

//    @SideOnly(Side.CLIENT)
//    @SubscribeEvent
//    public void onRenderLiving(RenderLivingEvent.Pre event)
//    {
//        event.setCanceled(true);
//
//        GL11.glDisable(GL11.GL_LIGHTING);
//        GL11.glPushMatrix();
//        GL11.glTranslated(event.x, event.y, event.z);
//        animator.update(event.entity.ticksExisted * 2500.0f / 20.0f, 1.0f);
//        ModelRenderer.renderModelDirectly(model);
//        GL11.glPopMatrix();
//        GL11.glEnable(GL11.GL_LIGHTING);
//    }
}
