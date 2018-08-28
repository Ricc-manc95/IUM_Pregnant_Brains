// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.AbsoluteSizeSpan;
import android.util.SparseArray;
import android.widget.RemoteViews;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.widgetmonth.model.MonthViewWidgetGridModel;
import com.google.android.calendar.widgetmonth.model.MonthViewWidgetModel;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.calendar.widgetmonth:
//            MonthViewWidgetUtils, MonthViewWidgetState, MonthViewWidgetViewsFactory, MonthViewWidgetUpdateManager, 
//            MonthViewWidgetUpdatesQueue

public class MonthViewWidgetProvider extends AppWidgetProvider
{
    public static final class MonthViewWidgetPropertyChangeListener
        implements OnPropertyChangedListener
    {

        public final Context context;

        public final void onCalendarPropertyChanged(int i, Object obj)
        {
            class .Lambda._cls0
                implements Runnable
            {

                private final MonthViewWidgetPropertyChangeListener arg$1;
                private final int arg$2;

                public final void run()
                {
                    MonthViewWidgetPropertyChangeListener monthviewwidgetpropertychangelistener;
label0:
                    {
label1:
                        {
label2:
                            {
                                monthviewwidgetpropertychangelistener = arg$1;
                                int j = arg$2;
                                if (MonthViewWidgetProvider.propertyChangeListener == monthviewwidgetpropertychangelistener)
                                {
                                    switch (j)
                                    {
                                    default:
                                        LogUtils.w(MonthViewWidgetProvider.TAG, "Ignoring property change notification for '%d'", new Object[] {
                                            Integer.valueOf(j)
                                        });
                                        break;

                                    case 0: // '\0'
                                        break label0;

                                    case 5: // '\005'
                                    case 7: // '\007'
                                    case 13: // '\r'
                                        break label2;

                                    case 14: // '\016'
                                        break label1;
                                    }
                                }
                                return;
                            }
                            MonthViewWidgetProvider.performUpdateForAll(monthviewwidgetpropertychangelistener.context);
                            return;
                        }
                        MonthViewWidgetModel.removeAllModels();
                        MonthViewWidgetProvider.performUpdateForAll(monthviewwidgetpropertychangelistener.context);
                        return;
                    }
                    MonthViewWidgetProvider.onDateTimeChanged(monthviewwidgetpropertychangelistener.context);
                }

                .Lambda._cls0(int i)
                {
                    arg$1 = MonthViewWidgetPropertyChangeListener.this;
                    arg$2 = i;
                }
            }

            (new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN)).execute(new .Lambda._cls0(i));
        }

