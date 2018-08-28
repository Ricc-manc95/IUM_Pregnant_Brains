// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics;

import android.graphics.LinearGradient;
import android.graphics.Shader;

public final class Shaders
{

    public static Shader color(int i)
    {
        android.graphics.Shader.TileMode tilemode = android.graphics.Shader.TileMode.CLAMP;
        return new LinearGradient(0.0F, 0.0F, 1.0F, 1.0F, new int[] {
            i, i
        }, new float[] {
            0.0F, 1.0F
        }, tilemode);
    }
}
