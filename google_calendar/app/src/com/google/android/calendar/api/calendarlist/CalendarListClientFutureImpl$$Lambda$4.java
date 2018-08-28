// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.accounts.Account;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.utils.timely.TimelyUtils;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListClientFutureImpl

final class arg._cls3
    implements Callable
{

    private final CalendarListClientFutureImpl arg$1;
    private final Account arg$2;
    private final String arg$3;

    public final Object call()
    {
        Object obj = arg$1;
        obj = arg$2;
        String s = arg$3;
        TimelyUtils.subscribeCalendar(CalendarApi.getApiAppContext(), ((Account) (obj)), s, null);
        return null;
    }

    Y(CalendarListClientFutureImpl calendarlistclientfutureimpl, Account account, String s)
    {
        arg$1 = calendarlistclientfutureimpl;
        arg$2 = account;
        arg$3 = s;
    }
}
