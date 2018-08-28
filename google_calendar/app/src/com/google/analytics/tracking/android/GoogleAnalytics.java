// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

// Referenced classes of package com.google.analytics.tracking.android:
//            TrackerHandler, GAThread, AdHitIdGenerator, AnalyticsThread, 
//            Tracker, GAUsage, Utils, AdMobInfo

public class GoogleAnalytics
    implements TrackerHandler
{
    public static interface AppOptOutCallback
    {

        public abstract void reportAppOptOut(boolean flag);
    }


    private static GoogleAnalytics sInstance;
    private AdHitIdGenerator mAdHitIdGenerator;
    public volatile Boolean mAppOptOut;
    public volatile String mClientId;
    private Context mContext;
    private Tracker mDefaultTracker;
    private AnalyticsThread mThread;
    private final Map mTrackers;

    GoogleAnalytics()
    {
        mTrackers = new HashMap();
    }

    private GoogleAnalytics(Context context)
    {
        if (GAThread.sInstance == null)
        {
            GAThread.sInstance = new GAThread(context);
        }
        this(context, ((AnalyticsThread) (GAThread.sInstance)));
    }

    private GoogleAnalytics(Context context, AnalyticsThread analyticsthread)
    {
        mTrackers = new HashMap();
        if (context == null)
        {
            throw new IllegalArgumentException("context cannot be null");
        } else
        {
            mContext = context.getApplicationContext();
            mThread = analyticsthread;
            mAdHitIdGenerator = new AdHitIdGenerator();
            mThread.requestAppOptOut(new _cls1());
            mThread.requestClientId(new _cls2());
            return;
        }
    }

    public static GoogleAnalytics getInstance(Context context)
    {
        com/google/analytics/tracking/android/GoogleAnalytics;
        JVM INSTR monitorenter ;
        if (sInstance == null)
        {
            sInstance = new GoogleAnalytics(context);
        }
        context = sInstance;
        com/google/analytics/tracking/android/GoogleAnalytics;
        JVM INSTR monitorexit ;
        return context;
        context;
        com/google/analytics/tracking/android/GoogleAnalytics;
        JVM INSTR monitorexit ;
        throw context;
    }

    public final Tracker getTracker(String s)
    {
        this;
        JVM INSTR monitorenter ;
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        throw new IllegalArgumentException("trackingId cannot be null");
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        Tracker tracker1 = (Tracker)mTrackers.get(s);
        Tracker tracker;
        tracker = tracker1;
        if (tracker1 != null)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        tracker1 = new Tracker(s, this);
        mTrackers.put(s, tracker1);
        tracker = tracker1;
        if (mDefaultTracker != null)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        mDefaultTracker = tracker1;
        tracker = tracker1;
        GAUsage.INSTANCE.setUsage(GAUsage.Field.GET_TRACKER);
        this;
        JVM INSTR monitorexit ;
        return tracker;
    }

    public final void sendHit(Map map)
    {
        this;
        JVM INSTR monitorenter ;
        if (map != null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        throw new IllegalArgumentException("hit cannot be null");
        map;
        this;
        JVM INSTR monitorexit ;
        throw map;
        map.put("language", Utils.getLanguage(Locale.getDefault()));
        if (mAdHitIdGenerator.mAdMobSdkInstalled)
        {
            break MISSING_BLOCK_LABEL_161;
        }
        int i = 0;
_L2:
        map.put("adSenseAdMobHitId", Integer.toString(i));
        map.put("screenResolution", (new StringBuilder()).append(mContext.getResources().getDisplayMetrics().widthPixels).append("x").append(mContext.getResources().getDisplayMetrics().heightPixels).toString());
        map.put("usage", GAUsage.INSTANCE.getAndClearSequence());
        GAUsage.INSTANCE.getAndClearUsage();
        mThread.sendHit(map);
        map.get("trackingId");
        this;
        JVM INSTR monitorexit ;
        return;
        AdMobInfo admobinfo = AdMobInfo.INSTANCE;
        admobinfo.mAdHitId = admobinfo.mRandom.nextInt(0x7ffffffe) + 1;
        i = admobinfo.mAdHitId;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private class _cls1
        implements AppOptOutCallback
    {

        private final GoogleAnalytics this$0;

        public final void reportAppOptOut(boolean flag)
        {
            mAppOptOut = Boolean.valueOf(flag);
        }

        _cls1()
        {
            this$0 = GoogleAnalytics.this;
            super();
        }
    }


    private class _cls2
        implements AnalyticsThread.ClientIdCallback
    {

        private final GoogleAnalytics this$0;

        public final void reportClientId(String s)
        {
            mClientId = s;
        }

        _cls2()
        {
            this$0 = GoogleAnalytics.this;
            super();
        }
    }

}
