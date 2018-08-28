// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.utils;

import android.text.TextUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.timely.TimelineEvent;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.newapi.utils:
//            SmartMailUtils

public final class LegacyUtils
{

    public static int convertVisibility(int i)
    {
        switch (i)
        {
        default:
            return 0;

        case 100: // 'd'
            return 3;

        case 1: // '\001'
        case 2: // '\002'
            return 2;

        case 3: // '\003'
            return 1;
        }
    }

    public static boolean isLegacyLocationOrEmpty(EventLocation eventlocation)
    {
        return eventlocation.address == null && eventlocation.geo == null && TextUtils.isEmpty(eventlocation.mapsClusterId) && TextUtils.isEmpty(eventlocation.placeId) && !eventlocation.serverGeocoded && TextUtils.isEmpty(eventlocation.url);
    }

    public static void updateTimelineEvent(Event event, String s, TimelineEvent timelineevent)
    {
        timelineevent.title = event.getSummary();
        timelineevent.hasImageData = false;
        if (!SmartMailUtils.isSupportedSmartMailType(event.getSmartMailInfo())) goto _L2; else goto _L1
_L1:
        timelineevent.hasImageData = true;
_L4:
        return;
_L2:
        timelineevent.location = s;
        if (TextUtils.isEmpty(timelineevent.location))
        {
            continue; /* Loop/switch isn't completed */
        }
        event = event.getLocation().getEventLocations().iterator();
        do
        {
            if (!event.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            s = (EventLocation)event.next();
            boolean flag;
            if (isLegacyLocationOrEmpty(s) && !TextUtils.isEmpty(((EventLocation) (s)).name))
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } while (flag || TextUtils.isEmpty(((EventLocation) (s)).name) && TextUtils.isEmpty(((EventLocation) (s)).url));
        break; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
        timelineevent.hasImageData = true;
        return;
    }
}
