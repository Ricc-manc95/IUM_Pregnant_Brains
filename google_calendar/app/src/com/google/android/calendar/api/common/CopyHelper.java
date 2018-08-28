// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CopyHelper
{

    public static List copyArrayToList(int ai[])
    {
        if (ai == null || ai.length == 0)
        {
            return Collections.emptyList();
        }
        ArrayList arraylist = new ArrayList(ai.length);
        for (int i = 0; i < ai.length; i++)
        {
            arraylist.add(Integer.valueOf(ai[i]));
        }

        return arraylist;
    }

    public static List copyArrayToList(long al[])
    {
        if (al == null || al.length == 0)
        {
            return Collections.emptyList();
        }
        ArrayList arraylist = new ArrayList(al.length);
        for (int i = 0; i < al.length; i++)
        {
            arraylist.add(Long.valueOf(al[i]));
        }

        return arraylist;
    }

    public static long[] copyListToArrayOrNull(List list)
    {
        if (list == null || list.isEmpty())
        {
            return null;
        }
        long al[] = new long[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            al[i] = ((Long)list.get(i)).longValue();
        }

        return al;
    }
}
