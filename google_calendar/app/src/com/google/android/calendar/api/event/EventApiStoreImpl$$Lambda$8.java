// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.api.event:
//            Event, EventApiStoreImpl

final class arg._cls1
    implements Predicate
{

    private final EventApiStoreImpl arg$1;

    public final boolean apply(Object obj)
    {
        EventApiStoreImpl eventapistoreimpl = arg$1;
        obj = (Event)obj;
        return !eventapistoreimpl.filterOutGoogleEvents || !AccountUtil.isGoogleAccount(((Event) (obj)).getCalendar().account);
    }

    (EventApiStoreImpl eventapistoreimpl)
    {
        arg$1 = eventapistoreimpl;
    }
}
