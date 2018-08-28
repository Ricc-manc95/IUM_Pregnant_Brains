// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.calendar.api.event.Event;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventReader

final class arg._cls3
    implements Function
{

    private final String arg$1;
    private final int arg$2;
    private final String arg$3;

    public final Object apply(Object obj)
    {
        return ICalEventReader.lambda$createICalEventOperations$6$ICalEventReader(arg$1, arg$2, arg$3, (Event)obj);
    }

    (String s, int i, String s1)
    {
        arg$1 = s;
        arg$2 = i;
        arg$3 = s1;
    }
}
