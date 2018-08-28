// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineHoliday;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;

final class WidgetRow
{

    static Chip createChip(TimelineItem timelineitem, int i, DateTimeFormatHelper datetimeformathelper)
    {
        if (TimelineItemUtil.isReminder(timelineitem))
        {
            if (timelineitem.isAllDay())
            {
                return new TaskAllDayChip(timelineitem, datetimeformathelper);
            } else
            {
                return new TaskTimedChip(timelineitem, datetimeformathelper);
            }
        }
        if (TimelineItemUtil.isReminderBundle(timelineitem))
        {
            return new TaskBundleChip(timelineitem, datetimeformathelper);
        }
        if (TimelineItemUtil.isGroove(timelineitem))
        {
            return new GrooveChip(timelineitem, datetimeformathelper);
        }
        if (TimelineItemUtil.isHoliday(timelineitem))
        {
            return new HolidayChip((TimelineHoliday)timelineitem, datetimeformathelper);
        }
        if (TimelineItemUtil.isBirthday(timelineitem))
        {
            return new BirthdayChip((TimelineBirthday)timelineitem, datetimeformathelper);
        }
        if (timelineitem.isAllDay() || TimelineItemUtil.isTimedMidnightToNextMidnight(timelineitem))
        {
            return new AllDayChip(timelineitem, datetimeformathelper);
        }
        if (timelineitem.getStartDay() < i && i < timelineitem.getEndDay())
        {
            return new RegularChipOne(timelineitem, datetimeformathelper);
        } else
        {
            return new RegularChipTwo(timelineitem, datetimeformathelper);
        }
    }

    private class TaskAllDayChip extends AllDayChip
    {
        private class AllDayChip extends RegularChipOne
        {
            private class RegularChipOne extends Chip
            {
                private class Chip extends Row
                {
                    private class Row
                    {

                        public boolean isRtl;

                        static void updateIcon(RemoteViews remoteviews, int i, int j, boolean flag, int k)
                        {
label0:
                            {
                                boolean flag2 = false;
                                boolean flag1;
                                int l;
                                if (j != 0)
                                {
                                    flag1 = true;
                                } else
                                {
                                    flag1 = false;
                                }
                                if (flag1 && !flag)
                                {
                                    l = 0;
                                } else
                                {
                                    l = 8;
                                }
                                remoteviews.setViewVisibility(0x7f10039e, l);
                                if (flag1 && flag)
                                {
                                    l = ((flag2) ? 1 : 0);
                                } else
                                {
                                    l = 8;
                                }
                                remoteviews.setViewVisibility(0x7f10039b, l);
                                if (flag1)
                                {
                                    if (!flag)
                                    {
                                        break label0;
                                    }
                                    remoteviews.setImageViewResource(0x7f10039d, j);
                                    remoteviews.setInt(0x7f10039d, "setColorFilter", -1);
                                    remoteviews.setInt(0x7f10039c, "setColorFilter", k);
                                }
                                return;
                            }
                            remoteviews.setImageViewResource(0x7f10039e, j);
                            remoteviews.setInt(0x7f10039e, "setColorFilter", i);
                        }

                        public abstract int getLayoutId(WidgetViewContext widgetviewcontext);

                        public void updateStatus(WidgetViewContext widgetviewcontext, RemoteViews remoteviews)
                        {
                            isRtl = RtlUtils.isLayoutDirectionRtl(widgetviewcontext.context);
                        }

                        final void updateTextView(RemoteViews remoteviews, int i, int j, CharSequence charsequence, int k)
                        {
                            remoteviews.setViewVisibility(i, 0);
                            remoteviews.setTextViewText(i, RtlUtils.forceTextAlignment(charsequence, isRtl));
                            remoteviews.setTextColor(i, k);
                        }

                        Row()
                        {
                        }

                        private class WidgetViewContext
                        {

                            public final Context context;
                            public final int darkDayColor;
                            public final int firstDayColor;
                            public final int greyTextColor;
                            public final int lightDayColor;
                            public final String noMoreEventsToday;
                            public final String timeLocation;
                            public final int whiteTextColor;
                            public final int widgetStyle;

                            WidgetViewContext(Context context1, int i)
                            {
                                context = context1;
                                widgetStyle = i;
                                whiteTextColor = ContextCompat.getColor(context1, 0x7f0d0336);
                                greyTextColor = ContextCompat.getColor(context1, 0x7f0d021e);
                                firstDayColor = ContextCompat.getColor(context1, 0x7f0d0338);
                                lightDayColor = ContextCompat.getColor(context1, 0x7f0d0339);
                                darkDayColor = ContextCompat.getColor(context1, 0x7f0d0337);
                                timeLocation = context1.getString(0x7f130482);
                                noMoreEventsToday = context1.getString(0x7f130353);
                            }
                        }

                    }


