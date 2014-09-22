package twinrealm.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import models.*;
import models.attributes.BlendingAttribute;
import models.attributes.ColorAttribute;
import models.data.IndexData;
import models.data.VertexAttribute;
import models.data.VertexAttributes;
import models.data.VertexData;
import models.loaders.MinecraftModelLoader;
import models.utils.BufferUtils;
import models.utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.vector.Matrix4f;
import twinrealm.TwinRealm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

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
//        GL11.glPushMatrix();
//        GL11.glTranslated(event.x, event.y, event.z);
//        drawModelDirectly(model);
//        GL11.glPopMatrix();
    }

    public static void drawModelDirectly(Model model)
    {
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        for (Node node : model.nodes)
        {
            GL11.glPushMatrix();

            Matrix4f nodeTransMat = new Matrix4f();
            MathUtils.setTRS(nodeTransMat, node.translation, node.rotation, node.scale);
            FloatBuffer nodeTransMatBuffer = GLAllocation.createDirectFloatBuffer(4 * 4);
            nodeTransMat.store(nodeTransMatBuffer);
            nodeTransMatBuffer.position(0);
            GL11.glMultMatrix(nodeTransMatBuffer);

            for (NodePart nodePart : node.parts)
            {
                MeshPart meshPart = nodePart.meshPart;
                Material material = nodePart.material;

                ColorAttribute diffuse = material.get(ColorAttribute.class, ColorAttribute.Diffuse);
                float[] rgb = diffuse != null
                        ? new float[]{diffuse.color.getRed() / 255.0f, diffuse.color.getGreen() / 255.0f, diffuse.color.getBlue() / 255.0f}
                        : new float[]{1.0f, 1.0f, 1.0f};

                BlendingAttribute blend = material.get(BlendingAttribute.class, BlendingAttribute.Type);
                if (blend != null)
                {
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(blend.sourceFunction, blend.destFunction);

                    GL11.glColor4f(rgb[0], rgb[1], rgb[2], blend.opacity);
                }
                else
                    GL11.glColor3f(rgb[0], rgb[1], rgb[2]);

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

                if (blend != null)
                    GL11.glDisable(GL11.GL_BLEND);
            }

            GL11.glPopMatrix();
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
