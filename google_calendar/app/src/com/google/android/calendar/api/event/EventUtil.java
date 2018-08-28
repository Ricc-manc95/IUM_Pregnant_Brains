// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Present;
import com.google.common.collect.FluentIterable;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.api.event:
//            Event

public final class EventUtil
{

    static int checkAvailability(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid availability value");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            return i;
        }
    }

    static int checkVisibility(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException("Invalid visibility value");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            return i;
        }
    }

    public static String getFirstLocationName(Event event)
    {
        Object obj;
        event = event.getLocation().getEventLocations();
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj2)
            {
                return ((EventLocation)obj2).name;
            }


            private .Lambda._cls0()
            {
            }
        }

        if (event instanceof FluentIterable)
        {
            event = (FluentIterable)event;
        } else
        {
            event = new com.google.common.collect.FluentIterable._cls1(event, event);
        }
        obj = .Lambda._cls0..instance;
        event = (Iterable)((FluentIterable) (event)).iterableDelegate.or(event);
        if (event == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
        event = new com.google.common.collect.Iterables._cls5(event, ((Function) (obj)));
        class .Lambda._cls1
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls1();

            public final boolean apply(Object obj2)
            {
                return !TextUtils.isEmpty((String)obj2);
            }


            private .Lambda._cls1()
            {
            }
        }

        if (event instanceof FluentIterable)
        {
            event = (FluentIterable)event;
        } else
        {
            event = new com.google.common.collect.FluentIterable._cls1(event, event);
        }
        obj = .Lambda._cls1..instance;
        event = ((Iterable)((FluentIterable) (event)).iterableDelegate.or(event)).iterator();
        if (event == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
_L4:
        if (!event.hasNext()) goto _L2; else goto _L1
_L1:
        Object obj1 = event.next();
        if (!((Predicate) (obj)).apply(obj1)) goto _L4; else goto _L3
_L3:
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        event = new Present(obj1);
_L6:
        return (String)event.orNull();
_L2:
        event = Absent.INSTANCE;
        if (true) goto _L6; else goto _L5
_L5:
    }
}
