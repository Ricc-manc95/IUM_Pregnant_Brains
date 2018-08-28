// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;


final class AutoValue_MonthLabelThresholdEvaluator_State extends MonthLabelThresholdEvaluator.State
{

    private final int currentOffsetPx;
    private final int currentScrollDeltaYPx;
    private final boolean firstDayOfMonth;
    private final int switchPointOffsetPx;

    AutoValue_MonthLabelThresholdEvaluator_State(boolean flag, int i, int j, int k)
    {
        firstDayOfMonth = flag;
        currentOffsetPx = i;
        currentScrollDeltaYPx = j;
        switchPointOffsetPx = k;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof MonthLabelThresholdEvaluator.State)
            {
                if (firstDayOfMonth != ((MonthLabelThresholdEvaluator.State) (obj = (MonthLabelThresholdEvaluator.State)obj)).isFirstDayOfMonth() || currentOffsetPx != ((MonthLabelThresholdEvaluator.State) (obj)).getCurrentOffsetPx() || currentScrollDeltaYPx != ((MonthLabelThresholdEvaluator.State) (obj)).getCurrentScrollDeltaYPx() || switchPointOffsetPx != ((MonthLabelThresholdEvaluator.State) (obj)).getSwitchPointOffsetPx())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    final int getCurrentOffsetPx()
    {
        return currentOffsetPx;
    }

    final int getCurrentScrollDeltaYPx()
    {
        return currentScrollDeltaYPx;
    }

    final int getSwitchPointOffsetPx()
    {
        return switchPointOffsetPx;
    }

    public final int hashCode()
    {
        char c;
        if (firstDayOfMonth)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return (((c ^ 0xf4243) * 0xf4243 ^ currentOffsetPx) * 0xf4243 ^ currentScrollDeltaYPx) * 0xf4243 ^ switchPointOffsetPx;
    }

    final boolean isFirstDayOfMonth()
    {
        return firstDayOfMonth;
    }

    public final String toString()
    {
        boolean flag = firstDayOfMonth;
        int i = currentOffsetPx;
        int j = currentScrollDeltaYPx;
        int k = switchPointOffsetPx;
        return (new StringBuilder(125)).append("State{firstDayOfMonth=").append(flag).append(", currentOffsetPx=").append(i).append(", currentScrollDeltaYPx=").append(j).append(", switchPointOffsetPx=").append(k).append("}").toString();
    }
}
