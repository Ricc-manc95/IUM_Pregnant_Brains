// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import com.google.api.client.json.GenericJson;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.syncadapters.calendar.timely.consistency:
//            ConsistencyChecker

static final class Requests extends GenericJson
{

    public String account;
    public String appVersion;
    public Long deviceDate;
    public Long feedUpdatedTime;
    public final List inconsistencies = new ArrayList();
    public final Requests requests = new Requests();
    public final Statistics statistics = new Statistics();

    Requests()
    {
        class Statistics extends GenericJson
        {

            public Long checkedEvents;
            public Long eventsToReport;
            public Long failedEvents;
            public Long inconsistentEvents;
            public Long overfetchedBackendEvents;
            public Long overfetchedClientEvents;
            public Long skippedEvents;

            Statistics()
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

        deviceDate = Long.valueOf(-1L);
        feedUpdatedTime = Long.valueOf(-1L);
        class Requests
        {

            public List backendRequests;
            public List providerRequests;

            Requests()
            {
            }
        }

    }
}