                    public DayInfo dayInfo;
                    public boolean isFirst;
                    public final TimelineItem item;

                    final Intent createTimelineItemFillIntent(Context context, Object obj)
                    {
                        context = LaunchInfoActivityUtils.getLaunchFillInIntent(context, obj);
                        TimelineItemUtil.setLaunchTimelineItem(context, item);
                        context.putExtra("beginTime", item.getStartMillis());
                        context.putExtra("allDay", true);
                        return context;
                    }

                    protected String getTitleDescription(WidgetViewContext widgetviewcontext, boolean flag)
                    {
                        return item.getTitle();
                    }

                    public abstract void setOnClickFillInIntent(Context context, RemoteViews remoteviews);

                    public void updateStatus(WidgetViewContext widgetviewcontext, RemoteViews remoteviews)
                    {
                        int i;
                        int j;
                        boolean flag = false;
                        super.updateStatus(widgetviewcontext, remoteviews);
                        com.google.android.calendar.timeline.chip.ChipViewModel.ColorStyle colorstyle;
                        int k;
                        if (TimelineItemUtil.isOutOfOfficeEvent(item))
                        {
                            i = TimelineItemUtil.getTimelineItemColor(item);
                        } else
                        {
                            i = ColorUtils.getDisplayColorFromColor(item.getColor());
                        }
                        colorstyle = TimelineItemUtil.getColorStyle(item);
                        switch (colorstyle.ordinal())
                        {
                        default:
                            j = 0x7f020281;
                            break;

                        case 1: // '\001'
                            break MISSING_BLOCK_LABEL_193;
                        }
_L1:
                        k = WidgetUtils.getChipTextColor(colorstyle, i, widgetviewcontext.whiteTextColor, widgetviewcontext.greyTextColor);
                        remoteviews.setImageViewResource(0x7f100399, j);
                        remoteviews.setInt(0x7f100399, "setColorFilter", i);
                        updateTextViews(widgetviewcontext, remoteviews, k);
                        remoteviews.setContentDescription(0x7f100398, TimelineItemUtil.createContentDescription(widgetviewcontext.context, item, false, "", "", getTitleDescription(widgetviewcontext, true)));
                        if (isFirst)
                        {
                            i = ((flag) ? 1 : 0);
                        } else
                        {
                            i = 8;
                        }
                        remoteviews.setViewVisibility(0x7f1003a0, i);
                        remoteviews.setViewVisibility(0x7f1003a1, i);
                        if (i == 0)
                        {
                            dayInfo.updateStatus(widgetviewcontext, remoteviews);
                        }
                        return;
                        j = 0x7f020283;
                          goto _L1
                    }

                    public abstract void updateTextViews(WidgetViewContext widgetviewcontext, RemoteViews remoteviews, int i);

                    Chip(TimelineItem timelineitem)
                    {
                        item = timelineitem;
                    }

                    private class DayInfo
                    {

                        private final String description;
                        private final boolean isToday;
                        public final int julianDay;
                        private final String monthDayLabel;
                        private final String weekdayLabel;

                        final void updateStatus(WidgetViewContext widgetviewcontext, RemoteViews remoteviews)
                        {
                            int i = PreferencesConstants.getAlternateCalendarPref(widgetviewcontext.context);
                            Object obj = weekdayLabel;
                            if (i != 0)
                            {
                                obj = AlternateCalendarUtils.getAlternateDayOfMonthString(julianDay, widgetviewcontext.context.getResources(), i);
                                Time time = new Time();
                                int j = julianDay;
                                time.writeFieldsToImpl();
                                time.impl.setJulianDay(j);
                                time.copyFieldsFromImpl();
                                StringBuilder stringbuilder = new StringBuilder();
                                time.writeFieldsToImpl();
                                obj = stringbuilder.append(Utils.getShortDayOfWeek(new Date(time.impl.toMillis(false)))).append("(").append(((String) (obj))).append(")");
                            }
                            remoteviews.setTextViewText(0x7f1003a0, monthDayLabel);
                            remoteviews.setTextViewText(0x7f1003a1, ((CharSequence) (obj)));
                            obj = widgetviewcontext.context.getResources();
                            if (i == 0)
                            {
                                i = 0x7f0e03f9;
                            } else
                            {
                                i = 0x7f0e03fa;
                            }
                            remoteviews.setTextViewTextSize(0x7f1003a1, 0, ((Resources) (obj)).getDimensionPixelSize(i));
                            if (isToday)
                            {
                                remoteviews.setTextColor(0x7f1003a0, widgetviewcontext.firstDayColor);
                                remoteviews.setTextColor(0x7f1003a1, widgetviewcontext.firstDayColor);
                            } else
                            {
                                remoteviews.setTextColor(0x7f1003a0, widgetviewcontext.darkDayColor);
                                remoteviews.setTextColor(0x7f1003a1, widgetviewcontext.lightDayColor);
                            }
                            remoteviews.setContentDescription(0x7f10039f, description);
                        }

