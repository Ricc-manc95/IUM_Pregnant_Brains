// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.event.EventDescriptor;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            GoalImpl, AutoValue_GoalImpl

public final class goal extends goal
{

    private goal event;
    private EventDescriptor eventDescriptor;
    private goal goal;

    public final GoalImpl build()
    {
        String s2 = "";
        if (eventDescriptor == null)
        {
            s2 = String.valueOf("").concat(" eventDescriptor");
        }
        String s = s2;
        if (event == null)
        {
            s = String.valueOf(s2).concat(" event");
        }
        s2 = s;
        if (goal == null)
        {
            s2 = String.valueOf(s).concat(" goal");
        }
        if (!s2.isEmpty())
        {
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                s1 = "Missing required properties:".concat(s1);
            } else
            {
                s1 = new String("Missing required properties:");
            }
            throw new IllegalStateException(s1);
        } else
        {
            return new AutoValue_GoalImpl(eventDescriptor, event, goal);
        }
    }

    public final goal setEvent(goal goal1)
    {
        if (goal1 == null)
        {
            throw new NullPointerException("Null event");
        } else
        {
            event = goal1;
            return this;
        }
    }

    public final event setEventDescriptor(EventDescriptor eventdescriptor)
    {
        if (eventdescriptor == null)
        {
            throw new NullPointerException("Null eventDescriptor");
        } else
        {
            eventDescriptor = eventdescriptor;
            return this;
        }
    }

    public final eventDescriptor setGoal(eventDescriptor eventdescriptor)
    {
        if (eventdescriptor == null)
        {
            throw new NullPointerException("Null goal");
        } else
        {
            goal = eventdescriptor;
            return this;
        }
    }

    public ()
    {
    }

    (GoalImpl goalimpl)
    {
        eventDescriptor = goalimpl.getEventDescriptor();
        event = goalimpl.getEvent();
        goal = goalimpl.getGoal();
    }
}
