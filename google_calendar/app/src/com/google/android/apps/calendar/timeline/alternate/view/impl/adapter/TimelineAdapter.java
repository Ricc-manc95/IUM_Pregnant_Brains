// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterDay, AdapterEvent, AdapterMonth, AdapterWeek

public interface TimelineAdapter
{

    public abstract ObservableReference getDataSetChangedObservable();

    public abstract AdapterDay getDay(int i);

    public abstract AdapterEvent getEvent(int i);

    public abstract int getEventPosition(AdapterEvent adapterevent, int i);

    public abstract AdapterMonth getMonth(YearMonth yearmonth);

    public abstract AdapterWeek getWeek(int i);
}
