// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.notification;

import android.os.Parcelable;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.notification:
//            Notification

public interface NotificationModifications
    extends Parcelable
{

    public abstract void addNotification(Notification notification);

    public abstract boolean isModified();

    public abstract void setNotifications(List list);

    public abstract void useCalendarDefaults();
}
