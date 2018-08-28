// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.widget.RemoteViews;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.timely.MonthViewPartitionItem;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.android.calendar.timely.geometry.SimplePartitionItem;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.android.calendar.widget.WidgetUtils;
import com.google.android.calendar.widgetmonth.model.MonthViewWidgetGridModel;
import com.google.common.base.Platform;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.widgetmonth:
//            MonthViewWidgetState, MonthViewWidgetUtils

public final class MonthViewWidgetViewsFactory
{

    public static final int CHIP_BACKGROUND_IDS[] = {
        0x7f1003bc, 0x7f1003c3, 0x7f1003ca, 0x7f1003d1, 0x7f1003d8, 0x7f1003df, 0x7f1003e6
    };
    public static final int CHIP_BADGED_ICON_BACKGROUND_IDS[] = {
        0x7f1003be, 0x7f1003c5, 0x7f1003cc, 0x7f1003d3, 0x7f1003da, 0x7f1003e1, 0x7f1003e8
    };
    public static final int CHIP_BADGED_ICON_CONTAINER_IDS[] = {
        0x7f1003bd, 0x7f1003c4, 0x7f1003cb, 0x7f1003d2, 0x7f1003d9, 0x7f1003e0, 0x7f1003e7
    };
    public static final int CHIP_BADGED_ICON_IDS[] = {
        0x7f1003bf, 0x7f1003c6, 0x7f1003cd, 0x7f1003d4, 0x7f1003db, 0x7f1003e2, 0x7f1003e9
    };
    public static final int CHIP_ICON_IDS[] = {
        0x7f1003c0, 0x7f1003c7, 0x7f1003ce, 0x7f1003d5, 0x7f1003dc, 0x7f1003e3, 0x7f1003ea
    };
    public static final int CHIP_IDS[] = {
        0x7f1003bb, 0x7f1003c2, 0x7f1003c9, 0x7f1003d0, 0x7f1003d7, 0x7f1003de, 0x7f1003e5
    };
    public static final int CHIP_TEXT_IDS[] = {
        0x7f1003c1, 0x7f1003c8, 0x7f1003cf, 0x7f1003d6, 0x7f1003dd, 0x7f1003e4, 0x7f1003eb
    };
    public static final int DAYS_OF_THE_WEEK_IDS[] = {
        0x7f1003f4, 0x7f1003f5, 0x7f1003f6, 0x7f1003f7, 0x7f1003f8, 0x7f1003f9, 0x7f1003fa
    };
    public static final int DAY_LABEL_IDS[] = {
        0x7f1003fd, 0x7f1003fe, 0x7f1003ff, 0x7f100400, 0x7f100401, 0x7f100402, 0x7f100403
    };
    public static final int DAY_NUMBER_ALTERNATE_IDS[] = {
        0x7f100415, 0x7f100416, 0x7f100417, 0x7f100418, 0x7f100419, 0x7f10041a, 0x7f10041b
    };
    public static final int DAY_NUMBER_CONTAINER_IDS[] = {
        0x7f100407, 0x7f100409, 0x7f10040b, 0x7f10040d, 0x7f10040f, 0x7f100411, 0x7f100413
    };
    public static final int DAY_NUMBER_IDS[] = {
        0x7f100408, 0x7f10040a, 0x7f10040c, 0x7f10040e, 0x7f100410, 0x7f100412, 0x7f100414
    };
    public static final int OVERFLOW_IDS[] = {
        0x7f1003b4, 0x7f1003b5, 0x7f1003b6, 0x7f1003b7, 0x7f1003b8, 0x7f1003b9, 0x7f1003ba
    };
    public int alternateCalendarPref;
    public final int appWidgetId;
    private int birthdayColor;
    private final int colForDay[] = new int[8];
    public final Context context;
    public final int dayForCol[] = new int[7];
    public int endJulianDay;
    public boolean isLandscapeShort;
    public boolean isPortraitShort;
    public boolean isRtl;
    public int landscapeMaxItemsPerDay;
    public int lastDayOfPrevMonth;
    public int numDaysOfNextMonth;
    public int numDaysOfPrevMonth;
    public int portraitMaxItemsPerDay;
    public int startJulianDay;
    private MonthViewWidgetState state;
    public int todayJulianDay;
    public int weekNumberInYearForWeekIndex[];