        public MonthViewWidgetPropertyChangeListener(Context context1)
        {
            context = context1.getApplicationContext();
        }
    }


    private static final String ACTION_MIDNIGHT = String.valueOf(com/google/android/calendar/widgetmonth/MonthViewWidgetProvider.getName()).concat(".MIDNIGHT");
    public static final String ACTION_NEXT_MONTH = String.valueOf(com/google/android/calendar/widgetmonth/MonthViewWidgetProvider.getName()).concat(".NEXT_MONTH");
    public static final String ACTION_PREV_MONTH = String.valueOf(com/google/android/calendar/widgetmonth/MonthViewWidgetProvider.getName()).concat(".PREV_MONTH");
    public static final String TAG = com/google/android/calendar/widgetmonth/MonthViewWidgetProvider.getSimpleName();
    public static MonthViewWidgetPropertyChangeListener propertyChangeListener;

    public MonthViewWidgetProvider()
    {
    }

    static void onDateTimeChanged(Context context)
    {
        LogUtils.d(TAG, "onDateTimeChanged", new Object[0]);
        AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
        int ai[];
        Calendar calendar;
        int j;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        alarmmanager.set(1, Utils.getNextMidnight(null, l, Utils.getTimeZoneId(context)), PendingIntent.getBroadcast(context, 0, new Intent(ACTION_MIDNIGHT, null, context, com/google/android/calendar/widgetmonth/MonthViewWidgetProvider), 0));
        AppWidgetManager.getInstance(context);
        ai = MonthViewWidgetUtils.getWidgetIds(context);
        calendar = MonthViewWidgetUtils.getCalendar(context);
        j = ai.length;
        for (int i = 0; i < j; i++)
        {
            int k = ai[i];
            MonthViewWidgetState.storeSelectedYearMonth(context, k, calendar.get(1), calendar.get(2));
            performUpdate$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FC5O70TR9CHJMAT1F85O70LR9CHJMAT2DC5N62PR5E8TKIAAM0(context, k);
        }

    }

    private static void onSelectedMonthChanged(Context context, AppWidgetManager appwidgetmanager, int i, int j)
    {
        LogUtils.d(TAG, "onSelectedMonthChanged", new Object[0]);
        appwidgetmanager = MonthViewWidgetUtils.getCalendarWithSelectedMonth(context, i);
        appwidgetmanager.add(2, j);
        MonthViewWidgetState.storeSelectedYearMonth(context, i, appwidgetmanager.get(1), appwidgetmanager.get(2));
        performUpdate$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FC5O70TR9CHJMAT1F85O70LR9CHJMAT2DC5N62PR5E8TKIAAM0(context, i);
    }

    private static void performUpdate(Context context, AppWidgetManager appwidgetmanager, int ai[])
    {
        int j = ai.length;
        for (int i = 0; i < j; i++)
        {
            performUpdate$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FC5O70TR9CHJMAT1F85O70LR9CHJMAT2DC5N62PR5E8TKIAAM0(context, ai[i]);
        }

    }

    public static void performUpdate$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FC5O70TR9CHJMAT1F85O70LR9CHJMAT2DC5N62PR5E8TKIAAM0(Context context, int i)
    {
        Object obj;
        MonthViewWidgetUpdateManager monthviewwidgetupdatemanager;
        MonthViewWidgetViewsFactory monthviewwidgetviewsfactory;
        int j;
        int l;
        int i1;
        LogUtils.v(TAG, "Updating widget with id '%d'", new Object[] {
            Integer.valueOf(i)
        });
        monthviewwidgetviewsfactory = new MonthViewWidgetViewsFactory(context, i);
        if (MonthViewWidgetUpdateManager.instance == null)
        {
            MonthViewWidgetUpdateManager.instance = new MonthViewWidgetUpdateManager(context);
        }
        monthviewwidgetupdatemanager = MonthViewWidgetUpdateManager.instance;
        context = monthviewwidgetviewsfactory.context;
        j = monthviewwidgetviewsfactory.appWidgetId;
        l = monthviewwidgetviewsfactory.startJulianDay;
        l = monthviewwidgetviewsfactory.numDaysOfPrevMonth + l;
        i1 = monthviewwidgetviewsfactory.endJulianDay - monthviewwidgetviewsfactory.numDaysOfNextMonth;
        obj = (MonthViewWidgetModel)MonthViewWidgetModel.modelForAppWidgetId.get(j);
        if (obj != null) goto _L2; else goto _L1
_L1:
        context = new MonthViewWidgetModel(context, j, l, i1);
        MonthViewWidgetModel.modelForAppWidgetId.put(j, context);
_L12:
        RemoteViews remoteviews2;
        Object obj1;
        l = monthviewwidgetviewsfactory.startJulianDay;
        i1 = monthviewwidgetviewsfactory.endJulianDay;
        String s;
        int i2;
        int l2;
        if (((MonthViewWidgetModel) (context)).startJulianDay <= l && l <= ((MonthViewWidgetModel) (context)).endJulianDay)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L4; else goto _L3
_L3:
        if (((MonthViewWidgetModel) (context)).startJulianDay <= i1 && i1 <= ((MonthViewWidgetModel) (context)).endJulianDay)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L5; else goto _L4
_L4:
        LogUtils.wtf(MonthViewWidgetModel.TAG, "The requested grid model is outside of this model's time range: appWidgetId=%d, startDayOfModel=%d, endDayOfModel=%d,startDayOfGridModel=%d, endDayOfGridModel=%d", new Object[] {
            Integer.valueOf(((MonthViewWidgetModel) (context)).appWidgetId), Integer.valueOf(((MonthViewWidgetModel) (context)).startJulianDay), Integer.valueOf(((MonthViewWidgetModel) (context)).endJulianDay), Integer.valueOf(l), Integer.valueOf(i1)
        });
        context = null;
_L13:
        remoteviews2 = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), 0x7f0501af);
        if (context == null)
        {
            remoteviews2.setProgressBar(0x7f1003f2, 0, 0, true);
            remoteviews2.setViewVisibility(0x7f1003f2, 0);
        } else
        {
            remoteviews2.setViewVisibility(0x7f1003f2, 8);
        }
        if (monthviewwidgetviewsfactory.alternateCalendarPref != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            obj = monthviewwidgetviewsfactory.getSelectedMonthStyledText();
            s = AlternateCalendarUtils.getAlternateYearMonthRangeString(monthviewwidgetviewsfactory.startJulianDay, monthviewwidgetviewsfactory.endJulianDay, monthviewwidgetviewsfactory.context.getResources(), monthviewwidgetviewsfactory.alternateCalendarPref);
            obj1 = new SpannableString(s);
            ((SpannableString) (obj1)).setSpan(new AbsoluteSizeSpan(monthviewwidgetviewsfactory.context.getResources().getDimensionPixelSize(0x7f0e0431)), 0, s.length(), 0);
            obj = TextUtils.concat(new CharSequence[] {
                obj, "\n", obj1
            });
        } else
        {
            obj = monthviewwidgetviewsfactory.getSelectedMonthStyledText();
        }
        remoteviews2.setTextViewText(0x7f1003ee, ((CharSequence) (obj)));
        remoteviews2.setOnClickPendingIntent(0x7f1003ed, MonthViewWidgetUtils.createLaunchCalendarPendingIntent(monthviewwidgetviewsfactory.context));
        remoteviews2.setOnClickPendingIntent(0x7f1003ef, MonthViewWidgetUtils.createPendingIntent(monthviewwidgetviewsfactory.context, com/google/android/calendar/widgetmonth/MonthViewWidgetProvider, ACTION_PREV_MONTH, monthviewwidgetviewsfactory.appWidgetId));
        remoteviews2.setOnClickPendingIntent(0x7f1003f0, MonthViewWidgetUtils.createPendingIntent(monthviewwidgetviewsfactory.context, com/google/android/calendar/widgetmonth/MonthViewWidgetProvider, ACTION_NEXT_MONTH, monthviewwidgetviewsfactory.appWidgetId));
        remoteviews2.removeAllViews(0x7f1003f1);
        if (!monthviewwidgetviewsfactory.isPortraitShort || !monthviewwidgetviewsfactory.isLandscapeShort) goto _L7; else goto _L6
