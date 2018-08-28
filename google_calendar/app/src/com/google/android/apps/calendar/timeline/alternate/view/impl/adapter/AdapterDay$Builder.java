// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterDay

abstract class 
{

    abstract AdapterDay build();

    abstract ImmutableList getAllDayEvents();

    abstract  setAllDayEvents(ImmutableList immutablelist);

    abstract  setAllDayOverflowPosition(int i);

    abstract  setCacheGeneration(int i);

    abstract  setJulianDay(int i);

    abstract  setLoaded(boolean flag);

    abstract  setTimedEvents(ImmutableList immutablelist);

    ()
    {
    }
}
