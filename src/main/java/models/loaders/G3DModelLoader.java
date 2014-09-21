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
import models.Model;
import models.loaders.data.ColorDeserializer;
import models.loaders.data.QuaternionDeserializer;
import models.loaders.data.Vector2fDeserializer;
import models.loaders.data.Vector3fDeserializer;
import models.loaders.g3draw.RawModel;
import org.apache.logging.log4j.Logger;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.awt.*;
import java.io.Reader;

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
        return model;
    }
}
