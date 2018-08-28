// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import com.google.api.services.calendar.model.Event;
import java.util.List;

// Referenced classes of package com.google.android.syncadapters.calendar.timely.consistency:
//            EventComparator

final class esult
    implements ass
{

    public final esult classify(List list, Event event, Event event1)
    {
        esult esult = new esult();
        if (list.size() == 1 && ((String)list.get(0)).equals("ETAG") && event.etag == null)
        {
            esult.belongsToClass = true;
            esult.shouldReport = EventComparator.wasRecentlyUpdated(event1);
        }
        return esult;
    }

    esult()
    {
    }
}
