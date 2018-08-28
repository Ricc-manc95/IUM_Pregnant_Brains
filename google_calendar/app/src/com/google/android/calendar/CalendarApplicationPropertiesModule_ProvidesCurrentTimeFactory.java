// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.time.clock.Clock;
import dagger.internal.Factory;

public final class CalendarApplicationPropertiesModule_ProvidesCurrentTimeFactory
    implements Factory
{

    public static final CalendarApplicationPropertiesModule_ProvidesCurrentTimeFactory INSTANCE = new CalendarApplicationPropertiesModule_ProvidesCurrentTimeFactory();

    public CalendarApplicationPropertiesModule_ProvidesCurrentTimeFactory()
    {
    }

    public final Object get()
    {
        com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1 _lcls1;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        _lcls1 = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Long.valueOf(l));
        if (_lcls1 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)_lcls1;
        }
    }

}