_L6:
        obj = null;
_L14:
        if (obj != null)
        {
            remoteviews2.addView(0x7f1003f1, ((RemoteViews) (obj)));
        }
        l = monthviewwidgetviewsfactory.startJulianDay;
_L31:
        if (l > monthviewwidgetviewsfactory.endJulianDay) goto _L9; else goto _L8
_L2:
        l = MonthViewWidgetModel.computeMaximumStartJulianDay(l);
        i1 = MonthViewWidgetModel.computeMinimumEndJulianDay(i1);
        if (((MonthViewWidgetModel) (obj)).startJulianDay <= l && l <= ((MonthViewWidgetModel) (obj)).endJulianDay)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L11; else goto _L10
_L10:
        if (((MonthViewWidgetModel) (obj)).startJulianDay <= i1 && i1 <= ((MonthViewWidgetModel) (obj)).endJulianDay)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        context = ((Context) (obj));
        if (j != 0) goto _L12; else goto _L11
_L11:
        obj.startJulianDay = l;
        obj.endJulianDay = i1;
        ((MonthViewWidgetModel) (obj)).refreshData(false);
        context = ((Context) (obj));
          goto _L12
_L5:
        if (((MonthViewWidgetModel) (context)).itemsForJulianDay != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            context = null;
        } else
        {
label0:
            {
                if (((MonthViewWidgetModel) (context)).cachedGridModel != null)
                {
                    obj = ((MonthViewWidgetModel) (context)).cachedGridModel;
                    if (((MonthViewWidgetGridModel) (obj)).startJulianDay == l && ((MonthViewWidgetGridModel) (obj)).endJulianDay == i1)
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j != 0)
                    {
                        break label0;
                    }
                }
                context.cachedGridModel = new MonthViewWidgetGridModel(l, i1, ((MonthViewWidgetModel) (context)).itemsForJulianDay, ((MonthViewWidgetModel) (context)).geometry);
            }
            context = ((MonthViewWidgetModel) (context)).cachedGridModel;
        }
          goto _L13
_L7:
        j = 0x7f0501b4;
        RemoteViews remoteviews;
        if (!monthviewwidgetviewsfactory.isLandscapeShort && !monthviewwidgetviewsfactory.isPortraitShort)
        {
            j = 0x7f0501b2;
        } else
        {
            if (monthviewwidgetviewsfactory.isLandscapeShort && monthviewwidgetviewsfactory.isPortraitShort)
            {
                throw new IllegalStateException("Caller must ensure that not both landscape and portrait are short when calling this method.");
            }
            if (!monthviewwidgetviewsfactory.isLandscapeShort)
            {
                j = 0x7f0501b5;
            }
        }
        remoteviews = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), j);
        if (monthviewwidgetviewsfactory.weekNumberInYearForWeekIndex != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            obj = monthviewwidgetviewsfactory.context.getResources();
            monthviewwidgetviewsfactory.adaptViewPaddingForWeekNumbers(remoteviews, 0x7f1003fc, ((Resources) (obj)).getDimensionPixelSize(0x7f0e0424), 0, ((Resources) (obj)).getDimensionPixelSize(0x7f0e0423), 0);
        }
        obj1 = Calendar.getInstance();
        j = monthviewwidgetviewsfactory.todayJulianDay;
        if (monthviewwidgetviewsfactory.startJulianDay + monthviewwidgetviewsfactory.numDaysOfPrevMonth <= j && j <= monthviewwidgetviewsfactory.endJulianDay - monthviewwidgetviewsfactory.numDaysOfNextMonth)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            j = (monthviewwidgetviewsfactory.todayJulianDay - monthviewwidgetviewsfactory.startJulianDay) % 7;
        } else
        {
            j = -1;
        }
        l = 0;
        obj = remoteviews;
        if (l < 7)
        {
            ((Calendar) (obj1)).set(7, monthviewwidgetviewsfactory.dayForCol[l]);
            remoteviews.setTextViewText(MonthViewWidgetViewsFactory.DAY_LABEL_IDS[l], Utils.getShortDayOfWeek(((Calendar) (obj1)).getTime()));
            if (l == j)
            {
                remoteviews.setTextColor(MonthViewWidgetViewsFactory.DAY_LABEL_IDS[l], monthviewwidgetviewsfactory.context.getResources().getColor(0x7f0d01d7));
            }
            l++;
            break MISSING_BLOCK_LABEL_1507;
        }
          goto _L14
