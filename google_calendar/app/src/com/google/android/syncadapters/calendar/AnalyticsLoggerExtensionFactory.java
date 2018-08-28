// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.commonsync.analytics.api.AnalyticsLogger;
import com.google.android.gsf.Gservices;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            SyncAnalyticsLoggerExtension, AnalyticsLoggerBase

public class AnalyticsLoggerExtensionFactory
{

    private static final Object ANALYTICS_LOGGER_LOCK = new Object();
    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/AnalyticsLoggerExtensionFactory);
    public static SyncAnalyticsLoggerExtension analyticsLogger = null;
    public static String appVersion;
    public static SyncAnalyticsLoggerExtension consistencyAnalyticsLogger = null;
    private static Context context = null;
    private static String packageName;

    public AnalyticsLoggerExtensionFactory()
    {
    }

    private static String getAppVersion(Context context1)
    {
        PackageManager packagemanager;
        packagemanager = context1.getPackageManager();
        if (packagemanager == null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        context1 = packagemanager.getPackageInfo(context1.getPackageName(), 0);
        if (TextUtils.isEmpty(((PackageInfo) (context1)).versionName))
        {
            break MISSING_BLOCK_LABEL_51;
        }
        context1 = ((PackageInfo) (context1)).versionName;
        return context1;
        context1;
        LogUtils.e(TAG, context1, "Package name not found", new Object[0]);
        return "UNKNOWN";
    }

    private static String getPackageName(Context context1)
    {
        PackageManager packagemanager;
        packagemanager = context1.getPackageManager();
        if (packagemanager == null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        context1 = packagemanager.getPackageInfo(context1.getPackageName(), 0);
        if (TextUtils.isEmpty(((PackageInfo) (context1)).packageName))
        {
            break MISSING_BLOCK_LABEL_51;
        }
        context1 = ((PackageInfo) (context1)).packageName;
        return context1;
        context1;
        LogUtils.e(TAG, context1, "Package name not found", new Object[0]);
        return "UNKNOWN";
    }

    public static void initialize(Context context1, AnalyticsLogger analyticslogger)
    {
        Object obj3 = ANALYTICS_LOGGER_LOCK;
        obj3;
        JVM INSTR monitorenter ;
        if (analyticsLogger != null) goto _L2; else goto _L1
_L1:
        appVersion = getAppVersion(context1);
        packageName = getPackageName(context1);
        context = context1.getApplicationContext();
        packageName = getPackageName(context1);
        if (!"com.google.android.calendar".equals(packageName)) goto _L4; else goto _L3
_L3:
        Object obj;
        LogUtils.v(TAG, "Initializing Timely Syncadapter Logging", new Object[0]);
        obj = AnalyticsConstants.Timely.PROPERTY_ID;
_L11:
        Object obj4;
        Object obj5;
        obj4 = context;
        obj5 = context;
        if (!"com.google.android.calendar".equals(packageName)) goto _L6; else goto _L5
_L5:
        Object obj2 = AnalyticsConstants.Timely.DEFAULT_SAMPLING_RATE;
        Object obj1 = "calendar_sync_analytics_sampling_rate";
_L12:
        obj1 = new SyncAnalyticsLoggerExtension(((Context) (obj4)), ((String) (obj)), Double.parseDouble(Gservices.getString(((Context) (obj5)).getContentResolver(), ((String) (obj1)), ((String) (obj2)))), analyticslogger);
        analyticsLogger = ((SyncAnalyticsLoggerExtension) (obj1));
        obj2 = context;
        obj4 = appVersion;
        obj5 = packageName;
        ((AnalyticsLoggerBase) (obj1)).setCustomDimension(((Context) (obj2)), ((String) (obj)), 3, ((String) (obj4)));
        ((AnalyticsLoggerBase) (obj1)).setCustomDimension(((Context) (obj2)), ((String) (obj)), 4, ((String) (obj5)));
_L2:
        if (consistencyAnalyticsLogger != null) goto _L8; else goto _L7
_L7:
        appVersion = getAppVersion(context1);
        packageName = getPackageName(context1);
        context = context1.getApplicationContext();
        packageName = getPackageName(context1);
        if (!"com.google.android.calendar".equals(packageName)) goto _L10; else goto _L9
_L9:
        LogUtils.v(TAG, "Initializing Timely Syncadapter Consistency Check Logging", new Object[0]);
        context1 = AnalyticsConstants.Timely.ConsistencyCheck.PROPERTY_ID;
_L13:
        obj1 = context;
        obj2 = context;
        if ("com.google.android.calendar".equals(packageName))
        {
            obj = "calendar_consistency_check_analytics_sampling_rate";
        } else
        {
            obj = "calendar_consistency_check_standalone_analytics_sampling_rate";
        }
        analyticslogger = new SyncAnalyticsLoggerExtension(((Context) (obj1)), context1, Double.parseDouble(Gservices.getString(((Context) (obj2)).getContentResolver(), ((String) (obj)), "100.0")), analyticslogger);
        consistencyAnalyticsLogger = analyticslogger;
        obj = context;
        obj1 = appVersion;
        obj2 = packageName;
        analyticslogger.setCustomDimension(((Context) (obj)), context1, 3, ((String) (obj1)));
        analyticslogger.setCustomDimension(((Context) (obj)), context1, 4, ((String) (obj2)));
_L8:
        obj3;
        JVM INSTR monitorexit ;
        return;
_L4:
        LogUtils.v(TAG, "Initializing Standalone Syncadapter Logging", new Object[0]);
        obj = AnalyticsConstants.Standalone.PROPERTY_ID;
          goto _L11
_L6:
        obj2 = AnalyticsConstants.Standalone.DEFAULT_SAMPLING_RATE;
        obj1 = "calendar_ssa_analytics_sampling_rate";
          goto _L12
_L10:
        LogUtils.v(TAG, "Initializing Standalone Syncadapter Consistency Check Logging", new Object[0]);
        context1 = AnalyticsConstants.Standalone.ConsistencyCheck.PROPERTY_ID;
          goto _L13
        context1;
        obj3;
        JVM INSTR monitorexit ;
        throw context1;
          goto _L11
    }

}
