package twinrealm.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import models.Mesh;
import models.MeshPart;
import models.Model;
import models.data.IndexData;
import models.data.VertexAttribute;
import models.data.VertexAttributes;
import models.data.VertexData;
import models.loaders.MinecraftModelLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import twinrealm.TwinRealm;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by lukas on 15.09.14.
 */
public class TREventHandlerForge
{
    Model model;

    public TREventHandlerForge()
    {
        model = MinecraftModelLoader.loadModelG3DJ(new ResourceLocation(TwinRealm.MODID, "models/Barrel.g3dj"));
    }

    public void register()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderLiving(RenderLivingEvent.Pre event)
    {
//        event.setCanceled(true);
//
//        drawModelDirectly(model);
    }

    public static void drawModelDirectly(Model model)
    {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(0.0f, 1.0f, 1.0f);
////        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/entity/chicken.png"));
//        new ModelChicken().render(event.entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);

        for (MeshPart meshPart : model.meshParts)
        {
            Mesh mesh = meshPart.mesh;
            IndexData indexData = mesh.getIndices();
            ShortBuffer indexBuf = indexData.getBuffer();

            VertexData vertexData = mesh.getVertices();
            FloatBuffer vertexBuf = vertexData.getBuffer();

            VertexAttributes vertexAttributes = vertexData.getAttributes();
            VertexAttribute posAttr = vertexAttributes.findByUsage(VertexAttributes.Usage.Position);
            int vertexLengthInFloats = vertexAttributes.vertexSize >> 2;

            GL11.glBegin(meshPart.primitiveType);
            for (int i = meshPart.indexOffset; i < meshPart.numVertices + meshPart.indexOffset; i++)
            {
                int index = indexBuf.get(i) * vertexLengthInFloats + posAttr.offset;
                GL11.glVertex3f(vertexBuf.get(index), vertexBuf.get(index + 1), vertexBuf.get(index + 2));
            }
            GL11.glEnd();
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
