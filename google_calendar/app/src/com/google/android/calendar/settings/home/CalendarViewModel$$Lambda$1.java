// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import com.google.android.calendar.api.event.notification.Notification;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.settings.home:
//            CalendarViewModel

final class 
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        return CalendarViewModel.bridge$lambda$0$CalendarViewModel((Notification)obj, (Notification)obj1);
    }


    private ()
    {
    }
}
