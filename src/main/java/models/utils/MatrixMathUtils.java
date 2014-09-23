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

package models.utils;

import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

/**
 * Created by lukas on 22.09.14.
 */
public class MatrixMathUtils
{
    public static Matrix4f setTRS(Matrix4f matrix, Vector3f translation, Quaternion quaternion, Vector3f scale)
    {
        quaternion = quaternion.normalise(new Quaternion());
        return setTRS(matrix, translation.getX(), translation.getY(), translation.getZ(), quaternion.getX(), quaternion.getY(), quaternion.getZ(), quaternion.getW(), scale.getX(), scale.getY(), scale.getZ());
    }

    public static Matrix4f setTRS(Matrix4f matrix, float translationX, float translationY, float translationZ, float quaternionX, float quaternionY, float quaternionZ, float quaternionW, float scaleX, float scaleY, float scaleZ)
    {
        final float xs = quaternionX * 2f, ys = quaternionY * 2f, zs = quaternionZ * 2f;
        final float wx = quaternionW * xs, wy = quaternionW * ys, wz = quaternionW * zs;
        final float xx = quaternionX * xs, xy = quaternionX * ys, xz = quaternionX * zs;
        final float yy = quaternionY * ys, yz = quaternionY * zs, zz = quaternionZ * zs;

        matrix.m00 = scaleX * (1.0f - (yy + zz));
        matrix.m10 = scaleY * (xy - wz);
        matrix.m20 = scaleZ * (xz + wy);
        matrix.m30 = translationX;

        matrix.m01 = scaleX * (xy + wz);
        matrix.m11 = scaleY * (1.0f - (xx + zz));
        matrix.m21 = scaleZ * (yz - wx);
        matrix.m31 = translationY;

        matrix.m02 = scaleX * (xz - wy);
        matrix.m12 = scaleY * (yz + wx);
        matrix.m22 = scaleZ * (1.0f - (xx + yy));
        matrix.m32 = translationZ;

        matrix.m03 = 0.f;
        matrix.m13 = 0.f;
        matrix.m23 = 0.f;
        matrix.m33 = 1.0f;

        return matrix;
    }
}
