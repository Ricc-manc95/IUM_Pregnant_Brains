// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import com.google.api.services.calendar.model.EventReminder;
import java.util.Comparator;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            EventHandler

final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (EventReminder)obj;
        obj1 = (EventReminder)obj1;
        int j = EventHandler.compareObjects(((EventReminder) (obj)).method, ((EventReminder) (obj1)).method);
        int i = j;
        if (j == 0)
        {
            i = EventHandler.compareObjects(((EventReminder) (obj)).minutes, ((EventReminder) (obj1)).minutes);
        }
        return i;
    }

    ()
    {
    }
}
