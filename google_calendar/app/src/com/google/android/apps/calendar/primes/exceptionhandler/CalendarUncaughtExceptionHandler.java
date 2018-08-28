// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.primes.exceptionhandler;

import android.content.Context;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.experiments.Experiment;
import com.google.android.apps.calendar.config.experiments.ExperimentConfiguration;
import com.google.android.apps.calendar.loggers.SilentFeedbackReceiver;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.gsf.Gservices;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.HashSet;
import java.util.Set;

public class CalendarUncaughtExceptionHandler
    implements Thread.UncaughtExceptionHandler
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/primes/exceptionhandler/CalendarUncaughtExceptionHandler);
    private static boolean isInitialized;
    private final Context context;
    private final Thread.UncaughtExceptionHandler previousHandler;

    private CalendarUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler, Context context1)
    {
        previousHandler = uncaughtexceptionhandler;
        context = context1;
    }

    public static void installHandler(Context context1)
    {
        while (isInitialized || "com.google.android.syncadapters.calendar".equals(context1.getApplicationContext().getPackageName()) && !ExperimentConfiguration.SSA_SILENT_FEEDBACK.isActive(context1)) 
        {
            return;
        }
        boolean flag = Gservices.getBoolean(context1.getContentResolver(), "google_calendar_enable_silent_crash_feedback", true);
        boolean flag1 = Gservices.getBoolean(context1.getContentResolver(), "google_calendar_enable_primes_crash", ExperimentConfiguration.PRIMES_CRASH_INSTRUMENTATION.isActive(context1));
        Thread.UncaughtExceptionHandler uncaughtexceptionhandler = Thread.getDefaultUncaughtExceptionHandler();
        if (flag)
        {
            Thread.setDefaultUncaughtExceptionHandler(new CalendarUncaughtExceptionHandler(uncaughtexceptionhandler, context1));
        }
        if (flag1)
        {
            context1 = PerformanceMetricCollectorHolder.instance;
            if (context1 == null)
            {
                throw new NullPointerException(String.valueOf("PrimesLogger not set"));
            }
            Thread.setDefaultUncaughtExceptionHandler(((PerformanceMetricCollector)context1).wrapCrashReportingIntoUncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()));
        }
        isInitialized = true;
    }

    private static void printCleanedThrowable(Throwable throwable, StringBuilder stringbuilder, Set set, String s)
    {
        do
        {
            if (throwable == null || set.contains(throwable))
            {
                break;
            }
            set.add(throwable);
            if (s != null)
            {
                stringbuilder.append(s);
            }
            stringbuilder.append(throwable.getClass().getName());
            s = throwable.getStackTrace();
            int k = s.length;
            for (int i = 0; i < k; i++)
            {
                Object obj = s[i];
                stringbuilder.append("\n\tat ");
                stringbuilder.append(obj);
            }

            s = ThrowableExtension.STRATEGY.getSuppressed(throwable);
            k = s.length;
            for (int j = 0; j < k; j++)
            {
                printCleanedThrowable(s[j], stringbuilder, set, "\nSuppressed: ");
            }

            if (throwable.getCause() == null)
            {
                break;
            }
            throwable = throwable.getCause();
            s = "\nCaused by: ";
        } while (true);
    }

    public void uncaughtException(Thread thread, Throwable throwable)
    {
        Thread.setDefaultUncaughtExceptionHandler(previousHandler);
        String s = TAG;
        Object obj;
        if (thread.getName() != null)
        {
            obj = thread.getName();
        } else
        {
            obj = "null";
        }
        LogUtils.e(s, throwable, "Uncaught Android Crash on thread %s with tid %s", new Object[] {
            obj, Long.valueOf(thread.getId())
        });
        obj = throwable.getStackTrace();
        if (throwable.getStackTrace().length != 0)
        {
            Object obj2 = new StringBuilder();
            printCleanedThrowable(throwable, ((StringBuilder) (obj2)), new HashSet(), null);
            Object obj1 = obj[0];
            Context context1;
            String s1;
            String s2;
            String s3;
            int i;
            if (TextUtils.isEmpty(((StackTraceElement) (obj1)).getFileName()))
            {
                obj = "Unknown Source";
            } else
            {
                obj = ((StackTraceElement) (obj1)).getFileName();
            }
            context1 = context;
            s1 = throwable.getClass().getName();
            obj2 = ((StringBuilder) (obj2)).toString();
            s2 = ((StackTraceElement) (obj1)).getClassName();
            i = ((StackTraceElement) (obj1)).getLineNumber();
            s3 = ((StackTraceElement) (obj1)).getMethodName();
            if ("com.google.android.syncadapters.calendar".equals(context.getApplicationContext().getPackageName()))
            {
                obj1 = "com.google.android.syncadapters.calendar.SILENT_FEEDBACK";
            } else
            {
                obj1 = "com.google.android.apps.calendar.calendar.SILENT_FEEDBACK";
            }
            obj = SilentFeedbackReceiver.createIntent(context1, s1, ((String) (obj2)), s2, ((String) (obj)), i, s3, ((String) (obj1)));
            context.sendBroadcast(((android.content.Intent) (obj)));
        }
        if (previousHandler != null)
        {
            previousHandler.uncaughtException(thread, throwable);
        }
    }

}
