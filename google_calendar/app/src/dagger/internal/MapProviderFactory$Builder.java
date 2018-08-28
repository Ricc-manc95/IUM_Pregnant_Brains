// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package dagger.internal;

import java.util.LinkedHashMap;

public final class map
{

    public final LinkedHashMap map;

    public (int i)
    {
        if (i < 3)
        {
            i++;
        } else
        if (i < 0x40000000)
        {
            i = (int)((float)i / 0.75F + 1.0F);
        } else
        {
            i = 0x7fffffff;
        }
        map = new LinkedHashMap(i);
    }
}
