// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;


final class MonthLabelThresholdEvaluator
{

    MonthLabelThresholdEvaluator()
    {
    }

    final com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarAnimation getAnimation(State state)
    {
        boolean flag1 = true;
        boolean flag;
        if (state.getCurrentOffsetPx() <= state.getSwitchPointOffsetPx() && state.getCurrentOffsetPx() + state.getCurrentScrollDeltaYPx() > state.getSwitchPointOffsetPx() || state.getCurrentOffsetPx() >= state.getSwitchPointOffsetPx() && state.getCurrentOffsetPx() + state.getCurrentScrollDeltaYPx() < state.getSwitchPointOffsetPx())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (state.isFirstDayOfMonth() && Math.abs(state.getCurrentScrollDeltaYPx()) <= 90)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (state.getCurrentScrollDeltaYPx() > 0)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarAnimation.FORWARD;
                } else
                {
                    return com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarAnimation.BACKWARD;
                }
            }
        }
        return com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarAnimation.NONE;
    }

    private class State
    {

        abstract int getCurrentOffsetPx();

        abstract int getCurrentScrollDeltaYPx();

        abstract int getSwitchPointOffsetPx();

        abstract boolean isFirstDayOfMonth();

        State()
        {
        }
    }

}
