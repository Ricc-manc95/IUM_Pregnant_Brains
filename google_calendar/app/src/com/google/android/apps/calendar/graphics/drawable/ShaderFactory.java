// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.graphics.Shader;

public interface ShaderFactory
{

    public abstract Shader resize(int i, int j);

    public abstract android.graphics.drawable.ShapeDrawable.ShaderFactory toAndroid();
}
