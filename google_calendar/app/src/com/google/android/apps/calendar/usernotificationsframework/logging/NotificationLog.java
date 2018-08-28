// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.logging;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.apps.xplat.logging.AndroidFileLogger;
import com.google.apps.xplat.logging.XLogLevel;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public final class NotificationLog
{

    public static final long LOG_FILES_MAX_AGE;
    public static final Executor SERIAL_EXECUTOR;
    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/usernotificationsframework/logging/NotificationLog);
    public static AndroidFileLogger fileLogger;

    private NotificationLog()
    {
    }

    public static String formatTime(long l)
    {
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US)).format(Long.valueOf(l));
    }

    public static File getLogsDirectory(Context context)
    {
        return new File(context.getFilesDir(), "notification_logs");
    }

    static final void lambda$cleanup$2$NotificationLog(Context context)
    {
        try
        {
            context = new File(context.getFilesDir(), "notification_logs");
            if (context.exists())
            {
                context.delete();
            }
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.e(TAG, context, "Failed to delete the directory.", new Object[0]);
        }
    }

    static final void lambda$e$4$NotificationLog(String s, String s1, Object aobj[])
    {
        LogUtils.e(s, s1, aobj);
        if (fileLogger != null)
        {
            fileLogger.log(s, XLogLevel.ERROR, LogUtils.safeFormat(s1, aobj));
        }
    }

    static final void lambda$e$5$NotificationLog(String s, Throwable throwable, String s1, Object aobj[])
    {
        LogUtils.e(s, throwable, s1, aobj);
        if (fileLogger != null)
        {
            fileLogger.log(s, XLogLevel.ERROR, LogUtils.safeFormat(s1, aobj), throwable);
        }
    }

    static final void lambda$i$3$NotificationLog(String s, String s1, Object aobj[])
    {
        LogUtils.i(s, s1, aobj);
        if (fileLogger != null)
        {
            fileLogger.log(s, XLogLevel.INFO, LogUtils.safeFormat(s1, aobj));
        }
    }

    public static transient void logOnFailure(ListenableFuture listenablefuture, final String tag, final String format, final Object args[])
    {
        tag = new _cls1();
        format = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (tag == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, tag), format);
            return;
        }
    }

    static 
    {
        LOG_FILES_MAX_AGE = TimeUnit.DAYS.toMillis(7L);
        SERIAL_EXECUTOR = CalendarExecutors.serialExecutor(CalendarExecutor.DISK);
    }

    private class _cls1
        implements FutureCallback
    {

        private final Object val$args[];
        private final String val$format;
        private final String val$tag;

        public final void onFailure(Throwable throwable)
        {
            String s = tag;
            String s1 = format;
            Object aobj[] = args;
            class .Lambda._cls5
                implements Runnable
            {

                private final String arg$1;
                private final Throwable arg$2;
                private final String arg$3;
                private final Object arg$4[];

                public final void run()
                {
                    NotificationLog.lambda$e$5$NotificationLog(arg$1, arg$2, arg$3, arg$4);
                }

                public .Lambda._cls5(String s, Throwable throwable, String s1, Object aobj[])
                {
                    arg$1 = s;
                    arg$2 = throwable;
                    arg$3 = s1;
                    arg$4 = aobj;
                }
            }

            NotificationLog.SERIAL_EXECUTOR.execute(new .Lambda._cls5(s, throwable, s1, aobj));
        }

        public final void onSuccess(Object obj)
        {
        }

        _cls1()
        {
            tag = s;
            format = s1;
            args = aobj;
            super();
        }
    }

}
