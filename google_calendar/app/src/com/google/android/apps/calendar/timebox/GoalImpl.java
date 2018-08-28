// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import android.accounts.Account;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.habit.HabitDescriptor;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            EventItem, GoalItem, Calendar

public abstract class GoalImpl
    implements EventItem, GoalItem
{

    public GoalImpl()
    {
    }

    public HabitDescriptor getHabitDescriptor()
    {
        Calendar calendar = getEvent().getCalendar();
        return new HabitDescriptor(CalendarDescriptor.createWithoutLocalId(new Account(calendar.getAccountName(), calendar.getAccountType()), calendar.getOwnerAccount()), getGoal().getHabitId());
    }

    public final Item.SortType getSortType()
    {
        return Item.SortType.EVENT;
    }

    public final GoalItem setGoalDone(boolean flag)
    {
        return toBuilder().setGoal(getGoal().toBuilder().setDone(true).build()).build();
    }

    public abstract Builder toBuilder();

    private class Builder
    {

        public abstract GoalImpl build();

        public abstract Builder setEvent(EventItem.Event event);

        public abstract Builder setEventDescriptor(EventDescriptor eventdescriptor);

        public abstract Builder setGoal(GoalItem.Goal goal);

        public Builder()
        {
        }
    }

}
