// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            CalendarStoreInvalidatorImpl, CalendarContentStore

final class arg._cls1
    implements Consumer
{

    private final CalendarStoreInvalidatorImpl arg$1;

    public final void accept(Object obj)
    {
        CalendarStoreInvalidatorImpl calendarstoreinvalidatorimpl;
        boolean flag;
label0:
        {
            boolean flag1 = false;
            calendarstoreinvalidatorimpl = arg$1;
            CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])obj;
            obj = new HashSet();
            int j = acalendarlistentry.length;
            for (int i = 0; i < j; i++)
            {
                CalendarListEntry calendarlistentry = acalendarlistentry[i];
                if (calendarlistentry.isVisible() && calendarlistentry.isSyncEnabled())
                {
                    ((Set) (obj)).add(calendarlistentry.getDescriptor().calendarKey);
                }
            }

            Set set = calendarstoreinvalidatorimpl.visibleCalendarIds;
            if (set != obj)
            {
                flag = flag1;
                if (set == null)
                {
                    break label0;
                }
                flag = flag1;
                if (!set.equals(obj))
                {
                    break label0;
                }
            }
            flag = true;
        }
        if (!flag)
        {
            calendarstoreinvalidatorimpl.visibleCalendarIds = ((Set) (obj));
            calendarstoreinvalidatorimpl.store.updateStore(.instance);
        }
    }

    (CalendarStoreInvalidatorImpl calendarstoreinvalidatorimpl)
    {
        arg$1 = calendarstoreinvalidatorimpl;
    }
}
