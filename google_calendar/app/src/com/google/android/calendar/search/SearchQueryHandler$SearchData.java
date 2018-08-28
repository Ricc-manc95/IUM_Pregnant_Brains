// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import com.google.android.calendar.timely.RangedData;

final class token
    implements RangedData
{

    private int token;

    public final boolean containsDay(int i)
    {
        return true;
    }

    public final String getDebugTag()
    {
        return "[2440588-2465067]";
    }

    public final int getEndDay()
    {
        return 0x259d2b;
    }

    public final int getStartDay()
    {
        return 0x253d8c;
    }

    public final int getToken()
    {
        return token;
    }

    public final void recycle(int i)
    {
    }

    public final void setToken(int i)
    {
        token = i;
    }

    ()
    {
        token = -1;
    }
}
