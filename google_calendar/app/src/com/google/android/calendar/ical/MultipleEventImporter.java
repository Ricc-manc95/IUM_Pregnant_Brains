// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.calendar.api.event.EventClient;

final class MultipleEventImporter
{

    public final EventClient eventClient;

    public MultipleEventImporter(EventClient eventclient)
    {
        if (eventclient == null)
        {
            throw new NullPointerException();
        } else
        {
            eventClient = (EventClient)eventclient;
            return;
        }
    }
}
