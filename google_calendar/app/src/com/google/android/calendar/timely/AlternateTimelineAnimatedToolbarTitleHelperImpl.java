// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.AllInOneCalendarActivity;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.time.Time;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthLabelProvider, TrommelAnimationHelper

public final class AlternateTimelineAnimatedToolbarTitleHelperImpl extends ViewPropertyAnimatorListenerAdapter
{

    public final Activity activity;
    public TrommelAnimationHelper animationHelper;
    private final MonthLabelProvider monthLabelProvider;
    public TextView secondaryAlternateCalendarView;
    public TextView secondaryDateView;
    public int targetDay;
    public Consumer updateRangeAction;

    public AlternateTimelineAnimatedToolbarTitleHelperImpl(Activity activity1, MonthLabelProvider monthlabelprovider)
    {
        activity = activity1;
        monthLabelProvider = monthlabelprovider;
    }

    public final void onAnimationEnd(View view)
    {
        secondaryDateView.setText("");
        updateRangeAction.accept(Integer.valueOf(targetDay));
    }

    public final void onAnimationStart(View view)
    {
        int i = 1;
        boolean flag = false;
        int j = targetDay;
        view = new Time();
        view.setJulianDaySafe(j);
        TextView textview = secondaryDateView;
        MonthLabelProvider monthlabelprovider = monthLabelProvider;
        view.writeFieldsToImpl();
        long l = ((Time) (view)).impl.toMillis(false);
        int k = ((Time) (view)).year;
        boolean flag2 = monthlabelprovider.isTablet;
        boolean flag1;
        if (k == monthlabelprovider.todayYear)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        textview.setText(AllInOneCalendarActivity.computeMonthLabel(l, Long.valueOf(l), DateTimeFormatHelper.getToolbarFormatFlags(flag2, flag1)));
        view = monthLabelProvider.getAlternateCalendarMonthLabel(j);
        textview = secondaryAlternateCalendarView;
        if (TextUtils.isEmpty(view))
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
}
