// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.executors;

import android.os.AsyncTask;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import java.util.concurrent.Executor;

public final class QueryExecutor
{

    private static final Executor EXECUTOR;

    public static transient void scheduleOnExecutor(AsyncTask asynctask, Object aobj[])
    {
        asynctask.executeOnExecutor(EXECUTOR, aobj);
    }

    static 
    {
        EXECUTOR = CalendarExecutors.serialExecutor(CalendarExecutor.DISK);
    }
}
