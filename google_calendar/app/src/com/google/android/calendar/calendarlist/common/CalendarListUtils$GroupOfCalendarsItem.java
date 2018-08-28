// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist.common;

import java.util.ArrayList;

public class areVisible
    implements areVisible
{

    public boolean areVisible;
    public final ArrayList calendars = new ArrayList();
    private final int order;
    private final int type;

    public final int getOrder()
    {
        return order;
    }

    public final int getType()
    {
        return type;
    }

    public final int getViewType()
    {
        return 2;
    }

    (int i, int j)
    {
        type = j;
        order = i;
        areVisible = false;
    }
}
