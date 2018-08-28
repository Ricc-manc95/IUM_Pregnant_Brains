// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import android.util.Base64;
import com.google.api.services.calendar.model.Event;
import java.util.List;

final class Result
    implements lass
{

    public final Result classify(List list, Event event, Event event1)
    {
        Result result = new Result();
        if (list.size() == 1 && ((String)list.get(0)).equals("ETAG") && event.etag != null && event1.etag != null)
        {
            list = event.etag.replace("\"", "");
            event = event1.etag.replace("\"", "");
            boolean flag;
            if (list.equals(event) || list.endsWith(Base64.encodeToString(event.getBytes(), 3)))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            result.belongsToClass = flag;
        }
        return result;
    }

    Result()
    {
    }
}
