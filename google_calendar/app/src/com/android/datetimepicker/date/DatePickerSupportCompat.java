// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import com.android.datetimepicker.SupportDialogFragmentListener;
import com.android.datetimepicker.SupportDialogFragmentWithListener;
import java.util.Calendar;

// Referenced classes of package com.android.datetimepicker.date:
//            DatePickerBaseCompat

public final class DatePickerSupportCompat extends DatePickerBaseCompat
{

    public final DatePickerCompat.OnDateSetListener dateListener;
    public SupportDialogFragmentListener dialogFragmentListener;
    public DialogFragment fragment;

    public DatePickerSupportCompat(DatePickerCompat.OnDateSetListener ondatesetlistener)
    {
        dateListener = ondatesetlistener;
    }

    public final void initialize(int i, int j, int k)
    {
        if (fragment != null)
        {
            fragment.dismiss();
        }
        DefaultSupportDatePickerFragment defaultsupportdatepickerfragment;
        Object obj;
        int l;
        long l1;
        if (firstDayOfWeek == -1)
        {
            l = Calendar.getInstance().getFirstDayOfWeek();
        } else
        {
            l = firstDayOfWeek;
        }
        defaultsupportdatepickerfragment = new DefaultSupportDatePickerFragment();
        obj = new Bundle(3);
        ((Bundle) (obj)).putInt("year", i);
        ((Bundle) (obj)).putInt("month", j);
        ((Bundle) (obj)).putInt("day", k);
        defaultsupportdatepickerfragment.setArguments(((Bundle) (obj)));
        defaultsupportdatepickerfragment.dateListener = new DatePickerCompat.DefaultDateSetListenerWrapper(dateListener);
        defaultsupportdatepickerfragment.dialogListener = dialogFragmentListener;
        obj = minDate;
        if (obj == null)
        {
            l1 = -1L;
        } else
        {
            l1 = ((Calendar) (obj)).getTimeInMillis();
        }
        defaultsupportdatepickerfragment.minDateMillis = l1;
        if (true)
        {
            defaultsupportdatepickerfragment.maxDateMillis = -1L;
            defaultsupportdatepickerfragment.firstDayOfWeek = l;
            fragment = defaultsupportdatepickerfragment;
            return;
        } else
        {
            throw new NullPointerException();
        }
    }

    public final volatile void setFirstDayOfWeek(int i)
    {
        super.setFirstDayOfWeek(i);
    }

    public final volatile void setMinDate(Calendar calendar)
    {
        super.setMinDate(calendar);
    }

    public final volatile void setRtlEnabled(boolean flag)
    {
        super.setRtlEnabled(flag);
    }

    private class DefaultSupportDatePickerFragment extends SupportDialogFragmentWithListener
    {

        public android.app.DatePickerDialog.OnDateSetListener dateListener;
        public int firstDayOfWeek;
        public long maxDateMillis;
        public long minDateMillis;

        public final Dialog onCreateDialog(Bundle bundle)
        {
            Object obj = dateListener;
            Object obj1 = super.mTarget;
            if (obj1 instanceof DatePickerCompat.OnDateSetListener)
            {
                obj = new DatePickerCompat.DefaultDateSetListenerWrapper((DatePickerCompat.OnDateSetListener)obj1);
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
            bundle = new DatePickerDialog(((android.content.Context) (obj1)), ((android.app.DatePickerDialog.OnDateSetListener) (obj)), j, k, l);
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

        public DefaultSupportDatePickerFragment()
        {
            minDateMillis = -1L;
            maxDateMillis = -1L;
            firstDayOfWeek = -1;
        }
    }

}
