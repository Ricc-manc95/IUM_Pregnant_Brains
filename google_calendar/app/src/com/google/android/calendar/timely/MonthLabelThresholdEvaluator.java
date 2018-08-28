// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.text.TextUtils;
import android.widget.TextView;
import com.google.android.calendar.time.Time;

// Referenced classes of package com.google.android.calendar.timely:
//            AnimationThresholdEvaluator, MonthLabelProvider

final class MonthLabelThresholdEvaluator
    implements AnimationThresholdEvaluator
{

    private final MonthLabelProvider monthLabelProvider;
    private final TextView monthLabelView;

    MonthLabelThresholdEvaluator(TextView textview, MonthLabelProvider monthlabelprovider)
    {
        monthLabelView = textview;
        monthLabelProvider = monthlabelprovider;
    }

    public final boolean canAnimate(Object obj)
    {
        Object obj1 = (State)obj;
        if (((State) (obj1)).time.monthDay == 1)
        {
            int i;
            if (Math.abs(((State) (obj1)).scrollDeltaY) <= 90F)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                obj = monthLabelView.getText();
                Time time = new Time(((State) (obj1)).time.timezone);
                if (((State) (obj1)).scrollDeltaY >= 0.0F)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    i = ((State) (obj1)).julianDay;
                } else
                {
                    i = ((State) (obj1)).julianDay - 1;
                }
                time.setJulianDaySafe(i);
                obj1 = monthLabelProvider.getMonthLabel(time);
                if (!TextUtils.isEmpty(((CharSequence) (obj1))) && !TextUtils.equals(((CharSequence) (obj)), ((CharSequence) (obj1))))
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isAnimationThresholdMet(Object obj)
    {
        obj = (State)obj;
        boolean flag;
        if (((State) (obj)).scrollDeltaY >= 0.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (((State) (obj)).monthViewTopOffset > -((State) (obj)).monthLabelTextBottomOffset) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (((State) (obj)).monthViewTopOffset < -((State) (obj)).monthLabelTextBottomOffset)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    private class State
    {

        public final int julianDay;
        public final int monthLabelTextBottomOffset;
        public final int monthViewTopOffset;
        public final float scrollDeltaY;
        public final Time time;

        public State(Time time1, int i, float f, int j, int k)
        {
            time = time1;
            julianDay = i;
            scrollDeltaY = f;
            monthViewTopOffset = j;
            monthLabelTextBottomOffset = k;
        }
    }

}
