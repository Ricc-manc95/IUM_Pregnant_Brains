// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;


// Referenced classes of package com.google.android.apps.calendar.util.concurrent:
//            CalendarExecutors

final class arg._cls2
    implements Runnable
{

    private final xt arg$1;
    private final Runnable arg$2;

    public final void run()
    {
        arg._cls2 _lcls2;
        Runnable runnable;
        _lcls2 = arg$1;
        runnable = arg$2;
        CalendarExecutors.serialExecutorTag.set(_lcls2);
        runnable.run();
        CalendarExecutors.serialExecutorTag.set(null);
        _lcls2.xt();
        return;
        Exception exception;
        exception;
        CalendarExecutors.serialExecutorTag.set(null);
        _lcls2.xt();
        throw exception;
    }

    ( , Runnable runnable)
    {
        arg$1 = ;
        arg$2 = runnable;
    }
}