_L8:
        RemoteViews remoteviews1;
        List alist[];
        ArrayList arraylist;
        List alist1[];
        List list1;
        if (monthviewwidgetviewsfactory.alternateCalendarPref != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            j = 0x7f0501b9;
        } else
        {
            j = 0x7f0501b8;
        }
        obj1 = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), j);
        if (monthviewwidgetviewsfactory.weekNumberInYearForWeekIndex != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            obj = monthviewwidgetviewsfactory.context.getResources();
            monthviewwidgetviewsfactory.adaptViewPaddingForWeekNumbers(((RemoteViews) (obj1)), 0x7f1003f3, ((Resources) (obj)).getDimensionPixelSize(0x7f0e0424), 0, ((Resources) (obj)).getDimensionPixelSize(0x7f0e0423), 0);
            monthviewwidgetviewsfactory.adaptViewPaddingForWeekNumbers(((RemoteViews) (obj1)), 0x7f100406, 0, 0, 0, 0);
            ((RemoteViews) (obj1)).setViewVisibility(0x7f100405, 0);
            ((RemoteViews) (obj1)).setTextViewText(0x7f100405, String.format(Locale.getDefault(), "%d", new Object[] {
                Integer.valueOf(monthviewwidgetviewsfactory.weekNumberInYearForWeekIndex[(l - monthviewwidgetviewsfactory.startJulianDay) / 7])
            }));
        }
        i1 = 0;
        while (i1 < 7) 
        {
            int j1 = l + i1;
            j = MonthViewWidgetViewsFactory.DAYS_OF_THE_WEEK_IDS[i1];
            ((RemoteViews) (obj1)).setOnClickPendingIntent(j, MonthViewWidgetUtils.createViewDayPendingIntent(monthviewwidgetviewsfactory.context, j1));
            obj = DateFormat.format("dd MMMM yyyy", Utils.getMillisFromJulianDay(j1));
            ((RemoteViews) (obj1)).setContentDescription(j, monthviewwidgetviewsfactory.context.getString(0x7f130311, new Object[] {
                obj
            }));
            l2 = MonthViewWidgetViewsFactory.DAY_NUMBER_CONTAINER_IDS[i1];
            i2 = MonthViewWidgetViewsFactory.DAY_NUMBER_IDS[i1];
            int k3 = MonthViewWidgetViewsFactory.DAY_NUMBER_ALTERNATE_IDS[i1];
            if (monthviewwidgetviewsfactory.startJulianDay <= j1 && j1 < monthviewwidgetviewsfactory.startJulianDay + monthviewwidgetviewsfactory.numDaysOfPrevMonth)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                j = (monthviewwidgetviewsfactory.lastDayOfPrevMonth - monthviewwidgetviewsfactory.numDaysOfPrevMonth) + 1 + (j1 - monthviewwidgetviewsfactory.startJulianDay);
            } else
            {
                if (monthviewwidgetviewsfactory.startJulianDay + monthviewwidgetviewsfactory.numDaysOfPrevMonth <= j1 && j1 <= monthviewwidgetviewsfactory.endJulianDay - monthviewwidgetviewsfactory.numDaysOfNextMonth)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j != 0)
                {
                    j = (j1 - monthviewwidgetviewsfactory.startJulianDay - monthviewwidgetviewsfactory.numDaysOfPrevMonth) + 1;
                } else
                {
                    if (monthviewwidgetviewsfactory.endJulianDay - monthviewwidgetviewsfactory.numDaysOfNextMonth < j1 && j1 <= monthviewwidgetviewsfactory.endJulianDay)
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j != 0)
                    {
                        j = (monthviewwidgetviewsfactory.numDaysOfNextMonth - (monthviewwidgetviewsfactory.endJulianDay - j1) - 1) + 1;
                    } else
                    {
                        throw new IllegalStateException((new StringBuilder(69)).append("The requested Julian day is not part of this grid's days: ").append(j1).toString());
                    }
                }
            }
            s = String.format(Locale.getDefault(), "%d", new Object[] {
                Integer.valueOf(j)
            });
            if (monthviewwidgetviewsfactory.alternateCalendarPref != 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                obj = AlternateCalendarUtils.getAlternateDayOfMonthString(j1, monthviewwidgetviewsfactory.context.getResources(), monthviewwidgetviewsfactory.alternateCalendarPref);
            } else
            {
                obj = null;
            }
            if (j1 == monthviewwidgetviewsfactory.todayJulianDay)
            {
                ((RemoteViews) (obj1)).setInt(i2, "setBackgroundResource", 0x7f020291);
            }
            if (obj == null)
            {
                obj = monthviewwidgetviewsfactory.context.getResources();
                j = MonthViewWidgetUtils.computeTodayNumberTextWidthPx(monthviewwidgetviewsfactory.context, s);
                j = ((Resources) (obj)).getDimensionPixelSize(0x7f0e041b) - (((Resources) (obj)).getDimensionPixelSize(0x7f0e0410) - j) / 2;
                k3 = monthviewwidgetviewsfactory.context.getResources().getDimensionPixelSize(0x7f0e0420);
                ((RemoteViews) (obj1)).setViewPadding(l2, ((Integer)MonthViewWidgetUtils.getLeft(monthviewwidgetviewsfactory.isRtl, Integer.valueOf(j), Integer.valueOf(0))).intValue(), 0, ((Integer)MonthViewWidgetUtils.getRight(monthviewwidgetviewsfactory.isRtl, Integer.valueOf(j), Integer.valueOf(0))).intValue(), k3);
            } else
            {
                ((RemoteViews) (obj1)).setTextViewText(k3, (new StringBuilder(String.valueOf(obj).length() + 2)).append('(').append(((String) (obj))).append(')').toString());
                if (j1 == monthviewwidgetviewsfactory.todayJulianDay)
                {
                    j = monthviewwidgetviewsfactory.context.getResources().getColor(0x7f0d01d7);
                } else
                {
                    j = monthviewwidgetviewsfactory.getDayNumberColor(j1);
                }
                ((RemoteViews) (obj1)).setTextColor(k3, j);
            }
            ((RemoteViews) (obj1)).setTextViewText(i2, s);
            ((RemoteViews) (obj1)).setTextColor(i2, monthviewwidgetviewsfactory.getDayNumberColor(j1));
            i1++;
        }
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_3379;
        }
        MonthViewWidgetViewsFactory.setUpChipsContainer(((RemoteViews) (obj1)), 0x7f1003fb);
        int k1;
        if (monthviewwidgetviewsfactory.weekNumberInYearForWeekIndex != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            monthviewwidgetviewsfactory.adaptViewPaddingForWeekNumbers(((RemoteViews) (obj1)), 0x7f1003fb, monthviewwidgetviewsfactory.context.getResources().getDimensionPixelSize(0x7f0e0426), 0, 0, 0);
        }
        alist1 = monthviewwidgetviewsfactory.computeChipsRows(l, context, monthviewwidgetviewsfactory.landscapeMaxItemsPerDay);
        alist = monthviewwidgetviewsfactory.computeChipsRows(l, context, monthviewwidgetviewsfactory.portraitMaxItemsPerDay);
        if (monthviewwidgetviewsfactory.isLandscapeShort == monthviewwidgetviewsfactory.isPortraitShort && monthviewwidgetviewsfactory.landscapeMaxItemsPerDay == monthviewwidgetviewsfactory.portraitMaxItemsPerDay)
        {
            j = monthviewwidgetviewsfactory.landscapeMaxItemsPerDay;
        } else
        {
            j = Math.max(Math.min(monthviewwidgetviewsfactory.landscapeMaxItemsPerDay, monthviewwidgetviewsfactory.portraitMaxItemsPerDay) - 1, 0);
        }
        arraylist = new ArrayList();
        i1 = 0;
