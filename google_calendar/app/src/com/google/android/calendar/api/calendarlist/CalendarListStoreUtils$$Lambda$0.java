// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.accounts.Account;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarDescriptor, CalendarKey

final class arg._cls2
    implements Function
{

    private final String arg$1;
    private final long arg$2;

    public final Object apply(Object obj)
    {
        String s = arg$1;
        long l = arg$2;
        return new CalendarDescriptor((Account)obj, s, CalendarKey.newInstance(l));
    }

    (String s, long l)
    {
        arg$1 = s;
        arg$2 = l;
    }
}
