// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.commonsync.analytics.v2;

import android.content.Context;
import android.text.TextUtils;
import com.google.analytics.tracking.android.GAUsage;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Log;
import com.google.analytics.tracking.android.Tracker;
import com.google.analytics.tracking.android.Utils;
import com.google.android.apps.calendar.commonsync.analytics.api.AnalyticsLogger;
import java.util.HashMap;
import java.util.Map;

public final class V2AnalyticsLogger
    implements AnalyticsLogger
{

    public V2AnalyticsLogger()
    {
    }

    public final void setCustomDimension(Context context, String s, int i, String s1)
    {
        context = GoogleAnalytics.getInstance(context).getTracker(s);
        if (i <= 0)
        {
            Log.w((new StringBuilder("index must be > 0, ignoring setCustomDimension call for ")).append(i).append(", ").append(s1).toString());
            return;
        } else
        {
            ((Tracker) (context)).mModel.setForNextHit(Utils.getSlottedModelField("customDimension", i), s1);
            return;
        }
    }

    public final void setCustomMetric(Context context, String s, int i, long l)
    {
        s = GoogleAnalytics.getInstance(context).getTracker(s);
        Long long1 = Long.valueOf(l);
        if (i <= 0)
        {
            Log.w((new StringBuilder("index must be > 0, ignoring setCustomMetric call for ")).append(i).append(", ").append(long1).toString());
            return;
        }
        context = null;
        if (long1 != null)
        {
            context = Long.toString(long1.longValue());
        }
        ((Tracker) (s)).mModel.setForNextHit(Utils.getSlottedModelField("customMetric", i), context);
    }

    public final void setSampleRate(Context context, String s, double d)
    {
        context = GoogleAnalytics.getInstance(context).getTracker(s);
        GAUsage.INSTANCE.setUsage(com.google.analytics.tracking.android.GAUsage.Field.SET_SAMPLE_RATE);
        ((Tracker) (context)).mModel.set("sampleRate", Double.toString(d));
    }

    public final void trackEvent(Context context, String s, String s1, String s2, String s3, Long long1)
    {
        context = GoogleAnalytics.getInstance(context).getTracker(s);
        boolean flag = ((Tracker) (context)).mIsTrackerClosed;
        GAUsage.INSTANCE.setUsage(com.google.analytics.tracking.android.GAUsage.Field.TRACK_EVENT);
        GAUsage.INSTANCE.setDisableUsage(true);
        s = new HashMap();
        s.put("eventCategory", s1);
        s.put("eventAction", s2);
        s.put("eventLabel", s3);
        if (long1 != null)
        {
            s.put("eventValue", Long.toString(long1.longValue()));
        }
        GAUsage.INSTANCE.setUsage(com.google.analytics.tracking.android.GAUsage.Field.CONSTRUCT_EVENT);
        context.internalSend("event", s);
        GAUsage.INSTANCE.setDisableUsage(false);
    }

    public final void trackScreenView(Context context, String s, String s1)
    {
        context = GoogleAnalytics.getInstance(context).getTracker(s);
        boolean flag = ((Tracker) (context)).mIsTrackerClosed;
        if (TextUtils.isEmpty(s1))
        {
            throw new IllegalStateException("trackView requires a appScreen to be set");
        } else
        {
            GAUsage.INSTANCE.setUsage(com.google.analytics.tracking.android.GAUsage.Field.TRACK_VIEW_WITH_APPSCREEN);
            ((Tracker) (context)).mModel.set("description", s1);
            context.internalSend("appview", null);
            return;
        }
    }

    public final void trackTiming(Context context, String s, String s1, long l, String s2, String s3)
    {
        context = GoogleAnalytics.getInstance(context).getTracker(s);
        boolean flag = ((Tracker) (context)).mIsTrackerClosed;
        GAUsage.INSTANCE.setUsage(com.google.analytics.tracking.android.GAUsage.Field.TRACK_TIMING);
        GAUsage.INSTANCE.setDisableUsage(true);
        s = new HashMap();
        s.put("timingCategory", s1);
        s.put("timingValue", Long.toString(l));
        s.put("timingVar", s2);
        s.put("timingLabel", s3);
        GAUsage.INSTANCE.setUsage(com.google.analytics.tracking.android.GAUsage.Field.CONSTRUCT_TIMING);
        context.internalSend("timing", s);
        GAUsage.INSTANCE.setDisableUsage(false);
    }
}
