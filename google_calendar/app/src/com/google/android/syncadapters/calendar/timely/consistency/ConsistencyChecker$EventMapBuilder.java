// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import com.google.api.services.calendar.model.Event;
import com.google.common.base.Platform;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.syncadapters.calendar.timely.consistency:
//            ConsistencyChecker

public static final class fetcher
    implements Callable
{

    private final Callable fetcher;

    public final Object call()
        throws Exception
    {
        List list = (List)fetcher.call();
        if (list == null)
        {
            return null;
        }
        HashMap hashmap = new HashMap();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) 
        {
            Event event = (Event)iterator.next();
            String s;
            if (Platform.stringIsNullOrEmpty(event.recurringEventId))
            {
                s = String.valueOf("E");
                String s1 = String.valueOf(event.id);
                if (s1.length() != 0)
                {
                    s = s.concat(s1);
                } else
                {
                    s = new String(s);
                }
            } else
            {
                long l = ConsistencyChecker.getUtcMillis(event.originalStartTime);
                s = event.recurringEventId;
                s = (new StringBuilder(String.valueOf(s).length() + 22)).append("I").append(l).append("E").append(s).toString();
            }
            hashmap.put(s, event);
        }
        return hashmap;
    }

    public (Callable callable)
    {
        fetcher = callable;
    }
}
