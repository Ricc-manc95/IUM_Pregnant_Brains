// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.common.base.Function;
import net.fortuna.ical4j.model.property.DtStamp;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventReader

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return ICalEventReader.lambda$createICalEventOperations$5$ICalEventReader((DtStamp)obj);
    }


    private ()
    {
    }
}