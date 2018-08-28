// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.ical.ICalEventOperation;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.screen.ics:
//            IcsImporter

final class arg._cls2
    implements AsyncFunction
{

    private final IcsImporter arg$1;
    private final ICalEventOperation arg$2;

    public final ListenableFuture apply(Object obj)
    {
        IcsImporter icsimporter = arg$1;
        Object obj1 = arg$2;
        obj = (CalendarListEntry)obj;
        obj1 = ((ICalEventOperation) (obj1)).events();
        obj = new <init>(icsimporter, ((CalendarListEntry) (obj)));
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.util.concurrent.uture(ImmutableList.copyOf(new com.google.common.collect.re.ListFuture(((Iterable) (obj1)), ((com.google.common.base.Function) (obj)))), true);
        }
    }

    I(IcsImporter icsimporter, ICalEventOperation icaleventoperation)
    {
        arg$1 = icsimporter;
        arg$2 = icaleventoperation;
    }
}