_L16:
        if (i1 >= j)
        {
            break MISSING_BLOCK_LABEL_2509;
        }
        list1 = alist1[i1];
        if (!MonthViewWidgetViewsFactory.hasMultidayChip(list1))
        {
            break; /* Loop/switch isn't completed */
        }
        remoteviews1 = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), 0x7f0501a7);
        k1 = 0;
        while (k1 < list1.size()) 
        {
            MonthViewWidgetViewsFactory.DataChip datachip2 = (MonthViewWidgetViewsFactory.DataChip)list1.get(k1);
            boolean flag1;
            if (!datachip2.empty && datachip2.item == null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                obj = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), 0x7f0501a5);
            } else
            {
                obj = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), MonthViewWidgetViewsFactory.getChipLayoutId(datachip2.numDays));
                if (datachip2.empty)
                {
                    ((RemoteViews) (obj)).setViewVisibility(0x7f1003ac, 4);
                } else
                {
                    monthviewwidgetviewsfactory.initChip(((RemoteViews) (obj)), 0x7f1003ad, 0x7f1003b2, 0x7f1003b1, 0x7f1003ae, 0x7f1003af, 0x7f1003b0, datachip2.item);
                }
            }
            remoteviews1.addView(0x7f1003b3, ((RemoteViews) (obj)));
            k1++;
        }
        obj = remoteviews1;
_L18:
        arraylist.add(obj);
        i1++;
        if (true) goto _L16; else goto _L15
_L15:
        int l1;
        int j2;
        remoteviews1 = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), 0x7f0501aa);
        j2 = 0;
        l1 = 0;
_L19:
        obj = remoteviews1;
        if (j2 >= list1.size()) goto _L18; else goto _L17
_L17:
        obj = (MonthViewWidgetViewsFactory.DataChip)list1.get(j2);
        int i3;
        if (!((MonthViewWidgetViewsFactory.DataChip) (obj)).empty && ((MonthViewWidgetViewsFactory.DataChip) (obj)).item == null)
        {
            i3 = 1;
        } else
        {
            i3 = 0;
        }
        if (i3 != 0)
        {
            remoteviews1.setViewVisibility(MonthViewWidgetViewsFactory.OVERFLOW_IDS[l1], 0);
        } else
        if (!((MonthViewWidgetViewsFactory.DataChip) (obj)).empty)
        {
            remoteviews1.setViewVisibility(MonthViewWidgetViewsFactory.CHIP_IDS[l1], 0);
            monthviewwidgetviewsfactory.initChip(remoteviews1, MonthViewWidgetViewsFactory.CHIP_BACKGROUND_IDS[l1], MonthViewWidgetViewsFactory.CHIP_TEXT_IDS[l1], MonthViewWidgetViewsFactory.CHIP_ICON_IDS[l1], MonthViewWidgetViewsFactory.CHIP_BADGED_ICON_CONTAINER_IDS[l1], MonthViewWidgetViewsFactory.CHIP_BADGED_ICON_BACKGROUND_IDS[l1], MonthViewWidgetViewsFactory.CHIP_BADGED_ICON_IDS[l1], ((MonthViewWidgetViewsFactory.DataChip) (obj)).item);
        }
        i3 = ((MonthViewWidgetViewsFactory.DataChip) (obj)).numDays;
        j2++;
        l1 += i3;
          goto _L19
          goto _L18
        i1 = j;
_L24:
        l1 = j;
        if (i1 >= monthviewwidgetviewsfactory.landscapeMaxItemsPerDay) goto _L21; else goto _L20
_L20:
        list1 = alist1[i1];
        if (!MonthViewWidgetViewsFactory.hasMultidayChip(list1)) goto _L23; else goto _L22
