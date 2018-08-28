// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.event.EventDescriptor;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            AutoValue_EventImpl, EventImpl

public final class  extends 
{

    private sortType event;
    private EventDescriptor eventDescriptor;
    private sortType sortType;

    public final EventImpl build()
    {
        String s2 = "";
        if (sortType == null)
        {
            s2 = String.valueOf("").concat(" sortType");
        }
        String s = s2;
        if (eventDescriptor == null)
        {
            s = String.valueOf(s2).concat(" eventDescriptor");
        }
        s2 = s;
        if (event == null)
        {
            s2 = String.valueOf(s).concat(" event");
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
            return new AutoValue_EventImpl(sortType, eventDescriptor, event);
        }
    }

    public final event setEvent(event event1)
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

    public final eventDescriptor setSortType(eventDescriptor eventdescriptor)
    {
        if (eventdescriptor == null)
        {
            throw new NullPointerException("Null sortType");
        } else
        {
            sortType = eventdescriptor;
            return this;
        }
    }

    public ()
    {
    }
}