                        DayInfo(int i, int j, Time time)
                        {
                            julianDay = j;
                            time.writeFieldsToImpl();
                            time.impl.setJulianDay(j);
                            time.copyFieldsFromImpl();
                            Object obj = NumberFormat.getNumberInstance().format(time.monthDay);
                            String s = DateUtils.getDayOfWeekString(time.weekDay + 1, 20);
                            monthDayLabel = ((String) (obj));
                            weekdayLabel = s;
                            boolean flag;
                            if (i == j)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            isToday = flag;
                            obj = WidgetDateUtils.instance;
                            if (obj == null)
                            {
                                throw new NullPointerException(String.valueOf("WidgetDateUtils#initialize(...) must be called first"));
                            } else
                            {
                                description = ((WidgetDateUtils)obj).getWidgetDayDividerText(j, time, i);
                                return;
                            }
                        }
                    }

                }


                public int getLayoutId(WidgetViewContext widgetviewcontext)
                {
                    if (widgetviewcontext.widgetStyle == 1)
                    {
                        return 0x7f050187;
                    }
                    return isFirst ? 0x7f050189 : 0x7f050188;
                }

                protected final String getTitleDescription(WidgetViewContext widgetviewcontext, boolean flag)
                {
                    String s = item.getTitle();
                    if (isChipDurationMultidayEnabled())
                    {
                        widgetviewcontext = widgetviewcontext.context.getResources();
                        int i;
                        if (flag)
                        {
                            i = 0x7f130079;
                        } else
                        {
                            i = 0x7f130481;
                        }
                        return widgetviewcontext.getString(i, new Object[] {
                            s, Integer.valueOf((dayInfo.julianDay - item.getStartDay()) + 1), Integer.valueOf((item.getEndDay() - item.getStartDay()) + 1)
                        });
                    } else
                    {
                        return s;
                    }
                }

                protected boolean isChipDurationMultidayEnabled()
                {
                    return TimelineItemUtil.shouldItemBeRenderedAsMultiday(item);
                }

                public void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
                {
                    remoteviews.setOnClickFillInIntent(0x7f100398, createTimelineItemFillIntent(context, (EventKey)((TimelineEvent)item).eventKey));
                }

                public void updateTextViews(WidgetViewContext widgetviewcontext, RemoteViews remoteviews, int i)
                {
                    Context context = widgetviewcontext.context;
                    int j;
                    if (TimelineItemUtil.isAnyReminder(item))
                    {
                        j = 0x7f020139;
                    } else
                    if (TimelineItemUtil.isGroove(item))
                    {
                        j = 0x7f0201fb;
                    } else
                    if (TimelineItemUtil.isOutOfOfficeEvent(item))
                    {
                        j = 0x7f0201ef;
                    } else
                    if (TimelineItemUtil.isProposeNewTimeEvent(item, context))
                    {
                        j = 0x7f02022f;
                    } else
                    if (TimelineItemUtil.isEveryoneDeclinedEvent(item))
                    {
                        j = 0x7f020127;
                    } else
                    {
                        j = 0;
                    }
                    updateTextViews(widgetviewcontext, remoteviews, i, j);
                }

                protected void updateTextViews(WidgetViewContext widgetviewcontext, RemoteViews remoteviews, int i, int j)
                {
                    widgetviewcontext = getTitleDescription(widgetviewcontext, false);
                    if (item.hasDeclinedStatus())
                    {
                        SpannableString spannablestring = new SpannableString(widgetviewcontext);
                        spannablestring.setSpan(new StrikethroughSpan(), 0, widgetviewcontext.length(), 0);
                        widgetviewcontext = spannablestring;
                    }
                    remoteviews.setViewVisibility(0x7f100047, 0);
                    remoteviews.setTextViewText(0x7f100047, RtlUtils.forceTextAlignment(widgetviewcontext, super.isRtl));
                    remoteviews.setTextColor(0x7f100047, i);
                    updateIcon(remoteviews, i, j, TimelineItemUtil.useBadgedIcon(item), item.getColor());
                }

