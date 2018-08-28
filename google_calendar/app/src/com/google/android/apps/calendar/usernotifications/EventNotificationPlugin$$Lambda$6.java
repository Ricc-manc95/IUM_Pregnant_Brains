// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotifications;

import com.google.common.base.Function;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.usernotifications:
//            EventNotificationPlugin

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return EventNotificationPlugin.lambda$getRelevantNotifications$0$EventNotificationPlugin((List)obj);
    }


    private ()
    {
    }
}
