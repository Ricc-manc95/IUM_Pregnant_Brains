// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import com.android.datetimepicker.date.DatePickerSupportCompat;
import com.android.datetimepicker.time.TimePickerSupportCompat;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import java.util.Calendar;

public final class DateTimePickerFactory
{

    public DateTimePickerFactory()
    {
    }

    static void showTimePicker(Fragment fragment, Calendar calendar)
    {
        if (fragment == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = fragment.mFragmentManager;
        if (fragment == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (fragment.mHost != null && fragment.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = false;
_L7:
        if (!flag)
        {
            return;
        }
        break; /* Loop/switch isn't completed */
_L5:
        FragmentActivity fragmentactivity;
        if (fragment.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)fragment.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
        TimePickerSupportCompat timepickersupportcompat = new TimePickerSupportCompat((com.android.datetimepicker.time.TimePickerCompat.OnTimeSetListener)fragment);
        timepickersupportcompat.initialize(calendar.get(11), calendar.get(12), DateFormat.is24HourFormat(fragment.getContext()));
        if (timepickersupportcompat.fragment == null)
        {
            throw new IllegalStateException("Tried to set target fragment before #initialize(...) was called.");
        } else
        {
            timepickersupportcompat.fragment.setTargetFragment(fragment, 0);
            fragment = fragment.mFragmentManager;
            calendar = timepickersupportcompat.fragment;
            fragment.beginTransaction().add(calendar, "TIME_PICKER_FRAGMENT_TAG").commitAllowingStateLoss();
            return;
        }
    }

    final void showDatePickerWithMinDate(Fragment fragment, Calendar calendar, Calendar calendar1)
    {
        if (fragment == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = fragment.mFragmentManager;
        if (fragment == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (fragment.mHost != null && fragment.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = false;
_L7:
        if (!flag)
        {
            return;
        }
        break; /* Loop/switch isn't completed */
_L5:
        FragmentActivity fragmentactivity;
        if (fragment.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)fragment.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
        DatePickerSupportCompat datepickersupportcompat = new DatePickerSupportCompat((com.android.datetimepicker.date.DatePickerCompat.OnDateSetListener)fragment);
        datepickersupportcompat.setFirstDayOfWeek(PreferenceUtils.getFirstDayOfWeekAsCalendar(fragment.getContext()));
        datepickersupportcompat.setRtlEnabled(true);
        if (calendar1 != null)
        {
            Calendar calendar2 = calendar1;
            if (calendar.compareTo(calendar1) < 0)
            {
                calendar2 = calendar;
            }
            datepickersupportcompat.setMinDate(calendar2);
        }
        datepickersupportcompat.initialize(calendar.get(1), calendar.get(2), calendar.get(5));
        if (datepickersupportcompat.fragment == null)
        {
            throw new IllegalStateException("Tried to set target fragment before #initialize(...) was called.");
        } else
        {
            datepickersupportcompat.fragment.setTargetFragment(fragment, 0);
            fragment = fragment.mFragmentManager;
            calendar = datepickersupportcompat.fragment;
            fragment.beginTransaction().add(calendar, "DATE_PICKER_FRAGMENT_TAG").commitAllowingStateLoss();
            return;
        }
    }
}
