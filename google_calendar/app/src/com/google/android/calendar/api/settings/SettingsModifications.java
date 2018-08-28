// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import com.google.android.calendar.api.event.notification.Notification;

// Referenced classes of package com.google.android.calendar.api.settings:
//            Settings

public interface SettingsModifications
    extends Settings
{

    public abstract void addPreferredNotification(int i, Notification notification);

    public abstract boolean arePreferredNotificationsModified(int i);

    public abstract Settings getOriginal();

    public abstract boolean isModified();
}
