// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.calendar.api.event.EventClient;
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
        MultipleEventImporter multipleeventimporter = arg$1;
        obj = (EventModifications)obj;
        return multipleeventimporter.eventClient.delete(((EventModifications) (obj)).getDescriptor(), 0, com.google.android.calendar.api.event.ion.UNDECIDED);
    }

    fication(MultipleEventImporter multipleeventimporter)
    {
        arg$1 = multipleeventimporter;
    }
}
