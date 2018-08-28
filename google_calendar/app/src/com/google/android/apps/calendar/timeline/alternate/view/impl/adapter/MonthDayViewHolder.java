// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.month.DayView;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;
import com.google.android.calendar.material.Material;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl, AdapterEvent, MonthDayViewPositionHelper, MonthAdapter, 
//            AdapterMonthDay, ViewLayoutParams

final class MonthDayViewHolder extends TimelineAdapterViewHolderImpl
{

    private final SimpleDateFormat a11yFullDateFormat = new SimpleDateFormat("EEEE dd MMMM", Locale.getDefault());
    private final AlternateCalendarHelper alternateCalendarHelper;
    private final Calendar calendar = Calendar.getInstance();
    private final int currentDayColor;
    private final ObservableReference currentJulianDay;
    private final ObservableReference isA11yEnabled;
    private final ItemAdapter itemAdapter;
    public int julianDay;
    public final ObservableReference maxEventsPerDay;
    private final MonthAdapter monthAdapter;
    private final int otherMonthColor;
    public int position;
    private final TimeUtils timeUtils;
    private int todayJulianDay;
    private final DayView view;

    MonthDayViewHolder(Context context, TimeUtils timeutils, ItemAdapter itemadapter, MonthAdapter monthadapter, ObservableReference observablereference, Consumer consumer, ObservableReference observablereference1, 
            ObservableReference observablereference2, ObservableReference observablereference3, AlternateCalendarHelper alternatecalendarhelper, DayView dayview)
    {
        super(dayview);
        timeUtils = timeutils;
        itemAdapter = itemadapter;
        monthAdapter = monthadapter;
        currentJulianDay = observablereference;
        maxEventsPerDay = observablereference1;
        isA11yEnabled = observablereference3;
        alternateCalendarHelper = alternatecalendarhelper;
        a11yFullDateFormat.setTimeZone((TimeZone)timeutils.timeZone.get());
        timeutils = itemView;
        class .Lambda._cls0
            implements Consumer
        {

            private final MonthDayViewHolder arg$1;

            public final void accept(Object obj)
            {
                obj = arg$1;
                ((MonthDayViewHolder) (obj)).onUpdate(((MonthDayViewHolder) (obj)).position);
            }

            .Lambda._cls0()
            {
                arg$1 = MonthDayViewHolder.this;
            }
        }

        timeutils.addOnAttachStateChangeListener(new com.google.android.apps.calendar.util.android.Views._cls1(timeutils, observablereference2, new .Lambda._cls0(), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), false));
        view = (DayView)itemView;
        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final MonthDayViewHolder arg$1;
            private final Consumer arg$2;

            public final void onClick(View view1)
            {
                view1 = arg$1;
                arg$2.accept(Integer.valueOf(((MonthDayViewHolder) (view1)).julianDay));
            }

            .Lambda._cls1(Consumer consumer)
            {
                arg$1 = MonthDayViewHolder.this;
                arg$2 = consumer;
            }
        }

        view.setOnClickListener(new .Lambda._cls1(consumer));
        otherMonthColor = ContextCompat.getColor(context, 0x7f0d021a);
        currentDayColor = ContextCompat.getColor(context, 0x7f0d02c4);
    }

    static final boolean lambda$countEventsOnOrAboveSlot$2$MonthDayViewHolder(int i, AdapterEvent adapterevent)
    {
        return adapterevent.getMonthSlot() >= i;
    }

    public final void onLayoutUpdate(ViewLayoutParams viewlayoutparams)
    {
        if (todayJulianDay != ((Integer)currentJulianDay.get()).intValue())
        {
            onUpdate(position);
        }
    }

    final void onUpdate(int i)
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj1 = null;
        int j = ((Integer)currentJulianDay.get()).intValue();
        if (i != position || j != todayJulianDay)
        {
            position = i;
            todayJulianDay = j;
            julianDay = MonthDayViewPositionHelper.toJulianDay(i);
            String s;
            int l;
            boolean flag;
            if (julianDay == todayJulianDay)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            calendar.setTimeZone((TimeZone)timeUtils.timeZone.get());
            calendar.setTimeInMillis(timeUtils.julianDateToMs(julianDay));
            obj2 = view;
            s = String.format(Locale.getDefault(), "%d", new Object[] {
                Integer.valueOf(calendar.get(5))
            });
            j = position;
            l = todayJulianDay;
            if (julianDay == l)
            {
                j = currentDayColor;
            } else
            if (MonthDayViewPositionHelper.isInCurrentMonth(j))
            {
                j = 0xff333333;
            } else
            {
                j = otherMonthColor;
            }
            if (!alternateCalendarHelper.isEnabled())
            {
                obj = null;
            } else
            {
                obj = alternateCalendarHelper.getDayOfMonth(julianDay);
                obj = (new StringBuilder(String.valueOf(obj).length() + 2)).append("(").append(((String) (obj))).append(")").toString();
            }
            ((DayView) (obj2)).drawable.isHighlighted = flag;
            ((DayView) (obj2)).drawable.dayText = s;
            ((DayView) (obj2)).drawable.textPaint.setColor(j);
            ((DayView) (obj2)).drawable.textPaint.setFakeBoldText(flag);
            ((DayView) (obj2)).drawable.alternateText = ((String) (obj));
            ((DayView) (obj2)).invalidate();
            if (((Boolean)isA11yEnabled.get()).booleanValue())
            {
                DayView dayview = view;
                obj = calendar.getTime();
                obj2 = a11yFullDateFormat.format(((java.util.Date) (obj)));
                obj = obj2;
                if (alternateCalendarHelper.isEnabled())
                {
                    obj = String.valueOf(obj2);
                    obj2 = alternateCalendarHelper.getMonthAndDay(julianDay);
                    obj = (new StringBuilder(String.valueOf(obj).length() + 2 + String.valueOf(obj2).length())).append(((String) (obj))).append(", ").append(((String) (obj2))).toString();
                }
                j = monthAdapter.getMonthDay(position).getEvents().size();
                class .Lambda._cls2
                    implements Predicate
                {

                    private final int arg$1;

                    public final boolean apply(Object obj5)
                    {
                        return MonthDayViewHolder.lambda$countEventsOnOrAboveSlot$2$MonthDayViewHolder(arg$1, (AdapterEvent)obj5);
                    }

            .Lambda._cls2(int i)
            {
                arg$1 = i;
            }
                }

                if (j != 0)
                {
                    obj2 = itemView.getResources().getQuantityString(0x7f12002b, j, new Object[] {
                        Integer.valueOf(j)
                    });
                    obj = (new StringBuilder(String.valueOf(obj).length() + 2 + String.valueOf(obj2).length())).append(((String) (obj))).append(": ").append(((String) (obj2))).toString();
                }
                dayview.setContentDescription(((CharSequence) (obj)));
            }
        }
        obj2 = view;
        obj = monthAdapter.getMonthDay(i);
        if (((AdapterMonthDay) (obj)).getNumRows() <= ((Integer)maxEventsPerDay.get()).intValue()) goto _L2; else goto _L1