_L22:
        remoteviews1 = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), 0x7f0501a8);
        l1 = 0;
        while (l1 < list1.size()) 
        {
            MonthViewWidgetViewsFactory.DataChip datachip3 = (MonthViewWidgetViewsFactory.DataChip)list1.get(l1);
            boolean flag2;
            if (!datachip3.empty && datachip3.item == null)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag2)
            {
                obj = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), 0x7f0501a5);
            } else
            {
                obj = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), MonthViewWidgetViewsFactory.getChipLayoutId(datachip3.numDays));
                if (datachip3.empty)
                {
                    ((RemoteViews) (obj)).setViewVisibility(0x7f1003ac, 4);
                } else
                {
                    monthviewwidgetviewsfactory.initChip(((RemoteViews) (obj)), 0x7f1003ad, 0x7f1003b2, 0x7f1003b1, 0x7f1003ae, 0x7f1003af, 0x7f1003b0, datachip3.item);
                }
            }
            remoteviews1.addView(0x7f1003b3, ((RemoteViews) (obj)));
            l1++;
        }
        obj = remoteviews1;
_L26:
        arraylist.add(obj);
        i1++;
          goto _L24
_L23:
        remoteviews1 = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), 0x7f0501ab);
        flag2 = false;
        l1 = 0;
_L27:
        obj = remoteviews1;
        if (flag2 >= list1.size()) goto _L26; else goto _L25
_L25:
        obj = (MonthViewWidgetViewsFactory.DataChip)list1.get(flag2);
        int j3;
        if (!((MonthViewWidgetViewsFactory.DataChip) (obj)).empty && ((MonthViewWidgetViewsFactory.DataChip) (obj)).item == null)
        {
            j3 = 1;
        } else
        {
            j3 = 0;
        }
        if (j3 != 0)
        {
            remoteviews1.setViewVisibility(MonthViewWidgetViewsFactory.OVERFLOW_IDS[l1], 0);
        } else
        if (!((MonthViewWidgetViewsFactory.DataChip) (obj)).empty)
        {
            remoteviews1.setViewVisibility(MonthViewWidgetViewsFactory.CHIP_IDS[l1], 0);
            monthviewwidgetviewsfactory.initChip(remoteviews1, MonthViewWidgetViewsFactory.CHIP_BACKGROUND_IDS[l1], MonthViewWidgetViewsFactory.CHIP_TEXT_IDS[l1], MonthViewWidgetViewsFactory.CHIP_ICON_IDS[l1], MonthViewWidgetViewsFactory.CHIP_BADGED_ICON_CONTAINER_IDS[l1], MonthViewWidgetViewsFactory.CHIP_BADGED_ICON_BACKGROUND_IDS[l1], MonthViewWidgetViewsFactory.CHIP_BADGED_ICON_IDS[l1], ((MonthViewWidgetViewsFactory.DataChip) (obj)).item);
        }
        j3 = ((MonthViewWidgetViewsFactory.DataChip) (obj)).numDays;
        flag2++;
        l1 += j3;
          goto _L27
_L21:
        List list;
label1:
        {
            if (l1 >= monthviewwidgetviewsfactory.portraitMaxItemsPerDay)
            {
                break MISSING_BLOCK_LABEL_3342;
            }
            list = alist[l1];
            if (!MonthViewWidgetViewsFactory.hasMultidayChip(list))
            {
                break label1;
            }
            remoteviews1 = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), 0x7f0501a9);
            j = 0;
            while (j < list.size()) 
            {
                MonthViewWidgetViewsFactory.DataChip datachip1 = (MonthViewWidgetViewsFactory.DataChip)list.get(j);
                boolean flag;
                if (!datachip1.empty && datachip1.item == null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    obj = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), 0x7f0501a5);
                } else
                {
                    obj = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), MonthViewWidgetViewsFactory.getChipLayoutId(datachip1.numDays));
                    if (datachip1.empty)
                    {
                        ((RemoteViews) (obj)).setViewVisibility(0x7f1003ac, 4);
                    } else
                    {
                        monthviewwidgetviewsfactory.initChip(((RemoteViews) (obj)), 0x7f1003ad, 0x7f1003b2, 0x7f1003b1, 0x7f1003ae, 0x7f1003af, 0x7f1003b0, datachip1.item);
                    }
                }
                remoteviews1.addView(0x7f1003b3, ((RemoteViews) (obj)));
                j++;
            }
        }
        obj = remoteviews1;
_L29:
        arraylist.add(obj);
        l1++;
          goto _L21
        remoteviews1 = new RemoteViews(monthviewwidgetviewsfactory.context.getPackageName(), 0x7f0501ad);
        flag = false;
        j = 0;
_L30:
        obj = remoteviews1;
        if (flag >= list.size()) goto _L29; else goto _L28
_L28:
        MonthViewWidgetViewsFactory.DataChip datachip = (MonthViewWidgetViewsFactory.DataChip)list.get(flag);
        int k2;
        if (!datachip.empty && datachip.item == null)
        {
            k2 = 1;
        } else
        {
            k2 = 0;
        }
        if (k2 != 0)
        {
            remoteviews1.setViewVisibility(MonthViewWidgetViewsFactory.OVERFLOW_IDS[j], 0);
        } else
        if (!datachip.empty)
        {
            remoteviews1.setViewVisibility(MonthViewWidgetViewsFactory.CHIP_IDS[j], 0);
            monthviewwidgetviewsfactory.initChip(remoteviews1, MonthViewWidgetViewsFactory.CHIP_BACKGROUND_IDS[j], MonthViewWidgetViewsFactory.CHIP_TEXT_IDS[j], MonthViewWidgetViewsFactory.CHIP_ICON_IDS[j], MonthViewWidgetViewsFactory.CHIP_BADGED_ICON_CONTAINER_IDS[j], MonthViewWidgetViewsFactory.CHIP_BADGED_ICON_BACKGROUND_IDS[j], MonthViewWidgetViewsFactory.CHIP_BADGED_ICON_IDS[j], datachip.item);
        }
        k2 = datachip.numDays;
        flag++;
        j += k2;
          goto _L30
          goto _L29
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); ((RemoteViews) (obj1)).addView(0x7f1003fb, (RemoteViews)iterator.next())) { }
        if (l + 6 == monthviewwidgetviewsfactory.endJulianDay)
        {
            ((RemoteViews) (obj1)).setViewVisibility(0x7f10041c, 8);
        }
        remoteviews2.addView(0x7f1003f1, ((RemoteViews) (obj1)));
        l += 7;
          goto _L31
