// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.event.EventDescriptor;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            GoalImpl

class $AutoValue_GoalImpl extends GoalImpl
{

    private final EventItem.Event event;
    private final EventDescriptor eventDescriptor;
    private final GoalItem.Goal goal;

    $AutoValue_GoalImpl(EventDescriptor eventdescriptor, EventItem.Event event1, GoalItem.Goal goal1)
    {
        if (eventdescriptor == null)
        {
            throw new NullPointerException("Null eventDescriptor");
        }
        eventDescriptor = eventdescriptor;
        if (event1 == null)
        {
            throw new NullPointerException("Null event");
        }
        event = event1;
        if (goal1 == null)
        {
            throw new NullPointerException("Null goal");
        } else
        {
            goal = goal1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof GoalImpl)
            {
                if (!eventDescriptor.equals(((GoalImpl) (obj = (GoalImpl)obj)).getEventDescriptor()) || !event.equals(((GoalImpl) (obj)).getEvent()) || !goal.equals(((GoalImpl) (obj)).getGoal()))
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

    public final EventItem.Event getEvent()
    {
        return event;
    }

    public final EventDescriptor getEventDescriptor()
    {
        return eventDescriptor;
    }

    public final GoalItem.Goal getGoal()
    {
        return goal;
    }

    public int hashCode()
    {
        return ((eventDescriptor.hashCode() ^ 0xf4243) * 0xf4243 ^ event.hashCode()) * 0xf4243 ^ goal.hashCode();
    }

    public final GoalImpl.Builder toBuilder()
    {
        class Builder extends GoalImpl.Builder
        {

            private EventItem.Event event;
            private EventDescriptor eventDescriptor;
            private GoalItem.Goal goal;

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

            public final GoalImpl.Builder setEvent(EventItem.Event event1)
            {
                if (event1 == null)
                {
                    throw new NullPointerException("Null event");
                } else
                {
                    event = event1;
                    return this;
                }
            }

            public final GoalImpl.Builder setEventDescriptor(EventDescriptor eventdescriptor)
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

            public final GoalImpl.Builder setGoal(GoalItem.Goal goal1)
            {
                if (goal1 == null)
                {
                    throw new NullPointerException("Null goal");
                } else
                {
                    goal = goal1;
                    return this;
                }
            }

            public Builder()
            {
            }

            Builder(GoalImpl goalimpl)
            {
                eventDescriptor = goalimpl.getEventDescriptor();
                event = goalimpl.getEvent();
                goal = goalimpl.getGoal();
            }
        }

        return new Builder(this);
    }

    public String toString()
    {
        String s = String.valueOf(eventDescriptor);
        String s1 = String.valueOf(event);
        String s2 = String.valueOf(goal);
        return (new StringBuilder(String.valueOf(s).length() + 41 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("GoalImpl{eventDescriptor=").append(s).append(", event=").append(s1).append(", goal=").append(s2).append("}").toString();
    }
}
