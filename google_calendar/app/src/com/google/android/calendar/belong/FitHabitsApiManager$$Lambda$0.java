// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.groove.GrooveUtils;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.belong:
//            FitHabitsApiManager

final class arg._cls2
    implements Function
{

    private final FitHabitsApiManager arg$1;
    private final long arg$2;

    public final Object apply(Object obj)
    {
        FitHabitsApiManager fithabitsapimanager = arg$1;
        long l = arg$2;
        obj = (Habit)obj;
        long al[] = GrooveUtils.getIntervalStartAndEnd(fithabitsapimanager.context, ((Habit) (obj)).getContract().getInterval(), l);
        return GrooveUtils.getInstanceInfoForParent(fithabitsapimanager.context, fithabitsapimanager.account, ((Habit) (obj)).getDescriptor().habitId, al[0], al[1]);
    }

    (FitHabitsApiManager fithabitsapimanager, long l)
    {
        arg$1 = fithabitsapimanager;
        arg$2 = l;
    }
}