_L9:
        long l3;
label2:
        {
label3:
            {
                context = new MonthViewWidgetUpdatesQueue(remoteviews2, new ArrayDeque());
                int k;
                if (monthviewwidgetupdatemanager.updatesForAppWidgetId.size() > 0)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k == 0)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                monthviewwidgetupdatemanager.updatesForAppWidgetId.put(i, context);
                if (k != 0)
                {
                    l3 = SystemClock.elapsedRealtime() - MonthViewWidgetUpdateManager.lastUpdateTimeMs;
                    if (l3 < 100L)
                    {
                        break label2;
                    }
                    if (monthviewwidgetupdatemanager.updatesForAppWidgetId.size() > 0)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        k = monthviewwidgetupdatemanager.updatesForAppWidgetId.keyAt(0);
                        context = (MonthViewWidgetUpdatesQueue)monthviewwidgetupdatemanager.updatesForAppWidgetId.valueAt(0);
                        Context context1 = monthviewwidgetupdatemanager.context;
                        if (((MonthViewWidgetUpdatesQueue) (context)).baseUpdate != null)
                        {
                            i = 1;
                        } else
                        {
                            i = 0;
                        }
                        if (i != 0)
                        {
                            AppWidgetManager.getInstance(context1).updateAppWidget(k, ((MonthViewWidgetUpdatesQueue) (context)).baseUpdate);
                            context.baseUpdate = null;
                        } else
                        {
                            AppWidgetManager.getInstance(context1).partiallyUpdateAppWidget(k, (RemoteViews)((MonthViewWidgetUpdatesQueue) (context)).partialUpdates.remove());
                        }
                        MonthViewWidgetUpdateManager.lastUpdateTimeMs = SystemClock.elapsedRealtime();
                        if (((MonthViewWidgetUpdatesQueue) (context)).baseUpdate == null && ((MonthViewWidgetUpdatesQueue) (context)).partialUpdates.isEmpty())
                        {
                            i = 1;
                        } else
                        {
                            i = 0;
                        }
                        if (i != 0)
                        {
                            monthviewwidgetupdatemanager.updatesForAppWidgetId.removeAt(0);
                        }
                    }
                    if (monthviewwidgetupdatemanager.updatesForAppWidgetId.size() > 0)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        break label3;
                    }
                    monthviewwidgetupdatemanager.mainHandler.postDelayed(monthviewwidgetupdatemanager.updatesApplier, 100);
                }
                return;
            }
            MonthViewWidgetUpdateManager.instance = null;
            return;
        }
        i = (int)l3;
        monthviewwidgetupdatemanager.mainHandler.postDelayed(monthviewwidgetupdatemanager.updatesApplier, 100 - i);
        return;
    }

    static void performUpdateForAll(Context context)
    {
        performUpdate(context, AppWidgetManager.getInstance(context), MonthViewWidgetUtils.getWidgetIds(context));
    }

    public static void registerPropertyChangeListener(MonthViewWidgetPropertyChangeListener monthviewwidgetpropertychangelistener)
    {
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            calendarproperties = (CalendarProperties)calendarproperties;
            calendarproperties.registerListener(13, monthviewwidgetpropertychangelistener);
            calendarproperties.registerListener(5, monthviewwidgetpropertychangelistener);
            calendarproperties.registerListener(7, monthviewwidgetpropertychangelistener);
            calendarproperties.registerListener(0, monthviewwidgetpropertychangelistener);
            calendarproperties.registerListener(14, monthviewwidgetpropertychangelistener);
            return;
        }
    }

    public static void unregisterPropertyChangeListener(MonthViewWidgetPropertyChangeListener monthviewwidgetpropertychangelistener)
    {
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            calendarproperties = (CalendarProperties)calendarproperties;
            calendarproperties.unregisterListener(13, monthviewwidgetpropertychangelistener);
            calendarproperties.unregisterListener(5, monthviewwidgetpropertychangelistener);
            calendarproperties.unregisterListener(7, monthviewwidgetpropertychangelistener);
            calendarproperties.unregisterListener(0, monthviewwidgetpropertychangelistener);
            calendarproperties.unregisterListener(14, monthviewwidgetpropertychangelistener);
            return;
        }
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appwidgetmanager, int i, Bundle bundle)
    {
        performUpdate$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FC5O70TR9CHJMAT1F85O70LR9CHJMAT2DC5N62PR5E8TKIAAM0(context, i);
    }

    public void onDeleted(Context context, int ai[])
    {
        LogUtils.d(TAG, "onDeleted", new Object[0]);
        int j = ai.length;
        int i = 0;
        while (i < j) 
        {
            int k = ai[i];
            LogUtils.v(TAG, "Deleting widget with id '%d'", new Object[] {
                Integer.valueOf(k)
            });
            if (MonthViewWidgetUpdateManager.instance != null)
            {
                Object obj = MonthViewWidgetUpdateManager.instance;
                ((MonthViewWidgetUpdateManager) (obj)).updatesForAppWidgetId.remove(k);
                boolean flag;
                if (((MonthViewWidgetUpdateManager) (obj)).updatesForAppWidgetId.size() > 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    MonthViewWidgetUpdateManager.instance = null;
                }
            }
            obj = context.getSharedPreferences("com.google.android.calendar.widgetmonth", 0).edit();
            ((android.content.SharedPreferences.Editor) (obj)).remove((new StringBuilder(String.valueOf(".selectedYear").length() + 11)).append(k).append(".selectedYear").toString());
            ((android.content.SharedPreferences.Editor) (obj)).remove((new StringBuilder(String.valueOf(".selectedMonth").length() + 11)).append(k).append(".selectedMonth").toString());
            ((android.content.SharedPreferences.Editor) (obj)).apply();
            MonthViewWidgetModel.removeModel(k);
            i++;
        }
    }

    public void onDisabled(Context context)
    {
        LogUtils.d(TAG, "onDisabled", new Object[0]);
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj).trackEvent(context, "month_widget", "disabled");
        if (propertyChangeListener != null)
        {
            unregisterPropertyChangeListener(propertyChangeListener);
            propertyChangeListener = null;
        }
        MonthViewWidgetUpdateManager.instance = null;
        obj = context.getSharedPreferences("com.google.android.calendar.widgetmonth", 0).edit();
        ((android.content.SharedPreferences.Editor) (obj)).clear();
        ((android.content.SharedPreferences.Editor) (obj)).apply();
        MonthViewWidgetModel.removeAllModels();
        ((AlarmManager)context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, 0, new Intent(ACTION_MIDNIGHT, null, context, com/google/android/calendar/widgetmonth/MonthViewWidgetProvider), 0));
    }

    public void onEnabled(Context context)
    {
        LogUtils.d(TAG, "onEnabled", new Object[0]);
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj).trackEvent(context, "month_widget", "enabled");
        if (propertyChangeListener != null)
        {
            unregisterPropertyChangeListener(propertyChangeListener);
        }
        obj = new MonthViewWidgetPropertyChangeListener(context);
        propertyChangeListener = ((MonthViewWidgetPropertyChangeListener) (obj));
        registerPropertyChangeListener(((MonthViewWidgetPropertyChangeListener) (obj)));
        obj = (AlarmManager)context.getSystemService("alarm");
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        ((AlarmManager) (obj)).set(1, Utils.getNextMidnight(null, l, Utils.getTimeZoneId(context)), PendingIntent.getBroadcast(context, 0, new Intent(ACTION_MIDNIGHT, null, context, com/google/android/calendar/widgetmonth/MonthViewWidgetProvider), 0));
    }

    public void onReceive(Context context, Intent intent)
    {
        LogUtils.d(TAG, "onReceive", new Object[0]);
        String s = intent.getAction();
        if (ACTION_PREV_MONTH.equals(s))
        {
            int i = intent.getIntExtra("appWidgetId", 0);
            if (i != 0)
            {
                onSelectedMonthChanged(context, AppWidgetManager.getInstance(context), i, -1);
            }
        } else
        if (ACTION_NEXT_MONTH.equals(s))
        {
            int j = intent.getIntExtra("appWidgetId", 0);
            if (j != 0)
            {
                onSelectedMonthChanged(context, AppWidgetManager.getInstance(context), j, 1);
                return;
            }
        } else
        if ("android.intent.action.DATE_CHANGED".equals(s) || "android.intent.action.TIME_SET".equals(s) || "android.intent.action.TIMEZONE_CHANGED".equals(s) || ACTION_MIDNIGHT.equals(s))
        {
            onDateTimeChanged(context);
            return;
        } else
        {
            super.onReceive(context, intent);
            return;
        }
    }

    public void onRestored(Context context, int ai[], int ai1[])
    {
        LogUtils.d(TAG, "onRestored", new Object[0]);
        for (int i = 0; i < ai.length; i++)
        {
            int j = ai[i];
            int k = ai1[i];
            Object obj = MonthViewWidgetState.loadState(context, j);
            MonthViewWidgetState.storeSelectedYearMonth(context, k, ((MonthViewWidgetState) (obj)).selectedYear, ((MonthViewWidgetState) (obj)).selectedMonth);
            obj = context.getSharedPreferences("com.google.android.calendar.widgetmonth", 0).edit();
            ((android.content.SharedPreferences.Editor) (obj)).remove((new StringBuilder(String.valueOf(".selectedYear").length() + 11)).append(j).append(".selectedYear").toString());
            ((android.content.SharedPreferences.Editor) (obj)).remove((new StringBuilder(String.valueOf(".selectedMonth").length() + 11)).append(j).append(".selectedMonth").toString());
            ((android.content.SharedPreferences.Editor) (obj)).apply();
            MonthViewWidgetModel.removeModel(ai[i]);
        }

    }

    public void onUpdate(Context context, AppWidgetManager appwidgetmanager, int ai[])
    {
        LogUtils.d(TAG, "onUpdate", new Object[0]);
        performUpdate(context, appwidgetmanager, ai);
    }

}
