// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import android.content.Intent;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.time.ByDayRecurrence;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

// Referenced classes of package com.google.android.calendar.recurrencepicker:
//            RecurrencePickerActivity, RecurrencePickerView, RecurrencePickerState, StateConverter

final class arg._cls1
    implements android.view.urrencePickerActivity..Lambda._cls0
{

    private final RecurrencePickerActivity arg$1;

    public final void onClick(View view)
    {
        Intent intent;
        Object obj;
        Object obj1;
        view = arg$1;
        view.hideKeyboard();
        intent = new Intent();
        obj = ((RecurrencePickerActivity) (view)).view.state;
        obj1 = ((RecurrencePickerState) (obj)).getFrequency();
        ((rd) (obj1)).inal();
        JVM INSTR tableswitch 0 3: default 68
    //                   0 111
    //                   1 327
    //                   2 333
    //                   3 339;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        view = String.valueOf(obj1);
        throw new IllegalStateException((new StringBuilder(String.valueOf(view).length() + 28)).append("RecurrencePickerState.Freq: ").append(view).toString());
_L2:
        int i = 3;
_L14:
        obj1 = new com.google.android.calendar.api.event.time.rdinal(i);
        ((RecurrencePickerState) (obj)).getFrequency().inal();
        JVM INSTR tableswitch 0 2: default 160
    //                   0 160
    //                   1 346
    //                   2 451;
           goto _L6 _L6 _L7 _L8
_L6:
        Object obj2;
        ArrayList arraylist;
        Object obj4;
        int j;
        if (((RecurrencePickerState) (obj)).getEnd().inal(inal))
        {
            obj1.Millis = ((RecurrencePickerState) (obj)).getUntilDateTimeMillis();
        } else
        if (((RecurrencePickerState) (obj)).getEnd().imeMillis(imeMillis))
        {
            ((com.google.android.calendar.api.event.time.eTimeMillis) (obj1)).eTimeMillis(((RecurrencePickerState) (obj)).getCount());
        } else
        {
            obj1.Millis = null;
            ((com.google.android.calendar.api.event.time.Millis) (obj1)).Millis(null);
        }
        ((com.google.android.calendar.api.event.time.Millis) (obj1)).Millis(((RecurrencePickerState) (obj)).getInterval()).();
        obj1. = Integer.valueOf(StateConverter.calendarWeekdayToApiWeekday(((RecurrencePickerState) (obj)).getFirstDayOfWeek().intValue()));
        intent.putExtra("recurrence_result", ((com.google.android.calendar.api.event.time.OfWeek) (obj1)).OfWeek().toRfc5545String());
        obj = RecurrencePickerActivity.TAG;
        obj1 = ((RecurrencePickerActivity) (view)).view.state;
        obj2 = ((RecurrencePickerState) (obj1)).getFrequency();
        ((Week) (obj2)).inal();
        JVM INSTR tableswitch 0 3: default 284
    //                   0 603
    //                   1 742
    //                   2 748
    //                   3 754;
           goto _L9 _L10 _L11 _L12 _L13
_L9:
        view = String.valueOf(obj2);
        throw new IllegalStateException((new StringBuilder(String.valueOf(view).length() + 28)).append("RecurrencePickerState.Freq: ").append(view).toString());
_L3:
        i = 4;
          goto _L14
_L4:
        i = 5;
          goto _L14
_L5:
        i = 6;
          goto _L14
_L7:
        arraylist = new ArrayList(((RecurrencePickerState) (obj)).getByDay());
        Collections.sort(arraylist);
        obj2 = new ArrayList();
        arraylist = (ArrayList)arraylist;
        j = arraylist.size();
        for (i = 0; i < j;)
        {
            obj4 = arraylist.get(i);
            i++;
            ((ArrayList) (obj2)).add(new ByDayRecurrence(StateConverter.calendarWeekdayToApiWeekday(((Integer)obj4).intValue()), null));
        }

        ((com.google.android.calendar.api.event.time.piWeekday) (obj1)).piWeekday(((java.util.List) (obj2)));
          goto _L6
_L8:
        if (((RecurrencePickerState) (obj)).getMonthFrequency() == y.MONTHDAY)
        {
            ((com.google.android.calendar.api.event.time.ncy.MONTHDAY) (obj1)).(ImmutableList.copyOf(((RecurrencePickerState) (obj)).getByMonthDay()));
        } else
        {
            j = StateConverter.calendarWeekdayToApiWeekday(((RecurrencePickerState) (obj)).getStartWeekday().intValue());
            if (((RecurrencePickerState) (obj)).getMonthFrequency() == y.LAST)
            {
                i = -1;
            } else
            {
                obj2 = Calendar.getInstance(((RecurrencePickerState) (obj)).getTimeZone());
                ((Calendar) (obj2)).setTimeInMillis(((RecurrencePickerState) (obj)).getStartTimeInMillis().longValue());
                i = ((Calendar) (obj2)).get(8);
            }
            ((com.google.android.calendar.api.event.time.eInMillis) (obj1)).eInMillis(ImmutableList.of(new ByDayRecurrence(j, Integer.valueOf(i))));
        }
          goto _L6
_L10:
        i = 3;
_L18:
        com.google.android.calendar.api.event.time._cls0 _lcls0 = new com.google.android.calendar.api.event.time.eInMillis(i);
        ((RecurrencePickerState) (obj1)).getFrequency().inal();
        JVM INSTR tableswitch 0 2: default 652
    //                   0 652
    //                   1 761
    //                   2 867;
           goto _L15 _L15 _L16 _L17
_L15:
        Object obj3;
        ArrayList arraylist1;
        Object obj5;
        int k;
        if (((RecurrencePickerState) (obj1)).getEnd().inal(inal))
        {
            _lcls0.Millis = ((RecurrencePickerState) (obj1)).getUntilDateTimeMillis();
        } else
        if (((RecurrencePickerState) (obj1)).getEnd().imeMillis(imeMillis))
        {
            _lcls0.eTimeMillis(((RecurrencePickerState) (obj1)).getCount());
        } else
        {
            _lcls0.Millis = null;
            _lcls0.Millis(null);
        }
        _lcls0.Millis(((RecurrencePickerState) (obj1)).getInterval()).();
        _lcls0. = Integer.valueOf(StateConverter.calendarWeekdayToApiWeekday(((RecurrencePickerState) (obj1)).getFirstDayOfWeek().intValue()));
        LogUtils.i(((String) (obj)), "RRULE: %s", new Object[] {
            _lcls0.OfWeek().toRfc5545String()
        });
        view.setResult(-1, intent);
        view.finish();
        return;
_L11:
        i = 4;
          goto _L18
_L12:
        i = 5;
          goto _L18
_L13:
        i = 6;
          goto _L18
_L16:
        arraylist1 = new ArrayList(((RecurrencePickerState) (obj1)).getByDay());
        Collections.sort(arraylist1);
        obj3 = new ArrayList();
        arraylist1 = (ArrayList)arraylist1;
        k = arraylist1.size();
        for (i = 0; i < k;)
        {
            obj5 = arraylist1.get(i);
            i++;
            ((ArrayList) (obj3)).add(new ByDayRecurrence(StateConverter.calendarWeekdayToApiWeekday(((Integer)obj5).intValue()), null));
        }

        _lcls0.piWeekday(((java.util.List) (obj3)));
          goto _L15
_L17:
        if (((RecurrencePickerState) (obj1)).getMonthFrequency() == y.MONTHDAY)
        {
            _lcls0.(ImmutableList.copyOf(((RecurrencePickerState) (obj1)).getByMonthDay()));
        } else
        {
            k = StateConverter.calendarWeekdayToApiWeekday(((RecurrencePickerState) (obj1)).getStartWeekday().intValue());
            if (((RecurrencePickerState) (obj1)).getMonthFrequency() == y.LAST)
            {
                i = -1;
            } else
            {
                obj3 = Calendar.getInstance(((RecurrencePickerState) (obj1)).getTimeZone());
                ((Calendar) (obj3)).setTimeInMillis(((RecurrencePickerState) (obj1)).getStartTimeInMillis().longValue());
                i = ((Calendar) (obj3)).get(8);
            }
            _lcls0.eInMillis(ImmutableList.of(new ByDayRecurrence(k, Integer.valueOf(i))));
        }
          goto _L15
    }

    y(RecurrencePickerActivity recurrencepickeractivity)
    {
        arg$1 = recurrencepickeractivity;
    }
}
