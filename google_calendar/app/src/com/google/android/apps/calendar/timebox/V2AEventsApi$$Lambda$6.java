// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.V2AEventKey;
import com.google.calendar.v2a.shared.storage.proto.CalendarKey;
import com.google.calendar.v2a.shared.storage.proto.EventKey;
import com.google.common.base.Function;

final class arg._cls1
    implements Function
{

    private final V2AEventKey arg$1;

    public final Object apply(Object obj)
    {
        V2AEventKey v2aeventkey = arg$1;
        CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])obj;
        int j = acalendarlistentry.length;
        for (int i = 0; i < j; i++)
        {
            CalendarListEntry calendarlistentry = acalendarlistentry[i];
            String s = calendarlistentry.getDescriptor().calendarId;
            obj = v2aeventkey.getV2Key();
            if (((EventKey) (obj)).calendarKey_ == null)
            {
                obj = CalendarKey.DEFAULT_INSTANCE;
            } else
            {
                obj = ((EventKey) (obj)).calendarKey_;
            }
            if (s.equals(((CalendarKey) (obj)).calendarId_))
            {
                return calendarlistentry;
            }
        }

        throw new RuntimeException();
    }

    (V2AEventKey v2aeventkey)
    {
        arg$1 = v2aeventkey;
    }
}
