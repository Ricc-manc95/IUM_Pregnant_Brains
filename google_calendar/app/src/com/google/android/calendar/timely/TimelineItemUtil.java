// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.task.assist.TaskAssistHolder;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.geometry.TimelineSegment;
import com.google.android.calendar.utils.ColorUtils;
import com.google.calendar.v2a.android.util.time.TimestampUtil;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineTask, TimelineItem, TimelineTaskType, TimelineTaskBundle, 
//            TimelineGroove, TimelineEvent, TimelineBirthday, TimelineHoliday, 
//            TimelineExternalEvent, TimelineItemOperation

public class TimelineItemUtil
{
    static final class ResolveNewApiObjectTask extends TimelineItemOperation
    {

        public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
        {
            return CalendarApi.Events.read((EventKey)timelineevent.eventKey);
        }

        public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
        {
            return CalendarApi.Events.read((EventKey)((TimelineEvent) (timelinegroove)).eventKey);
        }

        ResolveNewApiObjectTask()
        {
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/TimelineItemUtil);

    public TimelineItemUtil()
    {
    }

    public static String createContentDescription(Context context, TimelineItem timelineitem, boolean flag, String s)
    {
        return createContentDescription(context, timelineitem, false, null, null, s);
    }

    public static String createContentDescription(Context context, TimelineItem timelineitem, boolean flag, String s, String s1, String s2)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Resources resources = context.getResources();
        if (!TextUtils.isEmpty(s))
        {
            stringbuilder.append(s);
            stringbuilder.append(", ");
        }
        if (timelineitem instanceof TimelineTask)
        {
            if (timelineitem.hasDeclinedStatus())
            {
                stringbuilder.append(resources.getString(0x7f1303df));
            } else
            {
                stringbuilder.append(resources.getString(0x7f1303de));
            }
            stringbuilder.append(" ");
        } else
        if (timelineitem instanceof TimelineTaskBundle)
        {
            s = (TimelineTaskBundle)timelineitem;
            i = ((TimelineTaskBundle) (s)).timelineTaskList.size();
            if (((TimelineTaskBundle) (s)).done)
            {
                stringbuilder.append(resources.getQuantityString(0x7f12003c, i, new Object[] {
                    Integer.valueOf(i)
                })).append(" ");
            } else
            {
                stringbuilder.append(resources.getQuantityString(0x7f12003b, i, new Object[] {
                    Integer.valueOf(i)
                })).append(" ");
            }
        } else
        if (timelineitem instanceof TimelineGroove)
        {
            if (((TimelineGroove)timelineitem).completed)
            {
                stringbuilder.append(resources.getString(0x7f1302b2));
            } else
            {
                stringbuilder.append(resources.getString(0x7f1302b1));
            }
            stringbuilder.append(": ");
        } else
        {
            if ((timelineitem instanceof TimelineEvent) && ((TimelineEvent)timelineitem).isOutOfOffice())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                stringbuilder.append(resources.getString(0x7f130371)).append(" ");
            } else
            if (isEveryoneDeclinedEvent(timelineitem))
            {
                stringbuilder.append(resources.getString(0x7f1301e0));
            }
        }
        if (timelineitem.hasDeclinedStatus() && !(timelineitem instanceof TimelineTaskType))
        {
            stringbuilder.append(context.getString(0x7f130055));
            stringbuilder.append(": ");
        }
        if (timelineitem.isAllDay() && (!(timelineitem instanceof TimelineTaskType) || !timelineitem.hasDeclinedStatus()))
        {
            stringbuilder.append(resources.getString(0x7f13009d));
            stringbuilder.append(" ");
        }
        if (!timelineitem.isAllDay())
        {
            long l1 = timelineitem.getStartMillis();
            int i;
            long l;
            if (timelineitem.showEndTime())
            {
                l = timelineitem.getEndMillis();
            } else
            {
                l = l1;
            }
            Object obj;
            byte byte0;
            if (timelineitem.showEndTime() && timelineitem.getStartDay() != timelineitem.getEndDay())
            {
                i = 17;
            } else
            {
                i = 1;
            }
            s = DateTimeFormatHelper.instance;
            if (s == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            s = (DateTimeFormatHelper)s;
            if ((i & 0x10) == 0)
            {
                byte0 = 8;
            } else
            {
                byte0 = 0;
            }
            obj = Utils.getAccessibilityDateTimes(((DateTimeFormatHelper) (s)).context, byte0, false, l1, l, Utils.getTimeZoneId(((DateTimeFormatHelper) (s)).context, null));
            if (obj != null)
            {
                s = (String)obj;
            } else
            {
                s = s.getDateRangeText(l1, l, i);
            }
            stringbuilder.append(s);
            stringbuilder.append(": ");
        } else
        if (timelineitem.getStartDay() != timelineitem.getEndDay())
        {
            s = new Time("UTC");
            obj = Utils.getTimeZoneId(context);
            l = Utils.convertAlldayUtcToLocal(s, timelineitem.getStartMillis(), ((String) (obj)));
            l1 = Utils.convertAlldayUtcToLocal(s, timelineitem.getEndMillis(), ((String) (obj)));
            s = DateTimeFormatHelper.instance;
            if (s == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            stringbuilder.append(((DateTimeFormatHelper)s).getDateRangeText(l, l1, 16));
            stringbuilder.append(": ");
        }
        if (timelineitem.getTitle() != null)
        {
            if ((timelineitem instanceof TimelineBirthday) && !flag)
            {
                stringbuilder.append(((TimelineBirthday)timelineitem).singleLineTitle);
            } else
            {
                s = s2;
                if (timelineitem instanceof TimelineTaskBundle)
                {
                    s = s2;
                    if (timelineitem.hasDeclinedStatus())
                    {
                        s = ((TimelineTaskBundle)timelineitem).getAllTitles();
                    }
                }
                stringbuilder.append(s);
                if (timelineitem instanceof TimelineBirthday)
                {
                    s = (TimelineBirthday)timelineitem;
                    if (!TextUtils.isEmpty(((TimelineBirthday) (s)).subtitle))
                    {
                        stringbuilder.append(", ");
                        stringbuilder.append(((TimelineBirthday) (s)).subtitle);
                    }
                }
            }
        }
        s = timelineitem.getLocation();
        if (!TextUtils.isEmpty(s))
        {
            stringbuilder.append(", ");
            stringbuilder.append(s);
        }
        if (ExperimentalOptions.isProposeNewTimeEnabled(context) && (timelineitem instanceof TimelineEvent) && ((TimelineEvent)timelineitem).shouldShowTimeProposalIcon())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            stringbuilder.append(", ");
            stringbuilder.append(resources.getString(0x7f130066));
        }
        if (!TextUtils.isEmpty(s1))
        {
            stringbuilder.append(", ");
            stringbuilder.append(s1);
        }
        return stringbuilder.toString();
    }

