// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.room;

import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.newapi.segment.room:
//            RoomUtils

final class I
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return RoomUtils.lambda$createRoomsLink$2$RoomUtils((Attendee)obj);
    }


    private I()
    {
    }
}