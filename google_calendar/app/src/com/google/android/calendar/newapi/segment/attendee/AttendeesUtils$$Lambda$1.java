// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import com.google.android.calendar.api.event.attendee.Attendee;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee:
//            AttendeesUtils

final class 
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        return AttendeesUtils.lambda$static$0$AttendeesUtils((Attendee)obj, (Attendee)obj1);
    }


    private ()
    {
    }
}