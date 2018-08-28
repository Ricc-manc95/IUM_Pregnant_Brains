// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import android.accounts.Account;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            EventsApiImpl

final class arg._cls1
    implements Predicate
{

    private final Account arg$1;

    public final boolean apply(Object obj)
    {
        return EventsApiImpl.lambda$searchGoalsAsync$4$EventsApiImpl(arg$1, (CalendarListEntry)obj);
    }

    (Account account)
    {
        arg$1 = account;
    }
}
