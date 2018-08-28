// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.android.calendar.api.event.EventModifications;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalDeleteFragment

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return ICalDeleteFragment.lambda$onDeleteEventClicked$2$ICalDeleteFragment((EventModifications)obj);
    }


    private ()
    {
    }
}