    public static String getAssistActionText(TimelineItem timelineitem, Context context)
    {
        Object obj = null;
        String s = obj;
        if (timelineitem instanceof TimelineTask)
        {
            timelineitem = (TimelineTask)timelineitem;
            boolean flag;
            if (((TimelineTask) (timelineitem)).taskAssistHolder != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            s = obj;
            if (flag)
            {
                s = obj;
                if (((TimelineTask) (timelineitem)).taskAssistHolder.isClickable(context))
                {
                    s = ((TimelineTask) (timelineitem)).taskAssistHolder.getAssistActionText(context);
                }
            }
        }
        return s;
    }

    public static String getAssistInfoText(TimelineItem timelineitem, Context context)
    {
        Object obj = null;
        String s = obj;
        if (timelineitem instanceof TimelineTask)
        {
            timelineitem = (TimelineTask)timelineitem;
            boolean flag;
            if (((TimelineTask) (timelineitem)).taskAssistHolder != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            s = obj;
            if (flag)
            {
                s = ((TimelineTask) (timelineitem)).taskAssistHolder.getAssistInfoText(context);
            }
        }
        return s;
    }

    public static com.google.android.calendar.timeline.chip.ChipViewModel.ColorStyle getColorStyle(TimelineItem timelineitem)
    {
        com.google.android.calendar.timeline.chip.ChipViewModel.ColorStyle colorstyle = com.google.android.calendar.timeline.chip.ChipViewModel.ColorStyle.REGULAR;
        boolean flag;
        if ((timelineitem instanceof TimelineEvent) && ((TimelineEvent)timelineitem).isOutOfOffice())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return com.google.android.calendar.timeline.chip.ChipViewModel.ColorStyle.LIGHT;
        }
        if ((timelineitem instanceof TimelineEvent) && (timelineitem.hasDeclinedStatus() || timelineitem.hasInvitedStatus() || isEveryoneDeclinedEvent(timelineitem)))
        {
            return com.google.android.calendar.timeline.chip.ChipViewModel.ColorStyle.INVERTED;
        } else
        {
            return colorstyle;
        }
    }

    public static int getDayIndex(TimelineSegment timelinesegment, int i)
    {
        return (i - timelinesegment.getStartDay()) + 1;
    }

    public static long getDurationHours(TimelineSegment timelinesegment)
    {
        return (timelinesegment.getEndMillis() - timelinesegment.getStartMillis()) / 0x36ee80L;
    }

    public static int getMinutesOn(TimelineItem timelineitem, int i)
    {
        timelineitem = timelineitem.getTimeRange();
        TimeRange timerange = TimeRange.newAllDayFromJulianDay(timelineitem.getTimeZone(), i, i);
        if (timerange.intersects(timelineitem))
        {
            if (timerange.isAllDay() && timelineitem.isAllDay())
            {
                timelineitem = TimeRange.newAllDayFromJulianDay(timerange.getTimeZone(), Math.max(timerange.getStartDay(), timelineitem.getStartDay()), Math.min(timerange.getEndDay(), timelineitem.getEndDay()));
            } else
            {
                timelineitem = TimeRange.newNonAllDay(timerange.getTimeZone(), Math.max(timerange.getLocalStartMillis(), timelineitem.getLocalStartMillis()), Math.min(timerange.getLocalEndMillis(), timelineitem.getLocalEndMillis()));
            }
            return timelineitem.getEndMinute() - timelineitem.getStartMinute();
        } else
        {
            return 0;
        }
    }

    public static int getNumIntersectingDays(TimelineSegment timelinesegment)
    {
        return (timelinesegment.getEndDay() - timelinesegment.getStartDay()) + 1;
    }

    public static int getTimelineItemColor(TimelineItem timelineitem)
    {
        boolean flag;
        if ((timelineitem instanceof TimelineEvent) && ((TimelineEvent)timelineitem).isOutOfOffice())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return ColorUtils.blend(timelineitem.getColor(), -1, 0.88F);
        } else
        {
            return timelineitem.getColor();
        }
    }