                RegularChipOne(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
                {
                    super(timelineitem);
                }
            }


            AllDayChip(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
            {
                super(timelineitem, datetimeformathelper);
            }
        }


        public final void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
        {
            remoteviews.setOnClickFillInIntent(0x7f100398, createTimelineItemFillIntent(context, Long.valueOf(0L)));
        }

        TaskAllDayChip(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
        {
            super(timelineitem, datetimeformathelper);
        }
    }


    private class TaskTimedChip extends RegularChipTwo
    {
        private class RegularChipTwo extends RegularChipOne
        {

            public final String timeString;

            public final int getLayoutId(WidgetViewContext widgetviewcontext)
            {
                return widgetviewcontext.widgetStyle != 1 ? 0x7f05018b : 0x7f05018a;
            }

            protected void updateTextViews(WidgetViewContext widgetviewcontext, RemoteViews remoteviews, int i, int j)
            {
                super.updateTextViews(widgetviewcontext, remoteviews, i, j);
                Object obj1 = timeString;
                Object obj = obj1;
                if (isChipDurationMultidayEnabled())
                {
                    if (dayInfo.julianDay == item.getStartDay())
                    {
                        obj = DateTimeFormatHelper.instance;
                        if (obj == null)
                        {
                            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
                        }
                        obj = ((DateTimeFormatHelper)obj).getTimeRangeText(item.getStartMillis(), item.getStartMillis(), 0);
                    } else
                    {
                        obj = obj1;
                        if (dayInfo.julianDay == item.getEndDay())
                        {
                            obj = DateTimeFormatHelper.instance;
                            if (obj == null)
                            {
                                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
                            }
                            obj = ((DateTimeFormatHelper)obj).getTimeRangeText(item.getEndMillis(), item.getEndMillis(), 0);
                            obj = widgetviewcontext.context.getResources().getString(0x7f130483, new Object[] {
                                obj
                            });
                        }
                    }
                }
                if (super.item.getLocation() != null && !super.item.getLocation().isEmpty())
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                obj1 = obj;
                if (j != 0)
                {
                    obj1 = String.format(widgetviewcontext.timeLocation, new Object[] {
                        obj, item.getLocation()
                    });
                }
                widgetviewcontext = ((WidgetViewContext) (obj1));
                if (item.hasDeclinedStatus())
                {
                    widgetviewcontext = new SpannableString(((CharSequence) (obj1)));
                    widgetviewcontext.setSpan(new StrikethroughSpan(), 0, ((CharSequence) (obj1)).length(), 0);
                }
                remoteviews.setViewVisibility(0x7f10039a, 0);
                remoteviews.setTextViewText(0x7f10039a, RtlUtils.forceTextAlignment(widgetviewcontext, super.isRtl));
                remoteviews.setTextColor(0x7f10039a, i);
            }

            RegularChipTwo(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
            {
                super(timelineitem, datetimeformathelper);
                datetimeformathelper = WidgetDateUtils.instance;
                if (datetimeformathelper == null)
                {
                    throw new NullPointerException(String.valueOf("WidgetDateUtils#initialize(...) must be called first"));
                } else
                {
                    timeString = ((WidgetDateUtils)datetimeformathelper).getWidgetItemTimeText(timelineitem, 0x80000);
                    return;
                }
            }
        }


        public final void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
        {
            remoteviews.setOnClickFillInIntent(0x7f100398, createTimelineItemFillIntent(context, Long.valueOf(0L)));
        }

        TaskTimedChip(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
        {
            super(timelineitem, datetimeformathelper);
        }
    }


    private class TaskBundleChip extends RegularChipOne
    {

        private final String subTitle;

        public final int getLayoutId(WidgetViewContext widgetviewcontext)
        {
            return widgetviewcontext.widgetStyle != 1 ? 0x7f05018b : 0x7f05018a;
        }

        protected final boolean isChipDurationMultidayEnabled()
        {
            return false;
        }

        public final void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
        {
            remoteviews.setOnClickFillInIntent(0x7f100398, createTimelineItemFillIntent(context, Long.valueOf(0L)));
        }

        public final void updateTextViews(WidgetViewContext widgetviewcontext, RemoteViews remoteviews, int i)
        {
            super.updateTextViews(widgetviewcontext, remoteviews, i);
            updateTextView(remoteviews, 0x7f10039a, 0, subTitle, i);
        }

        TaskBundleChip(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
        {
            super(timelineitem, datetimeformathelper);
            subTitle = ((TimelineTaskBundle)timelineitem).subtitle;
        }
    }


