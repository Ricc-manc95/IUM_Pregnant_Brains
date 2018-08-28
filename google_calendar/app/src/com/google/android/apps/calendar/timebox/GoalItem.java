// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.habit.HabitDescriptor;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            EventItem

public interface GoalItem
    extends EventItem
{

    public abstract Goal getGoal();

    public abstract HabitDescriptor getHabitDescriptor();

    public abstract GoalItem setGoalDone(boolean flag);
}
