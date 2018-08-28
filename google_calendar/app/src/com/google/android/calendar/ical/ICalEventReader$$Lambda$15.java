// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.EventModifications;

final class arg._cls1
    implements Consumer
{

    private final EventModifications arg$1;

    public final void accept(Object obj)
    {
        arg$1.setTimeZoneId((String)obj);
    }

    A(EventModifications eventmodifications)
    {
        arg$1 = eventmodifications;
    }
}
