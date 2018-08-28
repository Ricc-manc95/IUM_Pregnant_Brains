// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.notification;


// Referenced classes of package com.google.android.calendar.api.event.notification:
//            NotificationPermissions

public class ReadOnlyNotificationPermissionsImpl
    implements NotificationPermissions
{

    public ReadOnlyNotificationPermissionsImpl()
    {
    }

    public boolean isReadOnly()
    {
        return true;
    }
}
