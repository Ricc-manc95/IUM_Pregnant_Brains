// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterEvent

public abstract class AdapterDay
{

    public AdapterDay()
    {
    }

    public abstract ImmutableList getAllDayEvents();

    public abstract int getAllDayOverflowPosition();

    public abstract int getCacheGeneration();

    public abstract int getJulianDay();

    public abstract boolean getLoaded();

    public int getNumAllDayRows()
    {
        int j = 0;
        ImmutableList immutablelist = (ImmutableList)getAllDayEvents();
        int k = immutablelist.size();
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
        Object obj;
        int i;
        for (i = 0; j < k; i = Math.max(i, ((AdapterEvent)obj).getGridAllDaySlot().intValue() + 1))
        {
            obj = immutablelist.get(j);
            j++;
        }

        return i;
    }

    public abstract ImmutableList getTimedEvents();
}
