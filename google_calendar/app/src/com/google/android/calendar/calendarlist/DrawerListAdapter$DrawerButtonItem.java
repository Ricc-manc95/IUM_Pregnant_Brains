// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;


public class iconId
    implements com.google.android.calendar.calendarlist.common.nfo
{

    public final int iconId;
    public final int id;
    public final int labelId;

    public final int getOrder()
    {
        return 0;
    }

    public int getType()
    {
        return 6;
    }

    public int getViewType()
    {
        return 3;
    }

    public stItemInfo(int i, int j, int k)
    {
        id = i;
        labelId = j;
        iconId = k;
    }
}
