// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.timely.DataFactory;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineTaskType;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.calendar:
//            AnalyticsUtils, Utils, AllInOneCalendarActivity

final class arg._cls1
    implements com.google.android.calendar.timely.tener
{

    private final AllInOneCalendarActivity arg$1;

    public final void onAllEventsDataReady()
    {
        DataFactory datafactory = (DataFactory)((FragmentActivity) (arg$1)).mFragments.mHost.mFragmentManager.findFragmentByTag("data_factory");
        if (datafactory == null)
        {
            LogUtils.e("AllInOneCalendarAct", "DataFactory unavailable, cannot log analytics for AllDataReady.", new Object[0]);
        } else
        {
            FragmentActivity fragmentactivity;
            if (((Fragment) (datafactory)).mHost == null)
            {
                fragmentactivity = null;
            } else
            {
                fragmentactivity = (FragmentActivity)((Fragment) (datafactory)).mHost.mActivity;
            }
            if (fragmentactivity == null)
            {
                LogUtils.e("AllInOneCalendarAct", "Unable to retrieve context from DataFactory for AllDataReady", new Object[0]);
                return;
            }
            if (!AnalyticsUtils.haveLoggedAllDataReady)
            {
                AnalyticsUtils.haveLoggedAllDataReady = true;
                FragmentActivity fragmentactivity1;
                AnalyticsLogger analyticslogger;
                if (((Fragment) (datafactory)).mHost == null)
                {
                    fragmentactivity1 = null;
                } else
                {
                    fragmentactivity1 = (FragmentActivity)((Fragment) (datafactory)).mHost.mActivity;
                }
                analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                analyticslogger = (AnalyticsLogger)analyticslogger;
                HashSet hashset = new HashSet();
                int j = Utils.getTodayJulianDay(fragmentactivity1);
label0:
                for (int i = 0; i < 7; i++)
                {
                    int k = i + j;
                    Object obj = datafactory.getDataAllowNull(k);
                    if (obj == null)
                    {
                        AnalyticsUtils.haveLoggedAllDataReady = false;
                        return;
                    }
                    obj = ((MonthData) (obj)).getItems(k);
                    if (obj == null)
                    {
                        continue;
                    }
                    obj = ((List) (obj)).iterator();
                    do
                    {
                        TimelineItem timelineitem;
                        do
                        {
                            if (!((Iterator) (obj)).hasNext())
                            {
                                continue label0;
                            }
                            timelineitem = (TimelineItem)((Iterator) (obj)).next();
                        } while (timelineitem instanceof TimelineTaskType);
                        hashset.add(timelineitem);
                    } while (true);
                }

                analyticslogger.trackTiming(fragmentactivity1, "events_count", hashset.size() * 1000, "num_events", null);
                return;
            }
        }
    }

    eadyListener(AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
