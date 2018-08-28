// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.v2a;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutors;
import com.google.android.calendar.executors.ThrottlingExecutor;
import com.google.android.calendar.sharedprefs.SharedPrefs;
import com.google.calendar.v2a.shared.android.AndroidSharedApi;
import com.google.calendar.v2a.shared.sync.DebugService;
import com.google.calendar.v2a.shared.sync.android.SyncDebugService;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class UnifiedSyncUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/v2a/UnifiedSyncUtils);

    public UnifiedSyncUtils()
    {
    }

    public static void applyUssNotReadyOverrides(Context context)
    {
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)context).uss();
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)context).uss();
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)context).uss();
            return;
        }
    }

    public static void cleanupUnifiedSync(Context context)
    {
        class .Lambda._cls6
            implements Runnable
        {

            private final Context arg$1;

            public final void run()
            {
                UnifiedSyncUtils.lambda$cleanupUnifiedSyncFuture$11$UnifiedSyncUtils(arg$1);
            }

            .Lambda._cls6(Context context)
            {
                arg$1 = context;
            }
        }

        LogUtils.logOnFailure((FluentFuture)CalendarExecutor.DISK.submit(new .Lambda._cls6(context)), TAG, "Failed to remove database", new Object[0]);
    }

    public static ListenableFuture getDatabaseDump(Context context)
    {
        class .Lambda._cls7
            implements Function
        {

            public static final Function $instance = new .Lambda._cls7();

            public final Object apply(Object obj)
            {
                return UnifiedSyncUtils.lambda$getDatabaseDump$13$UnifiedSyncUtils((AndroidSharedApi)obj);
            }


            private .Lambda._cls7()
            {
            }
        }

        context = .Lambda._cls7..instance;
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)context).uss();
        if (true)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
    }

    public static ListenableFuture getSyncLogs(Context context, long l)
    {
        class .Lambda._cls8
            implements Function
        {

            private final long arg$1;

            public final Object apply(Object obj)
            {
                return UnifiedSyncUtils.lambda$getSyncLogs$14$UnifiedSyncUtils(arg$1, (AndroidSharedApi)obj);
            }

            .Lambda._cls8(long l)
            {
                arg$1 = l;
            }
        }

        new .Lambda._cls8(0x400000L);
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)context).uss();
        if (true)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
    }

    public static ListenableFuture getSyncTraces(Context context, long l)
    {
        class .Lambda._cls9
            implements Function
        {

            private final long arg$1;

            public final Object apply(Object obj)
            {
                return UnifiedSyncUtils.lambda$getSyncTraces$15$UnifiedSyncUtils(arg$1, (AndroidSharedApi)obj);
            }

            .Lambda._cls9(long l)
            {
                arg$1 = l;
            }
        }

        new .Lambda._cls9(0x400000L);
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)context).uss();
        if (true)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
    }

    static final void lambda$cleanupUnifiedSyncFuture$11$UnifiedSyncUtils(Context context)
    {
        File file = context.getDatabasePath("cal_v2a");
        if (!context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("uss_cleaned_up", false) || file.exists())
        {
            FeatureConfig featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).uss();
            featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).uss_provider_sync();
            featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).experimental_options();
            SharedPrefs.removeSharedPreference(context, "uss_hns_ready");
            SharedPrefs.removeSharedPreference(context, "uss_cne_shipshape");
            SharedPrefs.setSharedPreference(context, "uss_cleaned_up", true);
            SQLiteDatabase.deleteDatabase(file);
        }
    }

    static final String lambda$getDatabaseDump$12$UnifiedSyncUtils(AndroidSharedApi androidsharedapi)
        throws Exception
    {
        return androidsharedapi.debugService().getDatabaseDump();
    }

    static final ListenableFuture lambda$getDatabaseDump$13$UnifiedSyncUtils(AndroidSharedApi androidsharedapi)
    {
        class .Lambda._cls12
            implements Callable
        {

            private final AndroidSharedApi arg$1;

            public final Object call()
            {
                return UnifiedSyncUtils.lambda$getDatabaseDump$12$UnifiedSyncUtils(arg$1);
            }

            .Lambda._cls12(AndroidSharedApi androidsharedapi)
            {
                arg$1 = androidsharedapi;
            }
        }

        return (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls12(androidsharedapi));
    }

    static final ListenableFuture lambda$getSyncLogs$14$UnifiedSyncUtils(long l, AndroidSharedApi androidsharedapi)
    {
        return androidsharedapi.syncDebugService().getSyncLogs(l);
    }

    static final ListenableFuture lambda$getSyncTraces$15$UnifiedSyncUtils(long l, AndroidSharedApi androidsharedapi)
    {
        return androidsharedapi.syncDebugService().getSyncTraces(l);
    }

    public static void logInitialization(Context context)
    {
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)context).uss();
            return;
        }
    }

    public static boolean shouldUseCalendarsAndEvents()
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).uss();
            return false;
        }
    }

    static 
    {
        new ThrottlingExecutor(CalendarExecutor.BACKGROUND, TimeUnit.SECONDS.toMillis(5L));
        CalendarExecutors.serialExecutor(CalendarExecutor.BACKGROUND);
    }
}
