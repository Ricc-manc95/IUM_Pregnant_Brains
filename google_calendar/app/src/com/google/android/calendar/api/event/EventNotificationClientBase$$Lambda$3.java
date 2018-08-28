// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.android.calendarcommon2.LogUtils;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventNotificationClientBase

final class Q
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        obj = (Throwable)obj;
        LogUtils.e(EventNotificationClientBase.TAG, ((Throwable) (obj)), "Failed obtaining notifications for habits.", new Object[0]);
        return ImmutableList.of();
    }


    private Q()
    {
    }
}
