// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.datetimepicker.HapticFeedbackController;
import com.android.datetimepicker.SupportDialogFragmentWithListener;
import java.util.Calendar;
import java.util.HashSet;

// Referenced classes of package com.android.datetimepicker.date:
//            DatePickerController, DatePickerBaseDialog, DayPickerView, SimpleDayPickerView, 
//            MonthAdapter, YearPickerView, AccessibleDateAnimator

public final class DatePickerSupportDialog extends SupportDialogFragmentWithListener
    implements DatePickerBaseDialog.DismissibleDialog, DatePickerController
{

    public OnDateSetListener callBack;
    private final DatePickerBaseDialog dialog = new DatePickerBaseDialog(this);

    public DatePickerSupportDialog()
    {
    }

    public final int getFirstDayOfWeek()
    {
        return dialog.weekStart;
    }

    public final Calendar getMaxDate()
    {
        return dialog.maxDate;
    }

    public final int getMaxYear()
    {
        return dialog.maxYear;
    }

    public final Calendar getMinDate()
    {
        return dialog.minDate;
    }

    public final int getMinYear()
    {
        return dialog.minYear;
    }

    public final MonthAdapter.CalendarDay getSelectedDay()
    {
        return new MonthAdapter.CalendarDay(dialog.calendar);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        DatePickerBaseDialog datepickerbasedialog = dialog;
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        fragmentactivity.getWindow().setSoftInputMode(3);
        if (bundle != null)
        {
            datepickerbasedialog.calendar.set(1, bundle.getInt("year"));
            datepickerbasedialog.calendar.set(2, bundle.getInt("month"));
            datepickerbasedialog.calendar.set(5, bundle.getInt("day"));
        }
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        bundle = super.onCreateDialog(bundle);
        Fragment fragment = super.mTarget;
        if (fragment instanceof DatePickerCompat.OnDateSetListener)
        {
            callBack = new DatePickerSupportCompat.LibraryDateSetSupportListenerWrapper((DatePickerCompat.OnDateSetListener)fragment);
        }
        return bundle;
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        DatePickerBaseDialog datepickerbasedialog;
        int i;
        int j;
        int k;
        datepickerbasedialog = dialog;
        boolean flag;
        if (super.mHost == null)
        {
            viewgroup = null;
        } else
        {
            viewgroup = (FragmentActivity)super.mHost.mActivity;
        }
        datepickerbasedialog.dialog.getDialog().getWindow().requestFeature(1);
        layoutinflater = layoutinflater.inflate(0x7f05003a, null);
        datepickerbasedialog.dayOfWeekView = (TextView)layoutinflater.findViewById(0x7f100159);
        datepickerbasedialog.monthAndDayView = (LinearLayout)layoutinflater.findViewById(0x7f10015b);
        datepickerbasedialog.monthAndDayView.setOnClickListener(datepickerbasedialog);
        datepickerbasedialog.selectedMonthTextView = (TextView)layoutinflater.findViewById(0x7f10015c);
        datepickerbasedialog.selectedDayTextView = (TextView)layoutinflater.findViewById(0x7f10015d);
        datepickerbasedialog.yearView = (TextView)layoutinflater.findViewById(0x7f10015e);
        datepickerbasedialog.yearView.setOnClickListener(datepickerbasedialog);
        k = -1;
        j = 0;
        i = 0;
        if (bundle != null)
        {
            datepickerbasedialog.weekStart = bundle.getInt("week_start");
            datepickerbasedialog.minYear = bundle.getInt("year_start");
            datepickerbasedialog.maxYear = bundle.getInt("year_end");
            int l = bundle.getInt("current_view");
            int i1 = bundle.getInt("list_position");
            int j1 = bundle.getInt("list_position_offset");
            if (bundle.containsKey("min_date"))
            {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(bundle.getLong("min_date"));
                datepickerbasedialog.minDate = calendar;
                if (datepickerbasedialog.dayPickerView != null)
                {
                    datepickerbasedialog.dayPickerView.refreshAdapter();
                }
            }
            i = l;
            j = j1;
            k = i1;
            if (bundle.containsKey("max_date"))
            {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTimeInMillis(bundle.getLong("max_date"));
                datepickerbasedialog.maxDate = calendar1;
                i = l;
                j = j1;
                k = i1;
                if (datepickerbasedialog.dayPickerView != null)
                {
                    datepickerbasedialog.dayPickerView.refreshAdapter();
                    k = i1;
                    j = j1;
                    i = l;
                }
            }
        }
        datepickerbasedialog.dayPickerView = new SimpleDayPickerView(viewgroup, datepickerbasedialog);
        bundle = datepickerbasedialog.dayPickerView;
        flag = datepickerbasedialog.rtlEnabled;
        if (((DayPickerView) (bundle)).mAdapter != null)
        {
            ((DayPickerView) (bundle)).mAdapter.rtlEnabled = flag;
        }
        datepickerbasedialog.yearPickerView = new YearPickerView(viewgroup, datepickerbasedialog);
        bundle = viewgroup.getResources();
        datepickerbasedialog.dayPickerDescription = bundle.getString(0x7f130154);
        datepickerbasedialog.selectDay = bundle.getString(0x7f130433);
        datepickerbasedialog.yearPickerDescription = bundle.getString(0x7f1304c8);
        datepickerbasedialog.selectYear = bundle.getString(0x7f130436);
        datepickerbasedialog.animator = (AccessibleDateAnimator)layoutinflater.findViewById(0x7f10015f);
        datepickerbasedialog.animator.addView(datepickerbasedialog.dayPickerView);
        datepickerbasedialog.animator.addView(datepickerbasedialog.yearPickerView);
        datepickerbasedialog.animator.setDateMillis(datepickerbasedialog.calendar.getTimeInMillis());
        bundle = new AlphaAnimation(0.0F, 1.0F);
        bundle.setDuration(300L);
        datepickerbasedialog.animator.setInAnimation(bundle);
        bundle = new AlphaAnimation(1.0F, 0.0F);
        bundle.setDuration(300L);
        datepickerbasedialog.animator.setOutAnimation(bundle);
        datepickerbasedialog.doneButton = (Button)layoutinflater.findViewById(0x7f100156);
        datepickerbasedialog.doneButton.setOnClickListener(new DatePickerBaseDialog._cls1(datepickerbasedialog));
        datepickerbasedialog.updateDisplay(viewgroup, false);
        datepickerbasedialog.setCurrentView(viewgroup, i);
        if (k == -1) goto _L2; else goto _L1
_L1:
        if (i != 0) goto _L4; else goto _L3
_L3:
        bundle = datepickerbasedialog.dayPickerView;
        bundle.clearFocus();
        bundle.post(new DayPickerView._cls1(bundle, k));
        bundle.onScrollStateChanged(bundle, 0);
_L2:
        datepickerbasedialog.hapticFeedbackController = new HapticFeedbackController(viewgroup);
        return layoutinflater;
_L4:
        if (i == 1)
        {
            bundle = datepickerbasedialog.yearPickerView;
            bundle.post(new YearPickerView._cls1(bundle, k, j));
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public final void onDateSet(Calendar calendar)
    {
        if (callBack != null)
        {
            callBack._mth51666RRD5TGMSP3IDTKM8BR4C5Q6AT39DLIN0QB3DDIN4BR4C5Q6ABQ4C5Q6AK39CDLMASIJELO70RRIEH26IOBCDTJJMIA994KLC___0(calendar.get(1), calendar.get(2), calendar.get(5));
        }
    }

    public final void onDayOfMonthSelected(int i, int j, int k)
    {
        dialog.onDayOfMonthSelected(i, j, k);
    }

    public final void onPause()
    {
        super.onPause();
        HapticFeedbackController hapticfeedbackcontroller = dialog.hapticFeedbackController;
        hapticfeedbackcontroller.vibrator = null;
        hapticfeedbackcontroller.context.getContentResolver().unregisterContentObserver(hapticfeedbackcontroller.contentObserver);
    }

    public final void onResume()
    {
        super.onResume();
        dialog.hapticFeedbackController.start();
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        DatePickerBaseDialog datepickerbasedialog;
        int i;
        super.onSaveInstanceState(bundle);
        datepickerbasedialog = dialog;
        bundle.putInt("year", datepickerbasedialog.calendar.get(1));
        bundle.putInt("month", datepickerbasedialog.calendar.get(2));
        bundle.putInt("day", datepickerbasedialog.calendar.get(5));
        bundle.putInt("week_start", datepickerbasedialog.weekStart);
        bundle.putInt("year_start", datepickerbasedialog.minYear);
        bundle.putInt("year_end", datepickerbasedialog.maxYear);
        bundle.putInt("current_view", datepickerbasedialog.currentView);
        i = -1;
        if (datepickerbasedialog.currentView != 0) goto _L2; else goto _L1
_L1:
        DayPickerView daypickerview = datepickerbasedialog.dayPickerView;
        int k1 = daypickerview.getFirstVisiblePosition();
        int l1 = daypickerview.getHeight();
        int i1 = 0;
        i = 0;
        int l = 0;
        int j = 0;
        while (i1 < l1) 
        {
            View view1 = daypickerview.getChildAt(i);
            if (view1 == null)
            {
                break;
            }
            i1 = view1.getBottom();
            int j1 = Math.min(i1, l1) - Math.max(0, view1.getTop());
            if (j1 > j)
            {
                l = i;
                j = j1;
            }
            i++;
        }
        i = l + k1;
_L4:
        bundle.putInt("list_position", i);
        if (datepickerbasedialog.minDate != null)
        {
            bundle.putLong("min_date", datepickerbasedialog.minDate.getTimeInMillis());
        }
        if (datepickerbasedialog.maxDate != null)
        {
            bundle.putLong("max_date", datepickerbasedialog.maxDate.getTimeInMillis());
        }
        return;
_L2:
        if (datepickerbasedialog.currentView == 1)
        {
            int k = datepickerbasedialog.yearPickerView.getFirstVisiblePosition();
            View view = datepickerbasedialog.yearPickerView.getChildAt(0);
            if (view == null)
            {
                i = 0;
            } else
            {
                i = view.getTop();
            }
            bundle.putInt("list_position_offset", i);
            i = k;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onYearSelected(int i)
    {
        dialog.onYearSelected(i);
    }

    public final void registerOnDateChangedListener(DatePickerDialog.OnDateChangedListener ondatechangedlistener)
    {
        dialog.listeners.add(ondatechangedlistener);
    }

    public final void tryVibrate()
    {
        dialog.hapticFeedbackController.tryVibrate();
    }

    private class OnDateSetListener
    {

        public abstract void onDateSet$51666RRD5TGMSP3IDTKM8BR4C5Q6AT39DLIN0QB3DDIN4BR4C5Q6ABQ4C5Q6AK39CDLMASIJELO70RRIEH26IOBCDTJJMIA994KLC___0(int i, int j, int k);
    }

}
