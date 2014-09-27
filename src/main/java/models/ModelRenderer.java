/*
 * Notice: This is a modified version of a libgdx file. See https://github.com/libgdx/libgdx for the original work.
 *
 * Copyright 2011 See libgdx AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package models;

import models.attributes.BlendingAttribute;
import models.attributes.ColorAttribute;
import models.attributes.TextureAttribute;
import models.data.IndexData;
import models.data.VertexAttribute;
import models.data.VertexAttributes;
import models.data.VertexData;
import models.utils.MatrixMathUtils;
import net.minecraft.client.renderer.GLAllocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by lukas on 22.09.14.
 */
public class ModelRenderer
{
    private static final float[] WHITE = new float[]{1.0f, 1.0f, 1.0f};

    public static void renderModelDirectly(Model model)
    {
        for (Node node : model.nodes)
        {
            GL11.glPushMatrix();

            Matrix4f nodeTransMat = new Matrix4f();
            MatrixMathUtils.setTRS(nodeTransMat, node.translation, node.rotation, node.scale);
            FloatBuffer nodeTransMatBuffer = GLAllocation.createDirectFloatBuffer(4 * 4);
            nodeTransMat.store(nodeTransMatBuffer);
            nodeTransMatBuffer.position(0);
            GL11.glMultMatrix(nodeTransMatBuffer);

            for (NodePart nodePart : node.parts)
            {
                MeshPart meshPart = nodePart.meshPart;
                Material material = nodePart.material;

                Mesh mesh = meshPart.mesh;
                IndexData indexData = mesh.getIndices();
                ShortBuffer indexBuf = indexData.getBuffer();

                VertexData vertexData = mesh.getVertices();
                FloatBuffer vertexBuf = vertexData.getBuffer();

                VertexAttributes vertexAttributes = vertexData.getAttributes();
                VertexAttribute posAttr = vertexAttributes.findByUsage(VertexAttributes.Usage.Position);
                int vertexLengthInFloats = vertexAttributes.vertexSize >> 2;

                VertexAttribute textureCoordAttr = null;

                float[] rgb = WHITE;
                Texture texture = null;
                float[] uvs = null;

                if (material.has(TextureAttribute.Diffuse))
                {
                    TextureAttribute textureAttr = material.get(TextureAttribute.class, TextureAttribute.Diffuse);
                    texture = textureAttr.texture;

                    textureCoordAttr = vertexAttributes.findByUsageAndUnit(VertexAttributes.Usage.TextureCoordinates, 0);
                    if (textureCoordAttr == null)
                        uvs = guessUVs(meshPart.primitiveType, texture, meshPart.numVertices);
                }
                else if (material.has(ColorAttribute.Diffuse))
                {
                    // Note that a texture replaces the color diffuse

                    ColorAttribute diffuse = material.get(ColorAttribute.class, ColorAttribute.Diffuse);
                    rgb = new float[]{diffuse.color.getRed() / 255.0f, diffuse.color.getGreen() / 255.0f, diffuse.color.getBlue() / 255.0f};
                }

                BlendingAttribute blend = material.get(BlendingAttribute.class, BlendingAttribute.Type);
                if (blend != null)
                {
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(blend.sourceFunction, blend.destFunction);

                    GL11.glColor4f(rgb[0], rgb[1], rgb[2], blend.opacity);
                }
                else
                    GL11.glColor3f(rgb[0], rgb[1], rgb[2]);

                if (texture != null)
                    texture.bindTexture();
                else
                    GL11.glDisable(GL11.GL_TEXTURE_2D);

                GL11.glBegin(meshPart.primitiveType);
                for (int i = meshPart.indexOffset; i < meshPart.numVertices + meshPart.indexOffset; i++)
                {
                    int vertexIndex = indexBuf.get(i) * vertexLengthInFloats;

                    if (textureCoordAttr != null)
                    {
                        int textureIndex = vertexIndex + (textureCoordAttr.offset >> 2);
                        GL11.glTexCoord2f(vertexBuf.get(textureIndex), vertexBuf.get(textureIndex + 1));
                    }
                    else if (uvs != null)
                        GL11.glTexCoord2f(uvs[i * 2], uvs[i * 2 + 1]);

                    int posIndex = vertexIndex + (posAttr.offset >> 2);
                    GL11.glVertex3f(vertexBuf.get(posIndex), vertexBuf.get(posIndex + 1), vertexBuf.get(posIndex + 2));
                }
                GL11.glEnd();

                if (texture == null)
                    GL11.glEnable(GL11.GL_TEXTURE_2D);

                if (blend != null)
                    GL11.glDisable(GL11.GL_BLEND);
            }

            GL11.glPopMatrix();
        }
    }

    public static float[] guessUVs(int primitiveType, Texture texture, int length)
    {
        float[] uvs = new float[length * 2];

        for (int i = 0; i < uvs.length / 2; i++) // Go through all combinations... Works for quads, the rest at least gets some variety
        {
            switch (i % 4)
            {
                case 0:
                    uvs[i * 2] = texture.minU();
                    uvs[i * 2 + 1] = texture.minV();
                    break;
                case 1:
                    uvs[i * 2] = texture.maxU();
                    uvs[i * 2 + 1] = texture.minV();
                    break;
                case 2:
                    uvs[i * 2] = texture.maxU();
                    uvs[i * 2 + 1] = texture.maxV();
                    break;
                case 3:
                    uvs[i * 2] = texture.minU();
                    uvs[i * 2 + 1] = texture.maxV();
                    break;
            }
        }

        return uvs;
    }
}
