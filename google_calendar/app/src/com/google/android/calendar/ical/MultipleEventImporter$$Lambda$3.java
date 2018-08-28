// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.ical:
//            MultipleEventImporter

final class arg._cls1
    implements Function
{

    private final MultipleEventImporter arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (EventModifications)obj;
        obj1 = ((MultipleEventImporter) (obj1)).eventClient;
        byte byte0;
        if (((EventModifications) (obj)).getDescriptor().isRecurringPhantom())
        {
            byte0 = 2;
        } else
        {
            byte0 = 0;
        }
        return ((EventClient) (obj1)).icsUpdate(((EventModifications) (obj)), byte0);
    }

    (MultipleEventImporter multipleeventimporter)
    {
        arg$1 = multipleeventimporter;
    }
}
