// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzaQI
{

    private final Object zzXy;
    private final List zzaQI;

    public final String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder(100)).append(zzXy.getClass().getSimpleName()).append('{');
        int j = zzaQI.size();
        for (int i = 0; i < j; i++)
        {
            stringbuilder.append((String)zzaQI.get(i));
            if (i < j - 1)
            {
                stringbuilder.append(", ");
            }
        }

        return stringbuilder.append('}').toString();
    }

    public final zzaQI zzh(String s, Object obj)
    {
        List list = zzaQI;
        if (s == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            s = (String)s;
            obj = String.valueOf(String.valueOf(obj));
            list.add((new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(obj).length())).append(s).append("=").append(((String) (obj))).toString());
            return this;
        }
    }

    public (Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            zzXy = obj;
            zzaQI = new ArrayList();
            return;
        }
    }
}
