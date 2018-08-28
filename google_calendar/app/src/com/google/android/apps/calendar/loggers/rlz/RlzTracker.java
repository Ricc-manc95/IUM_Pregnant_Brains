// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.rlz;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.loggers.rlz:
//            RlzConfig

public class RlzTracker
{

    public static final ComponentName RLZ_PING_INTENT_SERVICE = new ComponentName("com.google.android.partnersetup", "com.google.android.partnersetup.RlzPingBroadcastReceiver");
    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/loggers/rlz/RlzTracker);
    public final RlzConfig config;
    public final Context context;
    public final SharedPreferences sharedPrefs;
    public boolean shouldUseFirstUseBroadcast;

    public RlzTracker(Context context1, RlzConfig rlzconfig, SharedPreferences sharedpreferences)
    {
        boolean flag2 = false;
        super();
        context = context1;
        config = rlzconfig;
        sharedPrefs = sharedpreferences;
        boolean flag3 = sharedPrefs.getBoolean("sent_rlz_first_use_broadcast", false);
        context1 = config;
        boolean flag;
        boolean flag1;
        if (!TextUtils.isEmpty(((RlzConfig) (context1)).brandCode) && !((RlzConfig) (context1)).accessPoints.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && !flag3)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        shouldUseFirstUseBroadcast = flag1;
        context1 = TAG;
        if (LogUtils.maxEnabledLogLevel > 2)
        {
            flag1 = false;
        } else
        if (Log.isLoggable(context1, 2))
        {
            flag1 = true;
        } else
        {
            flag1 = Log.isLoggable(context1, 2);
        }
        if (flag1)
        {
            context1 = TAG;
            boolean flag4 = shouldUseFirstUseBroadcast;
            rlzconfig = config;
            flag1 = flag2;
            if (!TextUtils.isEmpty(rlzconfig.brandCode))
            {
                flag1 = flag2;
                if (!rlzconfig.accessPoints.isEmpty())
                {
                    flag1 = true;
                }
            }
            LogUtils.v(context1, "Initial mShouldUseFirstUseBroadcast=%b (rlzEnabled=%b,brandCode=%s,broadcastSent=%b)", new Object[] {
                Boolean.valueOf(flag4), Boolean.valueOf(flag1), config.brandCode, Boolean.valueOf(flag3)
            });
        }
    }

}
