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

import models.data.Disposable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 21.09.14.
 */
public class Model
{
    public final List<Material> materials = new ArrayList<>();
    public final List<Node> nodes = new ArrayList<>();
    public final List<Animation> animations = new ArrayList<>();
    public final List<Mesh> meshes = new ArrayList<>();
    public final List<MeshPart> meshParts = new ArrayList<>();

    public final List<Disposable> disposables = new ArrayList<>();

    public Animation animationForID(String id)
    {
        for (Animation animation : animations)
        {
            if (animation.id.equals(id))
                return animation;
        }

        return null;
    }
}