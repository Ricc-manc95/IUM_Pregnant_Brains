// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.timebox;

import com.google.android.apps.calendar.timebox.EventsApi;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemProvider;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.util.concurrent.FluentFuture;

public final class TimeBoxItemProvider
    implements ItemProvider
{

    private final EventsApi api;
    private final ObservableReference hideDeclinedEvents;

    public TimeBoxItemProvider(EventsApi eventsapi, ObservableReference observablereference)
    {
        api = eventsapi;
        hideDeclinedEvents = observablereference;
    }

    public final FluentFuture loadItems(int i, int j)
    {
        return api.getAsync(i, j, ((Boolean)hideDeclinedEvents.get()).booleanValue());
    }
}
