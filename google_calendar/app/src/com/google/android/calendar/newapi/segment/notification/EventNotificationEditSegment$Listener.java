// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.notification;

import com.google.android.calendar.api.event.notification.Notification;

// Referenced classes of package com.google.android.calendar.newapi.segment.notification:
//            EventNotificationEditSegment

public static interface 
{

    public abstract void onAddNotificationClicked();

    public abstract void onNotificationClicked(Notification notification, int i);

    public abstract void onRemoveButtonClicked(Notification notification);
}
