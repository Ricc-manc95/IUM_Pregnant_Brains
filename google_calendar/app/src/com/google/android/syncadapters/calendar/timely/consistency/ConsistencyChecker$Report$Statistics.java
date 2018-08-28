// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import com.google.api.client.json.GenericJson;

final class failedEvents extends GenericJson
{

    public Long checkedEvents;
    public Long eventsToReport;
    public Long failedEvents;
    public Long inconsistentEvents;
    public Long overfetchedBackendEvents;
    public Long overfetchedClientEvents;
    public Long skippedEvents;

    ()
    {
        checkedEvents = Long.valueOf(0L);
        skippedEvents = Long.valueOf(0L);
        inconsistentEvents = Long.valueOf(0L);
        eventsToReport = Long.valueOf(0L);
        overfetchedClientEvents = Long.valueOf(0L);
        overfetchedBackendEvents = Long.valueOf(0L);
        failedEvents = Long.valueOf(0L);
    }
}
