// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.habit.HabitDescriptor;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            $AutoValue_GoalImpl

final class AutoValue_GoalImpl extends $AutoValue_GoalImpl
{

    private volatile HabitDescriptor getHabitDescriptor;

    AutoValue_GoalImpl(EventDescriptor eventdescriptor, EventItem.Event event, GoalItem.Goal goal)
    {
        super(eventdescriptor, event, goal);
    }

    public final HabitDescriptor getHabitDescriptor()
    {
        if (getHabitDescriptor != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (getHabitDescriptor == null)
        {
            getHabitDescriptor = super.getHabitDescriptor();
            if (getHabitDescriptor == null)
            {
                throw new NullPointerException("getHabitDescriptor() cannot return null");
            }
        }
        break MISSING_BLOCK_LABEL_46;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        this;
        JVM INSTR monitorexit ;
_L2:
        return getHabitDescriptor;
    }
}
