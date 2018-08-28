// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterEvent

public abstract class AdapterMonthDay
{

    public AdapterMonthDay()
    {
    }

    public abstract ImmutableList getEvents();

    public abstract boolean getLoaded();

    public abstract int getMonthDayHeaderPosition();

    public int getNumRows()
    {
        int j = 0;
        ImmutableList immutablelist = (ImmutableList)getEvents();
        int k = immutablelist.size();
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
        Object obj;
        int i;
        for (i = 0; j < k; i = Math.max(i, ((AdapterEvent)obj).getMonthSlot() + 1))
        {
            obj = immutablelist.get(j);
            j++;
        }

        return i;
    }
}
