// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.calendar.AllInOneCalendarActivity;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;

// Referenced classes of package com.google.android.calendar.timely:
//            TrommelAnimationHelper, MonthLabelProvider, TimelyDayView, AnimationThresholdEvaluator

public final class AnimatedToolbarTitleHelper extends ViewPropertyAnimatorListenerAdapter
{

    public final TrommelAnimationHelper animationHelper;
    public TimelyDayView dayView;
    public int firstDayOfMonthJulianDay;
    public final AnimatedActionbarTitleListener listener;
    private final MonthLabelProvider monthLabelProvider;
    private final TextView secondaryAlternateCalendarView;
    private final TextView secondaryDateView;
    public final AnimationThresholdEvaluator thresholdEvaluator;
    public final Time time;

    AnimatedToolbarTitleHelper(View view, View view1, AnimationThresholdEvaluator animationthresholdevaluator, MonthLabelProvider monthlabelprovider, AnimatedActionbarTitleListener animatedactionbartitlelistener)
    {
        time = new Time(Utils.getTimeZoneId(view.getContext()));
        monthLabelProvider = monthlabelprovider;
        listener = animatedactionbartitlelistener;
        animationHelper = new TrommelAnimationHelper(view, view1, this);
        thresholdEvaluator = animationthresholdevaluator;
        secondaryDateView = (TextView)view1.findViewById(0x7f100106);
        secondaryAlternateCalendarView = (TextView)view1.findViewById(0x7f100107);
    }

    public final void onAnimationEnd(View view)
    {
        view = listener;
        int i;
        if (animationHelper.reversedAnimation)
        {
            i = firstDayOfMonthJulianDay - 1;
        } else
        {
            i = firstDayOfMonthJulianDay;
        }
        view.onDayChanged(i);
        secondaryDateView.setText("");
    }

    public final void onAnimationStart(View view)
    {
        boolean flag1 = true;
        boolean flag = false;
        TextView textview;
        MonthLabelProvider monthlabelprovider;
        int i;
        int j;
        long l;
        boolean flag2;
        boolean flag3;
        if (animationHelper.reversedAnimation)
        {
            i = firstDayOfMonthJulianDay - 1;
        } else
        {
            i = firstDayOfMonthJulianDay;
        }
        view = new Time();
        view.setJulianDaySafe(i);
        textview = secondaryDateView;
        monthlabelprovider = monthLabelProvider;
        view.writeFieldsToImpl();
        l = ((Time) (view)).impl.toMillis(false);
        j = ((Time) (view)).year;
        flag3 = monthlabelprovider.isTablet;
        if (j == monthlabelprovider.todayYear)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        textview.setText(AllInOneCalendarActivity.computeMonthLabel(l, Long.valueOf(l), DateTimeFormatHelper.getToolbarFormatFlags(flag3, flag2)));
        view = monthLabelProvider.getAlternateCalendarMonthLabel(i);
        textview = secondaryAlternateCalendarView;
        if (!TextUtils.isEmpty(view))
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (textview != null)
        {
            if (i != 0)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            textview.setVisibility(i);
        }
        secondaryAlternateCalendarView.setText(view);
    }

    private class AnimatedActionbarTitleListener
    {

        public abstract void onDayChanged(int i);
    }

}