    MonthViewWidgetViewsFactory(Context context1, int i)
    {
        context = context1;
        appWidgetId = i;
        context1 = Calendar.getInstance();
        i = PreferenceUtils.getFirstDayOfWeekAsCalendar(context);
        for (int j = 0; j < 7;)
        {
            dayForCol[j] = i;
            colForDay[i] = j;
            context1.set(7, i);
            j++;
            if (i == 7)
            {
                i = 1;
            } else
            {
                i++;
            }
        }

        state = MonthViewWidgetState.ensureStateExists(context, appWidgetId);
        todayJulianDay = Utils.getTodayJulianDay(context);
        context1 = Calendar.getInstance();
        context1.clear();
        context1.set(state.selectedYear, state.selectedMonth, 1);
        numDaysOfPrevMonth = colForDay[context1.get(7)];
        i = context1.getActualMaximum(5);
        context1.add(2, -1);
        lastDayOfPrevMonth = context1.getActualMaximum(5);
        context1.clear();
        context1.set(state.selectedYear, state.selectedMonth, i);
        numDaysOfNextMonth = 7 - colForDay[context1.get(7)] - 1;
        context1.clear();
        context1.set(state.selectedYear, state.selectedMonth, 1);
        context1.add(5, -numDaysOfPrevMonth);
        startJulianDay = Utils.getJulianDay(context1.get(1), context1.get(2), context1.get(5));
        context1.clear();
        context1.set(state.selectedYear, state.selectedMonth, i);
        context1.add(5, numDaysOfNextMonth);
        endJulianDay = Utils.getJulianDay(context1.get(1), context1.get(2), context1.get(5));
        if (context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_show_week_num", false))
        {
            i = ((endJulianDay - startJulianDay) + 7) / 7;
            int k = i - 1;
            int l = com.android.datetimepicker.Utils.convertDayOfWeekFromCalendarToTime(dayForCol[0]);
            weekNumberInYearForWeekIndex = new int[i];
            weekNumberInYearForWeekIndex[1] = com.android.datetimepicker.Utils.getWeekNumberInYear(startJulianDay + 7, l);
            for (i = 2; i < k; i++)
            {
                weekNumberInYearForWeekIndex[i] = weekNumberInYearForWeekIndex[i - 1] + 1;
            }

            if (state.selectedMonth == 0)
            {
                weekNumberInYearForWeekIndex[0] = com.android.datetimepicker.Utils.getWeekNumberInYear(startJulianDay, l);
            } else
            {
                weekNumberInYearForWeekIndex[0] = weekNumberInYearForWeekIndex[1] - 1;
            }
            if (state.selectedMonth == 11)
            {
                weekNumberInYearForWeekIndex[k] = com.android.datetimepicker.Utils.getWeekNumberInYear(endJulianDay - 6, l);
            } else
            {
                weekNumberInYearForWeekIndex[k] = weekNumberInYearForWeekIndex[k - 1] + 1;
            }
        }
        context1 = AppWidgetManager.getInstance(context).getAppWidgetOptions(appWidgetId);
        k = ((endJulianDay - startJulianDay) + 7) / 7;
        i = MonthViewWidgetUtils.computeMaxItemsPerDayForWidgetHeight(context, context1.getInt("appWidgetMinHeight"), k);
        k = MonthViewWidgetUtils.computeMaxItemsPerDayForWidgetHeight(context, context1.getInt("appWidgetMaxHeight"), k);
        landscapeMaxItemsPerDay = Math.min(i, 6);
        portraitMaxItemsPerDay = Math.min(k, 6);
        if (landscapeMaxItemsPerDay == 0)
        {
            landscapeMaxItemsPerDay = 1;
            isLandscapeShort = true;
        }
        if (portraitMaxItemsPerDay == 0)
        {
            portraitMaxItemsPerDay = 1;
            isPortraitShort = true;
        }
        birthdayColor = PreferenceUtils.getBirthdayColor(context);
        isRtl = RtlUtils.isLayoutDirectionRtl(context);
        alternateCalendarPref = PreferencesConstants.getAlternateCalendarPref(context);
    }

    static int getChipLayoutId(int i)
    {
        switch (i)
        {
        default:
            return 0x7f0501a4;

        case 1: // '\001'
            return 0x7f05019d;

        case 2: // '\002'
            return 0x7f05019e;

        case 3: // '\003'
            return 0x7f05019f;

        case 4: // '\004'
            return 0x7f0501a0;

        case 5: // '\005'
            return 0x7f0501a1;

        case 6: // '\006'
            return 0x7f0501a2;
        }
    }

    static boolean hasMultidayChip(List list)
    {
        boolean flag2 = false;
        int i = 0;
        do
        {
label0:
            {
                boolean flag1 = flag2;
                if (i < list.size())
                {
                    DataChip datachip = (DataChip)list.get(i);
                    if (datachip.empty)
                    {
                        break label0;
                    }
                    boolean flag;
                    if (!datachip.empty && datachip.item == null)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag || datachip.numDays <= 1)
                    {
                        break label0;
                    }
                    flag1 = true;
                }
                return flag1;
            }
            i++;
        } while (true);
    }

