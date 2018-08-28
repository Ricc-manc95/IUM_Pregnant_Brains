// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.room;

import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.newapi.segment.room:
//            RoomUtils

final class I
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        return RoomUtils.lambda$addRoomsToLocation$0$RoomUtils((Attendee)obj);
    }


    private I()
    {
    }
}