_L1:
        int k;
        boolean flag1;
        j = ((Integer)maxEventsPerDay.get()).intValue();
        obj = ((AdapterMonthDay) (obj)).getEvents();
        Object obj3 = new .Lambda._cls2(j - 1);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (obj3 == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), ((Predicate) (obj3)));
        boolean flag2;
        if (obj instanceof Collection)
        {
            k = ((Collection)obj).size();
        } else
        {
            obj = ((Iterable) (obj)).iterator();
            long l1;
            for (l1 = 0L; ((Iterator) (obj)).hasNext(); l1++)
            {
                ((Iterator) (obj)).next();
            }

            if (l1 > 0x7fffffffL)
            {
                k = 0x7fffffff;
            } else
            if (l1 < 0xffffffff80000000L)
            {
                k = 0x80000000;
            } else
            {
                k = (int)l1;
            }
        }
_L3:
        flag2 = MonthDayViewPositionHelper.isInCurrentMonth(i);
        obj = ((DayView) (obj2)).drawable;
        if (k > 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj.indicateHiddenEvents = flag1;
        if (k > 0)
        {
            obj3 = ((DayView) (obj2)).drawable.hiddenEventsPaint;
            obj = obj1;
            class .Lambda._cls3
                implements Predicate
            {

                private final MonthDayViewHolder arg$1;

                public final boolean apply(Object obj5)
                {
                    MonthDayViewHolder monthdayviewholder = arg$1;
                    return ((AdapterEvent)obj5).getMonthSlot() == ((Integer)monthdayviewholder.maxEventsPerDay.get()).intValue() - 1;
                }

            .Lambda._cls3()
            {
                arg$1 = MonthDayViewHolder.this;
            }
            }

            Object obj4;
            int i1;
            if (flag2)
            {
                if (Material.robotoMedium != null)
                {
                    obj = Material.robotoMedium;
                } else
                {
                    obj = Typeface.create("sans-serif-medium", 0);
                    Material.robotoMedium = ((Typeface) (obj));
                }
            }
            ((Paint) (obj3)).setTypeface(((Typeface) (obj)));
            ((DayView) (obj2)).drawable.hiddenEventsText = String.format(((DayView) (obj2)).getResources().getQuantityString(0x7f12002a, k), new Object[] {
                Integer.valueOf(k)
            });
        }
        ((DayView) (obj2)).invalidate();
        return;
_L2:
        if (((AdapterMonthDay) (obj)).getNumRows() < ((Integer)maxEventsPerDay.get()).intValue() || ((AdapterMonthDay) (obj)).getNumRows() == 0)
        {
            break MISSING_BLOCK_LABEL_1117;
        }
        obj = ((AdapterMonthDay) (obj)).getEvents();
        obj4 = new .Lambda._cls3();
        obj = (AdapterEvent)Iterators.find(((Iterable) (obj)).iterator(), ((Predicate) (obj4)));
        if (itemAdapter.getStartDay(((AdapterEvent) (obj)).getItem()) == itemAdapter.getEndDay(((AdapterEvent) (obj)).getItem()))
        {
            break MISSING_BLOCK_LABEL_1117;
        }
        k = MonthDayViewPositionHelper.toJulianDay(i);
        obj4 = timeUtils;
        i1 = (((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + k) / 7) * 7 - (2 - ((Integer)((TimeUtils) (obj4)).firstDayOfWeek.get()).intValue());
        k = Math.max(itemAdapter.getStartDay(((AdapterEvent) (obj)).getItem()), i1);
        i1 = Math.min(itemAdapter.getEndDay(((AdapterEvent) (obj)).getItem()), (i1 + 7) - 1);
_L4:
label0:
        {
            if (k > i1)
            {
                break MISSING_BLOCK_LABEL_1117;
            }
            if (monthAdapter.getMonthDay(MonthDayViewPositionHelper.fromJulianDay(k, true)).getNumRows() <= ((Integer)maxEventsPerDay.get()).intValue())
            {
                break label0;
            }
            k = 1;
        }
          goto _L3
        k++;
          goto _L4
        k = 0;
          goto _L3
    }
}
