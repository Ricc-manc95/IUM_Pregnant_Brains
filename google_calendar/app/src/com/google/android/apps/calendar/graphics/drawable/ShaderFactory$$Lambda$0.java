// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.graphics.Shader;

// Referenced classes of package com.google.android.apps.calendar.graphics.drawable:
//            ShaderFactory

public final class arg._cls1
    implements ShaderFactory
{

    private final Shader arg$1;

    public final Shader resize(int i, int j)
    {
        return arg$1;
    }

    public final android.graphics.drawable.ory toAndroid()
    {
        return new arg._cls1(this);
    }

    public (Shader shader)
    {
        arg$1 = shader;
    }
}
