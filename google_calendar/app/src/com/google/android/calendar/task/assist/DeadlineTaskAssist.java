// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.calendar.time.clock.Clock;
import com.google.personalization.assist.annotate.AssistType;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.DeadlineAssistance;
import com.google.personalization.assist.annotate.api.EventTime;
import com.google.personalization.assist.annotate.api.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistHolder, TaskAssistanceUtils

public final class DeadlineTaskAssist extends TaskAssistHolder
{

    private static final long ONE_DAY_MS;
    private final String flair;

    public DeadlineTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1)
    {
        super(assistance, grammarruletype, s, s1);
        if (TaskAssistanceUtils.grammarRuleTypeToFlair.containsKey(grammarruletype))
        {
            assistance = (String)TaskAssistanceUtils.grammarRuleTypeToFlair.get(grammarruletype);
        } else
        {
            assistance = "default";
        }
        flair = assistance;
        mAnalyticsLabel = "deadline";
    }

    private final long getStartOfDayTimeMsInUserTimeZone(Time time, long l)
    {
        l = (time.timeMs_ + TimeUnit.MINUTES.toMillis(time.timeZoneOffsetMinutes_)) - l;
        if (time.dateOnly_)
        {
            return l;
        } else
        {
            return roundToStartOfDay(l);
        }
    }

    private static long roundToStartOfDay(long l)
    {
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        gregoriancalendar.setTimeInMillis(l);
        gregoriancalendar.set(11, 0);
        gregoriancalendar.set(12, 0);
        gregoriancalendar.set(13, 0);
        gregoriancalendar.set(14, 0);
        return gregoriancalendar.getTimeInMillis();
    }

    public final String getAssistActionText(Context context)
    {
        return null;
    }

    public final String getAssistInfoText(Context context)
    {
        Object obj1;
        long l;
        long l3;
        Object obj;
        int i;
        long l1;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        obj = assistance;
        if (((Assistance) (obj)).deadlineAssistance_ == null)
        {
            obj = DeadlineAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).deadlineAssistance_;
        }
        if (((DeadlineAssistance) (obj)).eventTime_ == null)
        {
            obj = EventTime.DEFAULT_INSTANCE;
        } else
        {
            obj = ((DeadlineAssistance) (obj)).eventTime_;
        }
        if (((EventTime) (obj)).startTime_ == null)
        {
            obj = Time.DEFAULT_INSTANCE;
        } else
        {
            obj = ((EventTime) (obj)).startTime_;
        }
        obj1 = assistance;
        if (((Assistance) (obj1)).deadlineAssistance_ == null)
        {
            obj1 = DeadlineAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj1 = ((Assistance) (obj1)).deadlineAssistance_;
        }
        if (((DeadlineAssistance) (obj1)).eventTime_ == null)
        {
            obj1 = EventTime.DEFAULT_INSTANCE;
        } else
        {
            obj1 = ((DeadlineAssistance) (obj1)).eventTime_;
        }
        if (((EventTime) (obj1)).endTime_ == null)
        {
            obj1 = Time.DEFAULT_INSTANCE;
        } else
        {
            obj1 = ((EventTime) (obj1)).endTime_;
        }
        l3 = roundToStartOfDay(l);
        l = TimeZone.getDefault().getOffset(l);
        l1 = getStartOfDayTimeMsInUserTimeZone(((Time) (obj)), l);
        if (l3 >= l1) goto _L2; else goto _L1
_L1:
        l = l1 - l3;
_L4:
        i = (int)TimeUnit.MILLISECONDS.toDays(l);
        return context.getResources().getQuantityString(0x7f12004a, i, new Object[] {
            Integer.valueOf(i)
        });
_L2:
        if (obj1 != null)
        {
            long l2 = getStartOfDayTimeMsInUserTimeZone(((Time) (obj1)), l);
            l = l2;
            if (!((Time) (obj1)).dateOnly_)
            {
                l = l2 + ONE_DAY_MS;
            }
            l2 = ONE_DAY_MS + l3;
            if (l2 > l)
            {
                l -= l2;
                continue; /* Loop/switch isn't completed */
            }
        }
        l = 0L;
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected final AssistType getAssistType()
    {
        return AssistType.UNKNOWN_ASSIST_TYPE;
    }

    public final String getDescription(Context context)
    {
        return context.getString(0x7f13016d);
    }

    public final String getDisplayText(Context context)
    {
        return getAssistInfoText(context);
    }

    public final int getIconId()
    {
        return 0x7f0201f3;
    }

    public final String getImageUrl()
    {
        return FlairAllocatorFactory.getAssistFlairUrlString(flair);
    }

    public final void gotoAssist(Context context)
    {
    }

    public final boolean isClickable(Context context)
    {
        return false;
    }

    static 
    {
        ONE_DAY_MS = TimeUnit.DAYS.toMillis(1L);
    }
}