    static void setUpChipsContainer(RemoteViews remoteviews, int i)
    {
        if (i != 0x7f1003fb)
        {
            remoteviews.setInt(0x7f10041d, "setInflatedId", i);
        }
        remoteviews.setViewVisibility(0x7f10041d, 4);
    }

    final void adaptViewPaddingForWeekNumbers(RemoteViews remoteviews, int i, int j, int k, int l, int i1)
    {
        j += context.getResources().getDimensionPixelSize(0x7f0e0437);
        remoteviews.setViewPadding(i, ((Integer)MonthViewWidgetUtils.getLeft(isRtl, Integer.valueOf(j), Integer.valueOf(l))).intValue(), 0, ((Integer)MonthViewWidgetUtils.getRight(isRtl, Integer.valueOf(j), Integer.valueOf(l))).intValue(), 0);
    }

    final List[] computeChipsRows(int i, MonthViewWidgetGridModel monthviewwidgetgridmodel, int j)
    {
        List alist[] = new List[j];
        for (int k = 0; k < j; k++)
        {
            alist[k] = new ArrayList();
        }

        MonthViewPartitionItem amonthviewpartitionitem[] = new MonthViewPartitionItem[j];
        int ai[] = new int[j];
label0:
        for (int l = 0; l < 7; l++)
        {
            MonthViewPartitionItem amonthviewpartitionitem1[] = new MonthViewPartitionItem[j];
            List list1 = (List)monthviewwidgetgridmodel.itemsForJulianDay.get(i + l);
            Object obj = list1;
            if (list1 == null)
            {
                obj = Collections.emptyList();
            }
            obj = ((List) (obj)).iterator();
            do
            {
label1:
                {
                    MonthViewPartitionItem monthviewpartitionitem;
                    if (((Iterator) (obj)).hasNext())
                    {
                        monthviewpartitionitem = (MonthViewPartitionItem)((Iterator) (obj)).next();
                        if (((SimplePartitionItem) (monthviewpartitionitem)).partitionIndex < j)
                        {
                            break label1;
                        }
                        if (j > 0)
                        {
                            amonthviewpartitionitem1[j - 1] = new MonthViewPartitionItem();
                        }
                    }
                    int i1 = 0;
                    while (i1 < j) 
                    {
                        if (amonthviewpartitionitem1[i1] == amonthviewpartitionitem[i1])
                        {
                            ai[i1] = ai[i1] + 1;
                        } else
                        {
                            if (ai[i1] > 0)
                            {
                                List list2 = alist[i1];
                                Object obj1 = amonthviewpartitionitem[i1];
                                int j1 = ai[i1];
                                if (obj1 == null)
                                {
                                    obj1 = new DataChip(null, j1, true);
                                } else
                                {
                                    obj1 = new DataChip(((MonthViewPartitionItem) (obj1)).item, j1, false);
                                }
                                list2.add(obj1);
                            }
                            amonthviewpartitionitem[i1] = amonthviewpartitionitem1[i1];
                            ai[i1] = 1;
                        }
                        i1++;
                    }
                    continue label0;
                }
                amonthviewpartitionitem1[((SimplePartitionItem) (monthviewpartitionitem)).partitionIndex] = monthviewpartitionitem;
            } while (true);
        }

        i = 0;
        while (i < alist.length) 
        {
            List list = alist[i];
            monthviewwidgetgridmodel = amonthviewpartitionitem[i];
            j = ai[i];
            if (monthviewwidgetgridmodel == null)
            {
                monthviewwidgetgridmodel = new DataChip(null, j, true);
            } else
            {
                monthviewwidgetgridmodel = new DataChip(((MonthViewPartitionItem) (monthviewwidgetgridmodel)).item, j, false);
            }
            list.add(monthviewwidgetgridmodel);
            i++;
        }
        return alist;
    }

