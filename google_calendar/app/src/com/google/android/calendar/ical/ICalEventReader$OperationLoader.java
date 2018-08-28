// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventReader

static interface 
{

    public abstract ListenableFuture loadEvents(CalendarListEntry calendarlistentry);
}
