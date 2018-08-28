// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.calendar.v2a.shared.storage.proto.CalendarKey;
import com.google.calendar.v2a.shared.storage.proto.EventKey;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.api.event:
//            V2AEventKey

final class A
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        obj = ((V2AEventKey)obj).getV2Key();
        if (((EventKey) (obj)).calendarKey_ == null)
        {
            obj = CalendarKey.DEFAULT_INSTANCE;
        } else
        {
            obj = ((EventKey) (obj)).calendarKey_;
        }
        return ((CalendarKey) (obj)).calendarId_;
    }


    private A()
    {
    }
}
