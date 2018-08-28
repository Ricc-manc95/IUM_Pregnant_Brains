// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.notification;

import android.database.Cursor;

// Referenced classes of package com.google.android.calendar.api.event.notification:
//            NotificationsStoreUtils

final class 
    implements com.google.android.apps.calendar.util.database.a._cls0
{

    public static final com.google.android.apps.calendar.util.database..loadEventNotifications._cls0.NotificationsStoreUtils $instance = new <init>();

    public final Object extract(Cursor cursor)
    {
        return NotificationsStoreUtils.lambda$loadEventNotifications$0$NotificationsStoreUtils(cursor);
    }


    private ()
    {
    }
}
