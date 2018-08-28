// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            VersionedItem, CalendarWeekCache

final class arg._cls2
    implements Predicate
{

    private final CalendarWeekCache arg$1;
    private final Object arg$2;

    public final boolean apply(Object obj)
    {
        CalendarWeekCache calendarweekcache = arg$1;
        Object obj1 = arg$2;
        obj = (VersionedItem)obj;
        obj = calendarweekcache.adapter.getKey(((VersionedItem) (obj)).getItem());
        boolean flag;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return !flag;
    }

    (CalendarWeekCache calendarweekcache, Object obj)
    {
        arg$1 = calendarweekcache;
        arg$2 = obj;
    }
}
