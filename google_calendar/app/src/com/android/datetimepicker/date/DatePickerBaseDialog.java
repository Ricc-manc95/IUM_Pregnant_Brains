// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.datetimepicker.HapticFeedbackController;
import com.android.datetimepicker.Utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

// Referenced classes of package com.android.datetimepicker.date:
//            DatePickerController, DayPickerView, AccessibleDateAnimator, YearPickerView

public final class DatePickerBaseDialog
    implements android.view.View.OnClickListener, DatePickerController
{

    private static SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("dd", Locale.getDefault());
    private static SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy", Locale.getDefault());
    public AccessibleDateAnimator animator;
    public final Calendar calendar = Calendar.getInstance();
    public int currentView;
    public TextView dayOfWeekView;
    public String dayPickerDescription;
    public DayPickerView dayPickerView;
    private boolean delayAnimation;
    public final DismissibleDialog dialog;
    public Button doneButton;
    public HapticFeedbackController hapticFeedbackController;
    public HashSet listeners;
    public Calendar maxDate;
    public int maxYear;
    public Calendar minDate;
    public int minYear;
    public LinearLayout monthAndDayView;
    public boolean rtlEnabled;
    public String selectDay;
    public String selectYear;
    public TextView selectedDayTextView;
    public TextView selectedMonthTextView;
    public int weekStart;
    public String yearPickerDescription;
    public YearPickerView yearPickerView;
    public TextView yearView;

    DatePickerBaseDialog(DismissibleDialog dismissibledialog)
    {
        listeners = new HashSet();
        currentView = -1;
        weekStart = calendar.getFirstDayOfWeek();
        minYear = 1900;
        maxYear = 2100;
        delayAnimation = true;
        rtlEnabled = false;
        dialog = dismissibledialog;
    }

    private final void updatePickers()
    {
        for (Iterator iterator = listeners.iterator(); iterator.hasNext(); ((DatePickerDialog.OnDateChangedListener)iterator.next()).onDateChanged()) { }
    }

    public final int getFirstDayOfWeek()
    {
        return weekStart;
    }

    public final Calendar getMaxDate()
    {
        return maxDate;
    }

    public final int getMaxYear()
    {
        return maxYear;
    }

    public final Calendar getMinDate()
    {
        return minDate;
    }

    public final int getMinYear()
    {
        return minYear;
    }

    public final MonthAdapter.CalendarDay getSelectedDay()
    {
        return new MonthAdapter.CalendarDay(calendar);
    }

    public final void onClick(View view)
    {
        hapticFeedbackController.tryVibrate();
        if (view.getId() == 0x7f10015e)
        {
            setCurrentView(view.getContext(), 1);
        } else
        if (view.getId() == 0x7f10015b)
        {
            setCurrentView(view.getContext(), 0);
            return;
        }
    }

    public final void onDayOfMonthSelected(int i, int j, int k)
    {
        calendar.set(1, i);
        calendar.set(2, j);
        calendar.set(5, k);
        updatePickers();
        updateDisplay(dialog.getDialog().getContext(), true);
    }

    public final void onYearSelected(int i)
    {
        int k = calendar.get(2);
        int j = calendar.get(5);
        k = Utils.getDaysInMonth(k, i);
        if (j > k)
        {
            calendar.set(5, k);
        }
        calendar.set(1, i);
        updatePickers();
        setCurrentView(dialog.getDialog().getContext(), 0);
        updateDisplay(dialog.getDialog().getContext(), true);
    }

    public final void registerOnDateChangedListener(DatePickerDialog.OnDateChangedListener ondatechangedlistener)
    {
        listeners.add(ondatechangedlistener);
    }

    final void setCurrentView(Context context, int i)
    {
        long l = calendar.getTimeInMillis();
        i;
        JVM INSTR tableswitch 0 1: default 32
    //                   0 33
    //                   1 221;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        Object obj = Utils.getPulseAnimator(monthAndDayView, 0.9F, 1.05F);
        if (delayAnimation)
        {
            ((ObjectAnimator) (obj)).setStartDelay(500L);
            delayAnimation = false;
        }
        Object obj1 = dayPickerView;
        ((DayPickerView) (obj1)).goTo(((DayPickerView) (obj1)).controller.getSelectedDay(), false, true, true);
        if (currentView != i)
        {
            monthAndDayView.setSelected(true);
            yearView.setSelected(false);
            animator.setDisplayedChild(0);
            currentView = i;
        }
        ((ObjectAnimator) (obj)).start();
        context = DateUtils.formatDateTime(context, l, 16);
        obj = animator;
        obj1 = dayPickerDescription;
        ((AccessibleDateAnimator) (obj)).setContentDescription((new StringBuilder(String.valueOf(obj1).length() + 2 + String.valueOf(context).length())).append(((String) (obj1))).append(": ").append(context).toString());
        context = animator;
        obj = selectDay;
        if (context != null && obj != null)
        {
            context.announceForAccessibility(((CharSequence) (obj)));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        context = Utils.getPulseAnimator(yearView, 0.85F, 1.1F);
        if (delayAnimation)
        {
            context.setStartDelay(500L);
            delayAnimation = false;
        }
        yearPickerView.onDateChanged();
        if (currentView != i)
        {
            monthAndDayView.setSelected(false);
            yearView.setSelected(true);
            animator.setDisplayedChild(1);
            currentView = i;
        }
        context.start();
        String s1 = YEAR_FORMAT.format(Long.valueOf(l));
        context = animator;
        String s = yearPickerDescription;
        s1 = String.valueOf(s1);
        context.setContentDescription((new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(s1).length())).append(s).append(": ").append(s1).toString());
        context = animator;
        s = selectYear;
        if (context != null && s != null)
        {
            context.announceForAccessibility(s);
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public final void tryVibrate()
    {
        hapticFeedbackController.tryVibrate();
    }

    final void updateDisplay(Context context, boolean flag)
    {
        if (dayOfWeekView != null)
        {
            dayOfWeekView.setText(calendar.getDisplayName(7, 2, Locale.getDefault()).toUpperCase(Locale.getDefault()));
        }
        selectedMonthTextView.setText(calendar.getDisplayName(2, 1, Locale.getDefault()).toUpperCase(Locale.getDefault()));
        selectedDayTextView.setText(DAY_FORMAT.format(calendar.getTime()));
        yearView.setText(YEAR_FORMAT.format(calendar.getTime()));
        long l = calendar.getTimeInMillis();
        animator.setDateMillis(l);
        String s = DateUtils.formatDateTime(context, l, 24);
        monthAndDayView.setContentDescription(s);
        if (flag)
        {
            context = DateUtils.formatDateTime(context, l, 20);
            AccessibleDateAnimator accessibledateanimator = animator;
            if (accessibledateanimator != null && context != null)
            {
                accessibledateanimator.announceForAccessibility(context);
            }
        }
    }


    private class DismissibleDialog
    {

        public abstract void dismiss();

        public abstract Dialog getDialog();

        public abstract void onDateSet(Calendar calendar1);
    }

}
