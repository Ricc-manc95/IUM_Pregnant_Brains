// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.Context;
import android.content.Entity;
import android.content.SyncResult;
import android.content.SyncStats;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseLongArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.commonsync.analytics.api.AnalyticsLogger;
import com.google.android.apps.calendar.commonsync.constants.Constants;
import com.google.api.client.http.HttpResponseException;
import com.google.common.collect.ImmutableMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            AnalyticsLoggerBase, SyncLog, SyncStatsHelper

public class SyncAnalyticsLoggerExtension extends AnalyticsLoggerBase
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/SyncAnalyticsLoggerExtension);
    private static StringBuilder customDimensionsBuilder = new StringBuilder();
    private static StringBuilder customMetricsBuilder = new StringBuilder();
    private static StringBuilder syncLogBuilder = new StringBuilder();
    public final Context context;
    public final String trackingId;

    SyncAnalyticsLoggerExtension(Context context1, String s, double d, AnalyticsLogger analyticslogger)
    {
        super(context1, s, d, analyticslogger);
        context = context1;
        trackingId = s;
    }

    public final void addSyncSpecificCustomDimensions()
    {
        if (SyncLog.syncType == null)
        {
            LogUtils.w(TAG, "No sync custom dimensions found", new Object[0]);
        } else
        {
            setCustomDimension(context, trackingId, 10, SyncLog.syncType);
            setCustomDimension(context, trackingId, 6, SyncLog.accountType);
            setCustomDimension(context, trackingId, 7, SyncLog.calendarType);
            setCustomDimension(context, trackingId, 8, SyncLog.calendarAccess);
            setCustomDimension(context, trackingId, 9, SyncLog.calendarVisibility);
            setCustomDimension(context, trackingId, 26, String.valueOf(SyncLog.accountIndex));
            setCustomDimension(context, trackingId, 27, String.valueOf(SyncLog.calendarId));
            if (SyncLog.tooManyDeletionsResolution != null)
            {
                setCustomDimension(context, trackingId, 11, SyncLog.tooManyDeletionsResolution);
                return;
            }
        }
    }

    public final void logCalendarHttpException(String s, HttpResponseException httpresponseexception, String s1, String s2)
    {
        int i = httpresponseexception.statusCode;
        s1 = SyncStatsHelper.getMutatorType(s1);
        setCustomDimension(context, trackingId, 31, s1);
        logSyncError(s, i);
        if (!TextUtils.isEmpty(s2))
        {
            SyncLog.logError(httpresponseexception, s2);
        }
    }

    public final void logEventHttpException(String s, HttpResponseException httpresponseexception, String s1, Entity entity)
    {
        int i = httpresponseexception.statusCode;
        String s3 = httpresponseexception.statusMessage;
        String s2 = s3;
        if (s3 == null)
        {
            s2 = String.valueOf(i);
        }
        s1 = SyncStatsHelper.getMutatorType(s1);
        setCustomDimension(context, trackingId, 31, s1);
        logSyncError(s, i);
        SyncLog.logError(httpresponseexception, entity, (new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(s2).length())).append(s).append(": ").append(s2).toString());
    }

    public final void logSyncError(String s, int i)
    {
        String s1 = (String)Constants.ERROR_MAP.get(Integer.valueOf(i));
        if (s1 == null)
        {
            s1 = String.valueOf(i);
        }
        addSyncSpecificCustomDimensions();
        trackEvent("Sync", s, s1, 0L, null);
    }

    public final void logSyncLogEvent(String s, String s1, long l, SyncResult syncresult, SparseArray sparsearray, SparseLongArray sparselongarray)
    {
        addSyncSpecificCustomDimensions();
        if (sparsearray != null)
        {
            for (int i = 0; i < sparsearray.size(); i++)
            {
                setCustomDimension(context, trackingId, sparsearray.keyAt(i), (String)sparsearray.valueAt(i));
            }

        }
        if (sparselongarray != null)
        {
            for (int j = 0; j < sparselongarray.size(); j++)
            {
                setCustomMetric(context, trackingId, sparselongarray.keyAt(j), sparselongarray.valueAt(j));
            }

        }
        if (syncresult == null || syncresult.stats == null)
        {
            LogUtils.d(TAG, "No Sync Stats found", new Object[0]);
        } else
        {
            sparsearray = syncresult.stats;
            if (syncresult.tooManyDeletions)
            {
                setCustomDimension(context, trackingId, 12, "TRUE");
            }
            setCustomMetric(context, trackingId, 1, ((SyncStats) (sparsearray)).numInserts);
            setCustomMetric(context, trackingId, 2, ((SyncStats) (sparsearray)).numUpdates);
            setCustomMetric(context, trackingId, 3, ((SyncStats) (sparsearray)).numDeletes);
            setCustomMetric(context, trackingId, 4, ((SyncStats) (sparsearray)).numEntries);
            setCustomMetric(context, trackingId, 5, ((SyncStats) (sparsearray)).numSkippedEntries);
            setCustomMetric(context, trackingId, 6, ((SyncStats) (sparsearray)).numAuthExceptions);
            setCustomMetric(context, trackingId, 7, ((SyncStats) (sparsearray)).numIoExceptions);
            setCustomMetric(context, trackingId, 8, ((SyncStats) (sparsearray)).numParseExceptions);
            setCustomMetric(context, trackingId, 9, ((SyncStats) (sparsearray)).numConflictDetectedExceptions);
        }
        LogUtils.v(TAG, "track event: %s %s %s %d", new Object[] {
            "SyncLog", s, s1, Long.valueOf(l)
        });
        analytics.trackEvent(context, trackingId, "SyncLog", s, s1, Long.valueOf(l));
        customDimensionsBuilder.setLength(0);
        customMetricsBuilder.setLength(0);
    }

    public final void setCustomDimension(Context context1, String s, int i, String s1)
    {
        if (trackingId.equals(s))
        {
            if (customDimensionsBuilder.length() == 0)
            {
                customDimensionsBuilder.append(" Custom Dimensions:");
            }
            customDimensionsBuilder.append(" ").append(i).append("=").append(s1);
        }
        LogUtils.v(TAG, "set custom dimension: %d %s", new Object[] {
            Integer.valueOf(i), s1
        });
        super.setCustomDimension(context1, s, i, s1);
    }

    public final void setCustomMetric(Context context1, String s, int i, long l)
    {
        if (trackingId.equals(s))
        {
            if (customMetricsBuilder.length() == 0)
            {
                customMetricsBuilder.append(" Custom Metrics:");
            }
            customMetricsBuilder.append(" ").append(i).append("=").append(l);
        }
        LogUtils.v(TAG, "set custom metric: %d %s", new Object[] {
            Integer.valueOf(i), Long.valueOf(l)
        });
        super.setCustomMetric(context1, s, i, l);
    }

    public final void trackEvent(String s, String s1, String s2, long l, Map map)
    {
        if (map != null)
        {
            map = map.entrySet().iterator();
            do
            {
                if (!map.hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry = (java.util.Map.Entry)map.next();
                if (entry.getValue() != null)
                {
                    setCustomDimension(context, trackingId, ((Integer)entry.getKey()).intValue(), (String)entry.getValue());
                }
            } while (true);
        }
        LogUtils.v(TAG, "track event: %s %s %s %d", new Object[] {
            s, s1, s2, Long.valueOf(l)
        });
        analytics.trackEvent(context, trackingId, s, s1, s2, Long.valueOf(l));
        syncLogBuilder.setLength(0);
        syncLogBuilder.append("Analytics Event: ").append(s).append(" ").append(s1).append(" ").append(s2).append(" ").append(l).append(customDimensionsBuilder.toString()).append(customMetricsBuilder.toString());
        SyncLog.logToSyncLog(new String[] {
            syncLogBuilder.toString()
        });
        customDimensionsBuilder.setLength(0);
        customMetricsBuilder.setLength(0);
    }

}