    private class GrooveChip extends RegularChipTwo
    {

        protected final void updateTextViews(WidgetViewContext widgetviewcontext, RemoteViews remoteviews, int i, int j)
        {
            widgetviewcontext = item.getTitle();
            String s = timeString;
            if (((TimelineGroove)item).completed)
            {
                SpannableString spannablestring = new SpannableString(widgetviewcontext);
                spannablestring.setSpan(new StrikethroughSpan(), 0, widgetviewcontext.length(), 0);
                widgetviewcontext = spannablestring;
            }
            remoteviews.setViewVisibility(0x7f100047, 0);
            remoteviews.setTextViewText(0x7f100047, RtlUtils.forceTextAlignment(widgetviewcontext, super.isRtl));
            remoteviews.setTextColor(0x7f100047, i);
            remoteviews.setViewVisibility(0x7f10039a, 0);
            remoteviews.setTextViewText(0x7f10039a, RtlUtils.forceTextAlignment(s, super.isRtl));
            remoteviews.setTextColor(0x7f10039a, i);
            updateIcon(remoteviews, i, j, TimelineItemUtil.useBadgedIcon(item), item.getColor());
        }

        GrooveChip(TimelineItem timelineitem, DateTimeFormatHelper datetimeformathelper)
        {
            super(timelineitem, datetimeformathelper);
        }
    }


    private class HolidayChip extends RegularChipOne
    {

        public final void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
        {
            remoteviews.setOnClickFillInIntent(0x7f100398, createTimelineItemFillIntent(context, Long.valueOf(0L)));
        }

        HolidayChip(TimelineHoliday timelineholiday, DateTimeFormatHelper datetimeformathelper)
        {
            super(timelineholiday, datetimeformathelper);
        }
    }


    private class BirthdayChip extends Chip
    {

        private final int namesResource = 0x7f10039a;
        private final String subtitle;

        public final int getLayoutId(WidgetViewContext widgetviewcontext)
        {
            if (widgetviewcontext.widgetStyle == 1)
            {
                return 0x7f050187;
            }
            if (TextUtils.isEmpty(subtitle))
            {
                return isFirst ? 0x7f050189 : 0x7f050188;
            } else
            {
                return 0x7f05018b;
            }
        }

        protected final String getTitleDescription(WidgetViewContext widgetviewcontext, boolean flag)
        {
            if (TextUtils.isEmpty(subtitle))
            {
                return item.getTitle();
            } else
            {
                widgetviewcontext = item.getTitle();
                String s = subtitle;
                return (new StringBuilder(String.valueOf(widgetviewcontext).length() + 2 + String.valueOf(s).length())).append(widgetviewcontext).append(", ").append(s).toString();
            }
        }

        public final void setOnClickFillInIntent(Context context, RemoteViews remoteviews)
        {
            context = LaunchInfoActivityUtils.getLaunchFillInIntent(context, Long.valueOf(0L));
            TimelineItemUtil.setLaunchTimelineItem(context, super.item);
            context.putExtra("beginTime", super.item.getStartMillis());
            context.putExtra("allDay", true);
            remoteviews.setOnClickFillInIntent(0x7f100398, context);
        }

        public final void updateStatus(WidgetViewContext widgetviewcontext, RemoteViews remoteviews)
        {
            ((TimelineBirthday)item).validate(widgetviewcontext.context);
            super.updateStatus(widgetviewcontext, remoteviews);
        }

        public final void updateTextViews(WidgetViewContext widgetviewcontext, RemoteViews remoteviews, int i)
        {
            widgetviewcontext = item.getTitle();
            remoteviews.setViewVisibility(0x7f100047, 0);
            remoteviews.setTextViewText(0x7f100047, RtlUtils.forceTextAlignment(widgetviewcontext, super.isRtl));
            remoteviews.setTextColor(0x7f100047, i);
            updateIcon(remoteviews, i, 0, TimelineItemUtil.useBadgedIcon(item), item.getColor());
            if (!TextUtils.isEmpty(subtitle))
            {
                int j = namesResource;
                widgetviewcontext = subtitle;
                remoteviews.setViewVisibility(j, 0);
                remoteviews.setTextViewText(j, RtlUtils.forceTextAlignment(widgetviewcontext, super.isRtl));
                remoteviews.setTextColor(j, i);
            }
        }

        BirthdayChip(TimelineBirthday timelinebirthday, DateTimeFormatHelper datetimeformathelper)
        {
            super(timelinebirthday);
            subtitle = timelinebirthday.subtitle;
        }
    }

}
