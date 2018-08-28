// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import android.util.SparseArray;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.Response;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public final class attendees
{

    public final SparseArray attendees = new SparseArray();

    public (Iterable iterable, Comparator comparator)
    {
        Attendee attendee;
        for (Iterator iterator = iterable.iterator(); iterator.hasNext(); iterable.add(attendee))
        {
            attendee = (Attendee)iterator.next();
            int i = attendee.response.status.inal();
            Collection collection = (Collection)attendees.get(i, null);
            iterable = collection;
            if (collection == null)
            {
                iterable = new TreeSet(comparator);
                attendees.put(i, iterable);
            }
        }

    }
}
