// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.os.Handler;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.FindTimeAttendee;
import com.google.android.calendar.timely.FindTimeUtil;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineSuggestion;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            FindTimeGridDayView, GridDayView

final class this._cls0 extends android.view.DayView.FindTimeGestureListener
{

    public final FindTimeGridDayView this$0;

    public final boolean onDown(MotionEvent motionevent)
    {
        return isSuggestionView;
    }

    public final boolean onSingleTapUp(MotionEvent motionevent)
    {
        if (!isSuggestionView)
        {
            return false;
        }
        final TimelineSuggestion customSuggestion = FindTimeGridDayView.this;
        int j = Math.min(((int)motionevent.getY() * 24) / customSuggestion.getHeight(), 23);
        customSuggestion = FindTimeGridDayView.this;
        float f = ((float)(int)motionevent.getY() * 24F) / (float)customSuggestion.getHeight();
        int i = (int)((f - (float)(int)f) * 60F);
        int l = j * 60 + i;
        String s;
        TimelineSuggestion timelinesuggestion;
        FindTimeUtil findtimeutil;
        int i1;
        long l1;
        long l2;
        long l3;
        if (i < 30)
        {
            i = 0;
        } else
        {
            i = 30;
        }
        findtimeutil = FindTimeUtil.getInstance(context);
        s = accountType;
        timelinesuggestion = suggestion;
        i1 = getJulianDay();
        customSuggestion = timezone;
        motionevent = customSuggestion;
        if (customSuggestion == null)
        {
            motionevent = Utils.getTimeZone(findtimeutil.context);
        }
        customSuggestion = new Time();
        customSuggestion.timezone = motionevent.getID();
        l1 = Time.calculateDayHourMinuteMillis(i1, j, i, ((Time) (customSuggestion)).timezone, customSuggestion);
        l2 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcEndMillis();
        l3 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcStartMillis();
        customSuggestion = new TimelineSuggestion(timelinesuggestion);
        customSuggestion.isCustom = true;
        customSuggestion.timeRange = TimeRange.newInstance(motionevent, false, l1, (l2 - l3) + l1);
        if (AccountUtil.isGoogleExchangeType(s) || AccountUtil.isGoogleExchangeGoAccount(s))
        {
            ImmutableList immutablelist = customSuggestion.attendees;
            motionevent = new ArrayList();
            immutablelist = (ImmutableList)immutablelist;
            int k = immutablelist.size();
            i = 0;
label0:
            do
            {
                if (i >= k)
                {
                    break;
                }
                Object obj = immutablelist.get(i);
                i++;
                obj = (FindTimeAttendee)obj;
                int j1 = ((TimelineEvent) (customSuggestion)).timeRange.getStartDay();
                Object obj1 = (List)((FindTimeAttendee) (obj)).daysToEvents.get(j1);
                if (obj1 == null)
                {
                    continue;
                }
                obj1 = ((List) (obj1)).iterator();
                TimelineEvent timelineevent;
                do
                {
                    if (!((Iterator) (obj1)).hasNext())
                    {
                        continue label0;
                    }
                    timelineevent = (TimelineEvent)((Iterator) (obj1)).next();
                } while (!((TimelineEvent) (customSuggestion)).timeRange.intersects(timelineevent.timeRange));
                motionevent.add(obj);
            } while (true);
            customSuggestion.attendeeExplanations = FindTimeUtil.convertToExplanationForExchange(motionevent);
        }
        boolean flag;
        if (l < ((TimelineEvent) (suggestion)).timeRange.getStartMinute())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (((TimelineEvent) (suggestion)).timeRange.getEndDay() == getJulianDay())
        {
            if (((TimelineEvent) (suggestion)).timeRange.getEndMinute() <= l)
            {
                i = 1;
            } else
            {
                i = 0;
            }
        } else
        {
            i = 0;
        }
        if (flag || i)
        {
            playSoundEffect(0);
            class _cls1
                implements Runnable
            {

                private final FindTimeGridDayView.FindTimeGestureListener this$1;
                private final TimelineSuggestion val$customSuggestion;

                public final void run()
                {
                    onUpdate(customSuggestion, getJulianDay(), timezone);
                    Object obj2 = CustomUserSuggestionListenerHolder.INSTANCE;
                    TimelineSuggestion timelinesuggestion1 = customSuggestion;
                    obj2 = ((CustomUserSuggestionListenerHolder) (obj2)).createCustomSuggestionChangedListeners.iterator();
                    do
                    {
                        if (!((Iterator) (obj2)).hasNext())
                        {
                            break;
                        }
                        com.google.android.calendar.timely.CustomUserSuggestionListenerHolder.OnCreateCustomSuggestionListener oncreatecustomsuggestionlistener = (com.google.android.calendar.timely.CustomUserSuggestionListenerHolder.OnCreateCustomSuggestionListener)((Iterator) (obj2)).next();
                        if (oncreatecustomsuggestionlistener != null)
                        {
                            oncreatecustomsuggestionlistener.onCreateCustomUserSuggestionChanged(timelinesuggestion1);
                        }
                    } while (true);
                }

            _cls1()
            {
                this$1 = FindTimeGridDayView.FindTimeGestureListener.this;
                customSuggestion = timelinesuggestion;
                super();
            }
            }

            ((ViewGroup)getParent()).getHandler().post(new _cls1());
        }
        return true;
    }

    _cls1()
    {
        this$0 = FindTimeGridDayView.this;
        super();
    }
}
