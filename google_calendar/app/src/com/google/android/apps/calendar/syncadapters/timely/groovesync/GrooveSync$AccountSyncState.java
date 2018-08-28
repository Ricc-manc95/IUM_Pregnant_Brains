// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.syncadapters.timely.groovesync;

import com.google.api.client.json.GenericJson;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.syncadapters.timely.groovesync:
//            GrooveSync

public static class calendars extends GenericJson
{
    public static final class CalendarSyncState extends GenericJson
    {

        public Long lastSynced;
        public String nextSyncToken;
        public int version;

        public CalendarSyncState()
        {
            version = 1;
            super.jsonFactory = GrooveSync.JSON_FACTORY;
        }
    }


    public Map calendars;
    public int version;

    public CalendarSyncState.version()
    {
        version = 1;
        calendars = new HashMap();
        super.jsonFactory = GrooveSync.JSON_FACTORY;
    }
}