    final int getDayNumberColor(int i)
    {
        if (i == todayJulianDay)
        {
            return context.getResources().getColor(0x7f0d0342);
        }
        if (startJulianDay + numDaysOfPrevMonth <= i && i <= endJulianDay - numDaysOfNextMonth)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            return context.getResources().getColor(0x7f0d033f);
        } else
        {
            return context.getResources().getColor(0x7f0d0341);
        }
    }

    final SpannableString getSelectedMonthStyledText()
    {
        Object obj = MonthViewWidgetUtils.getCalendar(context);
        ((Calendar) (obj)).clear();
        ((Calendar) (obj)).set(state.selectedYear, state.selectedMonth, 1);
        Object obj1 = DateTimeFormatHelper.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        } else
        {
            obj = ((DateTimeFormatHelper)obj1).getDateRangeText(((Calendar) (obj)).getTimeInMillis(), ((Calendar) (obj)).getTimeInMillis(), 48);
            obj1 = new SpannableString(((CharSequence) (obj)));
            Resources resources = context.getResources();
            ((SpannableString) (obj1)).setSpan(new TextAppearanceSpan(context, 0x1030044), 0, ((String) (obj)).length(), 0);
            ((SpannableString) (obj1)).setSpan(new AbsoluteSizeSpan(resources.getDimensionPixelSize(0x7f0e0432)), 0, ((String) (obj)).length(), 0);
            ((SpannableString) (obj1)).setSpan(new ForegroundColorSpan(resources.getColor(0x7f0d02c8)), 0, ((String) (obj)).length(), 0);
            return ((SpannableString) (obj1));
        }
    }

    final void initChip(RemoteViews remoteviews, int i, int j, int k, int l, int i1, int j1, 
            TimelineItem timelineitem)
    {
        int k1;
        int l1;
        Object obj;
        com.google.android.calendar.timeline.chip.ChipViewModel.ColorStyle colorstyle;
        int i2;
        boolean flag;
        if (TimelineItemUtil.isBirthday(timelineitem))
        {
            obj = ((TimelineBirthday)timelineitem).singleLineTitle;
        } else
        if (TimelineItemUtil.isReminderBundle(timelineitem))
        {
            obj = ((TimelineTaskBundle)timelineitem).singleLineTitle;
        } else
        {
            obj = Platform.nullToEmpty(timelineitem.getTitle());
        }
        if (TimelineItemUtil.isBirthday(timelineitem))
        {
            k1 = birthdayColor;
        } else
        {
            k1 = TimelineItemUtil.getTimelineItemColor(timelineitem);
        }
        colorstyle = TimelineItemUtil.getColorStyle(timelineitem);
        if (timelineitem.hasDeclinedStatus() || TimelineItemUtil.isGroove(timelineitem) && ((TimelineGroove)timelineitem).completed)
        {
            l1 = 1;
        } else
        {
            l1 = 0;
        }
        if (l1 != 0)
        {
            SpannableString spannablestring = new SpannableString(((CharSequence) (obj)));
            spannablestring.setSpan(new StrikethroughSpan(), 0, ((CharSequence) (obj)).length(), 0);
            obj = spannablestring;
        } else
        {
            obj = MonthViewWidgetUtils.getBoldText(((CharSequence) (obj)));
        }
        remoteviews.setTextViewText(j, ((CharSequence) (obj)));
        obj = context;
        remoteviews.setTextColor(j, WidgetUtils.getChipTextColor(colorstyle, k1, ContextCompat.getColor(((Context) (obj)), 0x7f0d02c8), ContextCompat.getColor(((Context) (obj)), 0x7f0d021e)));
        colorstyle.ordinal();
        JVM INSTR tableswitch 1 1: default 176
    //                   1 401;
           goto _L1 _L2
_L1:
        j = 0x7f020281;
_L8:
        remoteviews.setImageViewResource(i, j);
        remoteviews.setInt(i, "setColorFilter", k1);
        if (TimelineItemUtil.isAnyReminder(timelineitem))
        {
            i = 0x7f02013a;
        } else
        if (TimelineItemUtil.isGroove(timelineitem))
        {
            i = 0x7f020129;
        } else
        if (TimelineItemUtil.isOutOfOfficeEvent(timelineitem))
        {
            i = 0x7f0201ef;
        } else
        if (TimelineItemUtil.isProposeNewTimeEvent(timelineitem, context))
        {
            i = 0x7f02013b;
        } else
        if (TimelineItemUtil.isEveryoneDeclinedEvent(timelineitem))
        {
            i = 0x7f020126;
        } else
        {
            i = 0;
        }
        obj = context;
        l1 = WidgetUtils.getChipTextColor(colorstyle, k1, ContextCompat.getColor(((Context) (obj)), 0x7f0d02c8), ContextCompat.getColor(((Context) (obj)), 0x7f0d021e));
        flag = TimelineItemUtil.useBadgedIcon(timelineitem);
        i2 = timelineitem.getColor();
        if (i != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0 && !flag)
        {
            k1 = 0;
        } else
        {
            k1 = 8;
        }
        remoteviews.setViewVisibility(k, k1);
        if (j != 0 && flag)
        {
            k1 = 0;
        } else
        {
            k1 = 8;
        }
        remoteviews.setViewVisibility(l, k1);
        if (j == 0) goto _L4; else goto _L3
_L3:
        if (!flag) goto _L6; else goto _L5
_L5:
        remoteviews.setImageViewResource(j1, i);
        remoteviews.setInt(j1, "setColorFilter", -1);
        remoteviews.setInt(i1, "setColorFilter", i2);
_L4:
        return;
_L2:
        j = 0x7f020283;
        continue; /* Loop/switch isn't completed */
_L6:
        remoteviews.setImageViewResource(k, i);
        remoteviews.setInt(k, "setColorFilter", l1);
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }


    private class DataChip
    {

        public final boolean empty;
        public final TimelineItem item;
        public final int numDays;

        DataChip(TimelineItem timelineitem, int i, boolean flag)
        {
            item = timelineitem;
            numDays = i;
            empty = flag;
        }
    }

}
