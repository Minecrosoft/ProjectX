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

package models.loaders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Mesh;
import models.MeshPart;
import models.Model;
import models.data.VertexAttribute;
import models.data.VertexAttributes;
import models.loaders.data.ColorDeserializer;
import models.loaders.data.QuaternionDeserializer;
import models.loaders.data.Vector2fDeserializer;
import models.loaders.data.Vector3fDeserializer;
import models.loaders.g3draw.RawMesh;
import models.loaders.g3draw.RawMeshPart;
import models.loaders.g3draw.RawModel;
import models.utils.BufferUtils;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.awt.*;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lukas on 21.09.14.
 */
public class G3DModelLoader implements ModelLoader
{
    public static final short VERSION_HI = 0;
    public static final short VERSION_LO = 1;

    private Logger logger;
    private Gson gson;

    public G3DModelLoader(Logger logger)
    {
        this.logger = logger;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Color.class, new ColorDeserializer());
        gsonBuilder.registerTypeAdapter(Quaternion.class, new QuaternionDeserializer());
        gsonBuilder.registerTypeAdapter(Vector2f.class, new Vector2fDeserializer());
        gsonBuilder.registerTypeAdapter(Vector3f.class, new Vector3fDeserializer());
        gson = gsonBuilder.create();
    }

    @Override
    public Model createModel(Reader reader)
    {
        RawModel rawModel = gson.fromJson(reader, RawModel.class);

        return modelFromRawModel(rawModel, logger);
    }

    public static Model modelFromRawModel(RawModel rawModel, Logger logger)
    {
        if (rawModel.version == null)
            rawModel.version = new short[]{VERSION_HI, VERSION_LO};

        if (rawModel.version[0] != VERSION_HI || rawModel.version[1] != VERSION_LO)
        {
            logger.error("Version number of g3d file unknown");
            return null;
        }

        Model model = new Model();
        loadMeshes(model, Arrays.asList(rawModel.meshes));
        return model;
    }

    private static void loadMeshes(Model model, Iterable<RawMesh> meshes)
    {
        for (RawMesh rawMesh : meshes)
        {
            Mesh mesh = convertMesh(rawMesh, model.meshParts);

            model.meshes.add(mesh);
            model.disposables.add(mesh);
        }
    }

    public static Mesh convertMesh(RawMesh modelMesh, List<MeshPart> meshParts)
    {
        int numIndices = 0;
        for (RawMeshPart part : modelMesh.parts)
        {
            numIndices += part.indices.length;
        }
        VertexAttributes attributes = new VertexAttributes(parseAttributes(modelMesh.attributes));
        int numVertices = modelMesh.vertices.length / (attributes.vertexSize / 4);

        Mesh mesh = new Mesh(true, numVertices, numIndices, attributes);

        BufferUtils.copy(modelMesh.vertices, mesh.getVerticesBuffer(), modelMesh.vertices.length, 0);
        int offset = 0;
        mesh.getIndicesBuffer().clear();
        for (RawMeshPart part : modelMesh.parts)
        {
            MeshPart meshPart = new MeshPart();
            meshPart.id = part.id;
            meshPart.primitiveType = parseGLType(part.type);
            meshPart.indexOffset = offset;
            meshPart.numVertices = part.indices.length;
            meshPart.mesh = mesh;
            mesh.getIndicesBuffer().put(part.indices);
            offset += meshPart.numVertices;
            meshParts.add(meshPart);
        }
        mesh.getIndicesBuffer().position(0);
        return mesh;
    }

    private static int parseGLType(String type)
    {
        if (type.equals("TRIANGLES"))
        {
            return GL11.GL_TRIANGLES;
        }
        else if (type.equals("LINES"))
        {
            return GL11.GL_LINES;
        }
        else if (type.equals("POINTS"))
        {
            return GL11.GL_POINTS;
        }
        else if (type.equals("TRIANGLE_STRIP"))
        {
            return GL11.GL_TRIANGLE_STRIP;
        }
        else if (type.equals("LINE_STRIP"))
        {
            return GL11.GL_LINE_STRIP;
        }
        else
        {
            throw new RuntimeException("Unknown primitive type '" + type
                    + "', should be one of triangle, trianglestrip, line, linestrip, lineloop or point");
        }
    }

    public static VertexAttribute[] parseAttributes(String[] attributes)
    {
        List<VertexAttribute> vertexAttributes = new ArrayList<>();
        int unit = 0;
        int blendWeightCount = 0;
        for (String attribute : attributes)
        {
            if (attribute.equals("POSITION"))
                vertexAttributes.add(VertexAttribute.Position());
            else if (attribute.equals("NORMAL"))
                vertexAttributes.add(VertexAttribute.Normal());
            else if (attribute.equals("COLOR"))
                vertexAttributes.add(VertexAttribute.ColorUnpacked());
            else if (attribute.equals("COLORPACKED"))
                vertexAttributes.add(VertexAttribute.ColorPacked());
            else if (attribute.equals("TANGENT"))
                vertexAttributes.add(VertexAttribute.Tangent());
            else if (attribute.equals("BINORMAL"))
                vertexAttributes.add(VertexAttribute.Binormal());
            else if (attribute.startsWith("TEXCOORD"))
                vertexAttributes.add(VertexAttribute.TexCoords(unit++));
            else if (attribute.startsWith("BLENDWEIGHT"))
                vertexAttributes.add(VertexAttribute.BoneWeight(blendWeightCount++));
            else
                throw new RuntimeException("Unknown vertex attribute '" + attribute
                        + "', should be one of position, normal, uv, tangent or binormal");
        }
        return vertexAttributes.toArray(new VertexAttribute[vertexAttributes.size()]);
    }
}
