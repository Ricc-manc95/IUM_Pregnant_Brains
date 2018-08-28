// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListApiStoreImpl, CalendarListEntry, CalendarListClientFutureImpl, CalendarDescriptor

final class arg._cls2
    implements Callable
{

    private final CalendarListClientFutureImpl arg$1;
    private final CalendarDescriptor arg$2;

    public final Object call()
    {
        boolean flag = false;
        Object obj = arg$1;
        obj = CalendarListApiStoreImpl.read(arg$2);
        if (obj != null)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        } else
        {
            return (CalendarListEntry)obj;
        }
    }

    Y(CalendarListClientFutureImpl calendarlistclientfutureimpl, CalendarDescriptor calendardescriptor)
    {
        arg$1 = calendarlistclientfutureimpl;
        arg$2 = calendardescriptor;
    }
}
