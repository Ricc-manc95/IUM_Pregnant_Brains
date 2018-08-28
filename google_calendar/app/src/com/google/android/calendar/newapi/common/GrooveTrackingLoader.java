// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.groove.GrooveUtils;
import com.google.android.calendar.groove.stats.GrooveStats;
import com.google.android.calendar.newapi.segment.tracking.GrooveTrackingData;
import com.google.android.calendar.newapi.segment.tracking.TrackingIntervalData;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            AsyncTaskLoader

public class GrooveTrackingLoader extends AsyncTaskLoader
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/common/GrooveTrackingLoader);
    public final Context context;
    private final Habit habit;
    private final TimelineGroove timelineItem;

    public GrooveTrackingLoader(Context context1, Habit habit1, TimelineGroove timelinegroove)
    {
        context = context1;
        habit = habit1;
        timelineItem = timelinegroove;
    }

    private final transient GrooveTrackingData runInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2URJ5ETGN0Q9FEDIMERB5DPQ2UT3IC5HMMQBECSNKESJFDTR6AL3IC5HMMQBECT262T317C______0()
    {
        List list;
        GrooveTrackingData groovetrackingdata;
        int j;
        groovetrackingdata = new GrooveTrackingData(habit);
        j = habit.getContract().getInterval();
        list = Collections.emptyList();
        Object obj;
        String s;
        Object obj2;
        obj = timelineItem.descriptor.calendar.account;
        s = timelineItem.descriptor.habitId;
        obj2 = Calendar.getInstance(Utils.getTimeZone(context));
        ((Calendar) (obj2)).setTimeInMillis(((TimelineEvent) (timelineItem)).timeRange.getUtcStartMillis());
        long al1[];
        byte byte0;
        if (j == 3)
        {
            byte0 = 2;
        } else
        {
            byte0 = 4;
        }
        al1 = GrooveUtils.getIntervalStartAndEnd(context, j, ((Calendar) (obj2)).getTimeInMillis());
        if (j != 3) goto _L2; else goto _L1
_L1:
        ((Calendar) (obj2)).add(2, -(byte0 - 1));
_L4:
        obj2 = GrooveUtils.getIntervalStartAndEnd(context, j, ((Calendar) (obj2)).getTimeInMillis());
        class .Lambda._cls0
            implements Supplier
        {

            private final GrooveTrackingLoader arg$1;

            public final Object get()
            {
                return Utils.getTimeZone(arg$1.context);
            }

            .Lambda._cls0()
            {
                arg$1 = GrooveTrackingLoader.this;
            }
        }

        obj = (List)Futures.getUnchecked((new EventsApiImpl(context, new .Lambda._cls0())).searchGoalsAsync(((android.accounts.Account) (obj)), s, obj2[0], al1[1]));
        list = ((List) (obj));
        break MISSING_BLOCK_LABEL_185;
_L2:
        try
        {
            ((Calendar) (obj2)).add(5, (byte0 - 1) * -7);
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (Object obj1)
        {
            LogUtils.e(TAG, ((Throwable) (obj1)), "Failed to load habits.", new Object[0]);
        }
        Object obj1 = Calendar.getInstance(Utils.getTimeZone(context));
        ((Calendar) (obj1)).setTimeInMillis(((TimelineEvent) (timelineItem)).timeRange.getUtcStartMillis());
        int i;
        if (j == 3)
        {
            byte0 = 2;
        } else
        {
            byte0 = 4;
        }
        i = 0;
        while (i < byte0) 
        {
            long al[] = GrooveUtils.getIntervalStartAndEnd(context, j, ((Calendar) (obj1)).getTimeInMillis());
            ArrayList arraylist = groovetrackingdata.intervalDataList;
            obj2 = GrooveUtils.getStats(list, al[0], al[1]);
            arraylist.add(new TrackingIntervalData(al[0], ((GrooveStats) (obj2)).getCompletedSessions()));
            if (j == 3)
            {
                ((Calendar) (obj1)).add(2, -1);
            } else
            {
                ((Calendar) (obj1)).add(5, -7);
            }
            i++;
        }
        return groovetrackingdata;
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected final volatile Object runInBackground(Object aobj[])
    {
        return runInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2URJ5ETGN0Q9FEDIMERB5DPQ2UT3IC5HMMQBECSNKESJFDTR6AL3IC5HMMQBECT262T317C______0();
    }

}