    public static boolean hasAssist(TimelineItem timelineitem)
    {
        if (timelineitem instanceof TimelineTask)
        {
            boolean flag;
            if (((TimelineTask)timelineitem).taskAssistHolder != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyReminder(TimelineItem timelineitem)
    {
        return timelineitem instanceof TimelineTaskType;
    }

    public static boolean isBirthday(TimelineItem timelineitem)
    {
        return timelineitem instanceof TimelineBirthday;
    }

    public static boolean isEveryoneDeclinedEvent(TimelineItem timelineitem)
    {
        if (timelineitem instanceof TimelineEvent)
        {
            timelineitem = (TimelineEvent)timelineitem;
            boolean flag;
            if (RemoteFeatureConfig.EVERYONE_DECLINED.enabled() && ((TimelineEvent) (timelineitem)).showEveryoneDeclined)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isFirstDay(TimelineSegment timelinesegment, int i)
    {
        return timelinesegment.getStartDay() == i;
    }

    public static boolean isGroove(TimelineItem timelineitem)
    {
        return timelineitem instanceof TimelineGroove;
    }

    public static boolean isHoliday(TimelineItem timelineitem)
    {
        return timelineitem instanceof TimelineHoliday;
    }

    public static boolean isItemInPast(TimelineItem timelineitem, long l, String s)
    {
        if (!timelineitem.isAllDay()) goto _L2; else goto _L1
_L1:
        if (Utils.convertAlldayUtcToLocal(new Time(), timelineitem.getEndMillis(), s) >= l) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (timelineitem.showEndTime())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (timelineitem.getStartMillis() + 0x36ee80L < l) goto _L3; else goto _L5
_L5:
        return false;
        if (timelineitem.getEndMillis() < l) goto _L3; else goto _L6
_L6:
        return false;
    }

    public static boolean isLastDay(TimelineSegment timelinesegment, int i)
    {
        return timelinesegment.getEndDay() == i;
    }

    public static boolean isOutOfOfficeEvent(TimelineItem timelineitem)
    {
        return (timelineitem instanceof TimelineEvent) && ((TimelineEvent)timelineitem).isOutOfOffice();
    }

    public static boolean isProposeNewTimeEvent(TimelineItem timelineitem, Context context)
    {
        return ExperimentalOptions.isProposeNewTimeEnabled(context) && (timelineitem instanceof TimelineEvent) && ((TimelineEvent)timelineitem).shouldShowTimeProposalIcon();
    }

    public static boolean isReminder(TimelineItem timelineitem)
    {
        return timelineitem instanceof TimelineTask;
    }

    public static boolean isReminderBundle(TimelineItem timelineitem)
    {
        return timelineitem instanceof TimelineTaskBundle;
    }

    public static boolean isTimedMidnightToNextMidnight(TimelineItem timelineitem)
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (!((FeatureConfig)featureconfig).ooo())
        {
            return false;
        } else
        {
            TimeZone timezone = TimeZone.getDefault();
            return Utils.isTimedMidnightToNextMidnightEvent(timelineitem.getStartMillis(), timelineitem.getEndMillis(), timezone.getOffset(timelineitem.getStartMillis()) / 1000, timezone.getOffset(timelineitem.getEndMillis()) / 1000, false);
        }
    }

    public static boolean isTransparent(TimelineItem timelineitem)
    {
        return (timelineitem instanceof TimelineEvent) && ((TimelineEvent)timelineitem).isTransparent;
    }

    public static TimelineItem readTimelineItemFromIntent(Context context, Intent intent)
    {
        if (intent != null) goto _L2; else goto _L1
_L1:
        Object obj1 = null;
_L7:
        return ((TimelineItem) (obj1));
_L2:
        Object obj = intent.getBundleExtra("key_timeline_item");
        if (obj == null) goto _L4; else goto _L3
_L3:
        obj = ((Bundle) (obj)).getParcelable("key_timeline_item");
        if (obj == null || !(obj instanceof TimelineItem)) goto _L4; else goto _L5
_L5:
        obj = (TimelineItem)obj;
_L18:
        obj1 = obj;
        if (obj != null) goto _L7; else goto _L6
_L6:
        int i;
        long l;
        long l2;
        long l6;
        long l8;
        long l9;
        boolean flag;
        l = intent.getLongExtra("beginTime", 0L);
        l2 = intent.getLongExtra("endTime", 0L);
        flag = intent.getBooleanExtra("allDay", false);
        i = intent.getIntExtra("attendeeStatus", 0);
        l9 = -1L;
        obj = intent.getData();
        l6 = l9;
        l8 = l;
        if (obj == null) goto _L9; else goto _L8
_L8:
        long l1;
        long l3;
        l1 = l9;
        l3 = l;
        obj1 = ((Uri) (obj)).getPathSegments();
        if (obj1 == null) goto _L11; else goto _L10
_L10:
        l1 = l9;
        l3 = l;
        int j = ((List) (obj1)).size();
        if (j <= 2) goto _L13; else goto _L12
_L12:
        l1 = l9;
        l3 = l;
        if (!"EventTime".equals(((List) (obj1)).get(2))) goto _L13; else goto _L14
_L14:
        l1 = l9;
        l3 = l;
        l6 = Long.parseLong((String)((List) (obj1)).get(1));
        if (j <= 4) goto _L16; else goto _L15
_L15:
        l1 = l6;
        l3 = l;
        l = Long.parseLong((String)((List) (obj1)).get(3));
        l1 = l6;
        l3 = l;
        l8 = Long.parseLong((String)((List) (obj1)).get(4));
        l2 = l8;
        l1 = l;
        l = l2;
_L19:
        l2 = l6;
          goto _L17
_L4:
        obj = null;
          goto _L18
_L13:
        l1 = l9;
        l3 = l;
        l6 = Long.parseLong(((Uri) (obj)).getLastPathSegment());
        l1 = l6;
        break MISSING_BLOCK_LABEL_569;
_L11:
        l1 = l9;
        l3 = l;
        l6 = Long.parseLong(((Uri) (obj)).getLastPathSegment());
        l1 = l;
        l = l2;
          goto _L19
        numberformatexception;
        l6 = l1;
        l8 = l3;
        if (l1 == -1L) goto _L9; else goto _L20
_L20:
        if (l3 == 0L) goto _L22; else goto _L21
_L21:
        l6 = l1;
        l8 = l3;
        if (l2 != 0L) goto _L9; else goto _L22
_L22:
        flag = false;
        l = 0L;
        long l4 = 0L;
        l2 = l1;
        l1 = l4;
_L17:
        NumberFormatException numberformatexception;
        if (l2 <= 0L && !intent.hasExtra("eventkey"))
        {
            LogUtils.w(TAG, "No item id", new Object[0]);
            return null;
        }
        if (intent.hasExtra("eventkey"))
        {
            intent = EventKey.deserialize(intent.getStringExtra("eventkey"));
        } else
        if (l1 > 0L)
        {
            intent = CpEventKey.newInstance(l2, l1);
        } else
        {
            intent = CpEventKey.newInstance(l2);
        }
        context = Utils.getTimeZone(context);
        if (flag)
        {
            String s = context.getID();
            l1 = TimestampUtil.roundToMidnight(l1, s, true, "UTC");
            l2 = TimestampUtil.roundToMidnight(l, s, true, "UTC");
            l = l1;
            l1 = l2;
        } else
        {
            l2 = l;
            l = l1;
            l1 = l2;
        }
        return new TimelineExternalEvent(intent, TimeRange.newInstance(context, flag, l, Math.max(l, l1)), i);
_L9:
        l = l2;
        l1 = l8;
        l2 = l6;
        continue; /* Loop/switch isn't completed */
_L16:
        l1 = l6;
        long l5 = l;
        long l7 = l1;
        l = l2;
        l1 = l5;
        l2 = l7;
        if (true) goto _L17; else goto _L23
_L23:
          goto _L18
    }

    public static ListenableFuture resolveNewApiObject(TimelineItem timelineitem)
    {
        return (ListenableFuture)timelineitem.perform(new ResolveNewApiObjectTask(), new Void[0]);
    }

    public static void setLaunchTimelineItem(Intent intent, TimelineItem timelineitem)
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_timeline_item", timelineitem);
        intent.putExtra("key_timeline_item", bundle);
    }

    public static boolean shouldItemBeRenderedAsMultiday(TimelineSegment timelinesegment)
    {
        long l1 = timelinesegment.getEndMillis();
        long l2 = timelinesegment.getStartMillis();
        TimeUnit timeunit = TimeUnit.HOURS;
        long l;
        if (timelinesegment.isAllDay())
        {
            l = 45L;
        } else
        {
            l = 24L;
        }
        return l1 - l2 > timeunit.toMillis(l);
    }

    public static boolean useBadgedIcon(TimelineItem timelineitem)
    {
        return (timelineitem instanceof TimelineEvent) && ((TimelineEvent)timelineitem).isOutOfOffice();
    }

}
