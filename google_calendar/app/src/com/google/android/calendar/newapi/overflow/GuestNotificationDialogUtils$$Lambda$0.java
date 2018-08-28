// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.common.base.Predicate;
import java.util.List;

public final class arg._cls2
    implements Predicate
{

    private final EventModifications arg$1;
    private final List arg$2;

    public final boolean apply(Object obj)
    {
        Object obj1 = arg$1;
        List list = arg$2;
        obj = (Attendee)obj;
        obj1 = ((EventModifications) (obj1)).getOrganizer();
        com.google.android.calendar.api.event.attendee.AttendeeDescriptor attendeedescriptor = ((Attendee) (obj)).attendeeDescriptor;
        boolean flag;
        if (obj1 == attendeedescriptor || obj1 != null && obj1.equals(attendeedescriptor))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && list.contains(obj);
    }

    public (EventModifications eventmodifications, List list)
    {
        arg$1 = eventmodifications;
        arg$2 = list;
    }
}
