package twinrealm.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import models.Animation;
import models.Model;
import models.ModelRenderer;
import models.animation.Animator;
import models.loaders.MinecraftModelLoader;
import models.textures.MinecraftIconProvider;
import models.textures.MinecraftTextureProvider;
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
//        if (model == null)
//        {
//            model = MinecraftModelLoader.loadModelG3DJ(new ResourceLocation(TwinRealm.MODID, "models/SquishCube.g3dj"),
//                    new MinecraftTextureProvider(TwinRealm.MODID + TwinRealm.pathModTextures));
//
//            Animation animation = model.animationForID("Cube|CubeAction");
//            if (animation != null)
//                animator = new Animator(animation, true);
//        }
//
//        if (animator != null)
//            animator.update(event.entity.ticksExisted * 2500.0f / 20.0f, 1.0f);
//
//        GL11.glDisable(GL11.GL_LIGHTING);
//        GL11.glPushMatrix();
//        GL11.glTranslated(event.x, event.y, event.z);
//        ModelRenderer.renderModelDirectly(model);
//        GL11.glPopMatrix();
//        GL11.glEnable(GL11.GL_LIGHTING);
//    }
}
