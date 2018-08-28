// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.visualelements;

import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.loggers.visualelements:
//            CalendarAnalyticsLoggerProvider

final class 
    implements Provider
{

    public static final Provider $instance = new <init>();

    public final Object get()
    {
        return CalendarAnalyticsLoggerProvider.handlers;
    }


    private ()
    {
    }
}
