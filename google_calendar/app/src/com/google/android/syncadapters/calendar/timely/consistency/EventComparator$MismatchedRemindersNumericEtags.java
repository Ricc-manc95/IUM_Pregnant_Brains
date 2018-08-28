// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import java.util.List;

// Referenced classes of package com.google.android.syncadapters.calendar.timely.consistency:
//            EventComparator

final class q
    implements q
{

    public final q classify(List list, Event event, Event event1)
    {
        q q = new q();
        if (list.size() != 1 || !((String)list.get(0)).equals("ETAG") || event.etag == null || event1.etag == null)
        {
            break MISSING_BLOCK_LABEL_131;
        }
        list = event.etag.replace("\"", "");
        event = event1.etag.replace("\"", "");
        long l;
        long l1;
        long l2;
        try
        {
            l = Long.parseLong(list);
            l1 = Long.parseLong(event);
            l2 = event1.updated.value;
        }
        // Misplaced declaration of an exception variable
        catch (List list)
        {
            return q;
        }
        if (l1 == l || l != 2000L * l2)
        {
            break MISSING_BLOCK_LABEL_131;
        }
        q.ass = true;
        q.t = EventComparator.wasRecentlyUpdated(event1);
        return q;
    }

    q()
    {
    }
}
