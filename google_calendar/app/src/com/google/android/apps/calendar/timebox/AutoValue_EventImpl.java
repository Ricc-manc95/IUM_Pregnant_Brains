// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.event.EventDescriptor;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            EventImpl

final class AutoValue_EventImpl extends EventImpl
{

    private final EventItem.Event event;
    private final EventDescriptor eventDescriptor;
    private final Item.SortType sortType;

    AutoValue_EventImpl(Item.SortType sorttype, EventDescriptor eventdescriptor, EventItem.Event event1)
    {
        sortType = sorttype;
        eventDescriptor = eventdescriptor;
        event = event1;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof EventImpl)
            {
                if (!sortType.equals(((EventImpl) (obj = (EventImpl)obj)).getSortType()) || !eventDescriptor.equals(((EventImpl) (obj)).getEventDescriptor()) || !event.equals(((EventImpl) (obj)).getEvent()))
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

    public final Item.SortType getSortType()
    {
        return sortType;
    }

    public final int hashCode()
    {
        return ((sortType.hashCode() ^ 0xf4243) * 0xf4243 ^ eventDescriptor.hashCode()) * 0xf4243 ^ event.hashCode();
    }

    public final String toString()
    {
        String s = String.valueOf(sortType);
        String s1 = String.valueOf(eventDescriptor);
        String s2 = String.valueOf(event);
        return (new StringBuilder(String.valueOf(s).length() + 46 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("EventImpl{sortType=").append(s).append(", eventDescriptor=").append(s1).append(", event=").append(s2).append("}").toString();
    }
}
