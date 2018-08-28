// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime;

import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.base.Predicate;
import com.google.common.base.Present;
import java.util.Iterator;

final class arg._cls1
    implements Function
{

    private final Event arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (String)obj;
        obj1 = ((Event) (obj1)).getAttendees();
        obj = new <init>(((String) (obj)));
        obj1 = ((Iterable) (obj1)).iterator();
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
        Object obj2;
        do
        {
            if (!((Iterator) (obj1)).hasNext())
            {
                break MISSING_BLOCK_LABEL_118;
            }
            obj2 = ((Iterator) (obj1)).next();
        } while (!((Predicate) (obj)).apply(obj2));
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        obj = new Present(obj2);
_L1:
        obj = (Attendee)((Optional) (obj)).orNull();
        if (obj == null)
        {
            return null;
        } else
        {
            return Platform.emptyToNull(((Attendee) (obj)).displayName);
        }
        obj = Absent.INSTANCE;
          goto _L1
    }

    (Event event)
    {
        arg$1 = event;
    }
}
