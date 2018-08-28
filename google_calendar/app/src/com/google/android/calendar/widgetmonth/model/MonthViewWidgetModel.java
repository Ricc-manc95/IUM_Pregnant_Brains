// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth.model;

import android.content.Context;
import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.timely.EventRangedQueryHandler;
import com.google.android.calendar.timely.RangedData;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.geometry.GridPartitionItemGeometry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.widgetmonth.model:
//            MonthViewWidgetRangedQueryHandler, MonthViewWidgetGridModel

public class MonthViewWidgetModel
{
    static final class MonthViewWidgetEventResults
        implements com.google.android.calendar.timely.RangedData.EventResults
    {

        public final ArrayList results;

        public final void addTimelineItem(TimelineItem timelineitem)
        {
            results.add(timelineitem);
        }

        MonthViewWidgetEventResults()
        {
            results = new ArrayList();
        }

        MonthViewWidgetEventResults(int i)
        {
            results = new ArrayList(i);
        }
    }

    final class MonthViewWidgetRangedData
        implements RangedData
    {

        public final int endDay;
        public volatile List events;
        public final int startDay;
        public volatile List tasks;
        public final MonthViewWidgetModel this$0;
        private int token;

        public final boolean containsDay(int i)
        {
            return startDay <= i && i <= endDay;
        }

        public final String getDebugTag()
        {
            return getClass().getSimpleName();
        }

        public final int getEndDay()
        {
            return endDay;
        }

        public final int getStartDay()
        {
            return startDay;
        }

        public final int getToken()
        {
            return token;
        }

        public final void recycle(int i)
        {
            boolean flag;
            if (startDay <= i && i <= endDay)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                LogUtils.wtf(MonthViewWidgetModel.TAG, "Julian day is outside the model's range: appWidgetId=%d, startDay=%d, endDay=%d, recycleJulianDay=%d", new Object[] {
                    Integer.valueOf(appWidgetId), Integer.valueOf(startDay), Integer.valueOf(endDay), Integer.valueOf(i)
                });
            }
        }

        public final void setToken(int i)
        {
            token = i;
        }

        MonthViewWidgetRangedData(int i, int j)
        {
            this$0 = MonthViewWidgetModel.this;
            super();
            events = Collections.emptyList();
            tasks = Collections.emptyList();
            startDay = i;
            endDay = j;
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/widgetmonth/model/MonthViewWidgetModel);
    public static final SparseArray modelForAppWidgetId = new SparseArray();
    public final int appWidgetId;
    public MonthViewWidgetGridModel cachedGridModel;
    public final Context context;
    public int endJulianDay;
    public boolean forceNewRefreshAfterDataLoading;
    public final GridPartitionItemGeometry geometry;
    public boolean hasModelBeenRemoved;
    public SparseArray itemsForJulianDay;
    public int startJulianDay;

    public MonthViewWidgetModel(Context context1, int i, int j, int k)
    {
        context = context1.getApplicationContext();
        appWidgetId = i;
        startJulianDay = j - 6;
        endJulianDay = k + 6;
        startDataLoadingAsync();
        geometry = new GridPartitionItemGeometry(context1);
    }

    public static int computeMaximumStartJulianDay(int i)
    {
        return i - 6;
    }

    public static int computeMinimumEndJulianDay(int i)
    {
        return i + 6;
    }

    public static void removeAllModels()
    {
        for (int i = 0; i < modelForAppWidgetId.size(); i++)
        {
            ((MonthViewWidgetModel)modelForAppWidgetId.valueAt(i)).hasModelBeenRemoved = true;
        }

        modelForAppWidgetId.clear();
    }

    public static void removeModel(int i)
    {
        MonthViewWidgetModel monthviewwidgetmodel = (MonthViewWidgetModel)modelForAppWidgetId.get(i);
        if (monthviewwidgetmodel != null)
        {
            monthviewwidgetmodel.hasModelBeenRemoved = true;
        }
        modelForAppWidgetId.remove(i);
    }

    public final void refreshData(boolean flag)
    {
        boolean flag1;
        if (itemsForJulianDay == null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            forceNewRefreshAfterDataLoading = forceNewRefreshAfterDataLoading | flag;
            return;
        } else
        {
            itemsForJulianDay = null;
            cachedGridModel = null;
            startDataLoadingAsync();
            return;
        }
    }

    final void startDataLoadingAsync()
    {
        forceNewRefreshAfterDataLoading = false;
        Object obj = context;
        MonthViewWidgetRangedData monthviewwidgetrangeddata = new MonthViewWidgetRangedData(startJulianDay, endJulianDay);
        boolean flag = Utils.getHideDeclinedEvents(context);
        obj = new MonthViewWidgetRangedQueryHandler(((Context) (obj)));
        (new MonthViewWidgetRangedQueryHandler.EventsLoader(((MonthViewWidgetRangedQueryHandler) (obj)), ((MonthViewWidgetRangedQueryHandler) (obj)).context, flag)).refreshData(monthviewwidgetrangeddata, monthviewwidgetrangeddata.startDay);
    }

}
