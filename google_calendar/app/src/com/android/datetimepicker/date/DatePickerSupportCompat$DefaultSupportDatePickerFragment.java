// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.widget.DatePicker;
import com.android.datetimepicker.SupportDialogFragmentWithListener;

public final class firstDayOfWeek extends SupportDialogFragmentWithListener
{

    public android.app.tDatePickerFragment.firstDayOfWeek dateListener;
    public int firstDayOfWeek;
    public long maxDateMillis;
    public long minDateMillis;

    public final Dialog onCreateDialog(Bundle bundle)
    {
        Object obj = dateListener;
        Object obj1 = super.mTarget;
        if (obj1 instanceof dateListener)
        {
            obj = new dateListener((dateListener)obj1);
        }
        Bundle bundle1;
        int i;
        int j;
        int k;
        int l;
        long l1;
        long l2;
        if (super.mHost == null)
        {
            obj1 = null;
        } else
        {
            obj1 = (FragmentActivity)super.mHost.mActivity;
        }
        bundle1 = getArguments();
        i = firstDayOfWeek;
        l2 = minDateMillis;
        l1 = maxDateMillis;
        j = bundle1.getInt("year");
        k = bundle1.getInt("month");
        l = bundle1.getInt("day");
        if (bundle != null)
        {
            i = bundle.getInt("first_day_of_week");
            l2 = bundle.getLong("min_date");
            l1 = bundle.getLong("max_date");
        }
        bundle = new DatePickerDialog(((android.content.Context) (obj1)), ((android.app.tDatePickerFragment.maxDateMillis) (obj)), j, k, l);
        obj = bundle.getDatePicker();
        if (l2 != -1L)
        {
            ((DatePicker) (obj)).setMinDate(l2);
        }
        if (l1 != -1L)
        {
            ((DatePicker) (obj)).setMaxDate(l1);
        }
        if (i != -1)
        {
            ((DatePicker) (obj)).setFirstDayOfWeek(i);
        }
        return bundle;
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        long l = minDateMillis;
        long l1 = maxDateMillis;
        int i = firstDayOfWeek;
        bundle.putLong("min_date", l);
        bundle.putLong("max_date", l1);
        bundle.putInt("first_day_of_week", i);
    }

    public ()
    {
        minDateMillis = -1L;
        maxDateMillis = -1L;
        firstDayOfWeek = -1;
    }
}
