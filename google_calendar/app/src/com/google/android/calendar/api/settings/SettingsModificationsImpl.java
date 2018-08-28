// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.api.event.notification.NotificationModificationsImpl;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsModifications, Settings

public class SettingsModificationsImpl
    implements SettingsModifications
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final Settings original;
    private final NotificationModificationsImpl preferredAllDayNotifications;
    private final NotificationModificationsImpl preferredTimedNotifications;

    SettingsModificationsImpl(Parcel parcel)
    {
        original = (Settings)parcel.readParcelable(com/google/android/calendar/api/settings/Settings.getClassLoader());
        preferredTimedNotifications = (NotificationModificationsImpl)parcel.readParcelable(com/google/android/calendar/api/event/notification/NotificationModificationsImpl.getClassLoader());
        preferredAllDayNotifications = (NotificationModificationsImpl)parcel.readParcelable(com/google/android/calendar/api/event/notification/NotificationModificationsImpl.getClassLoader());
    }

    SettingsModificationsImpl(Settings settings)
    {
        if (settings == null)
        {
            throw new NullPointerException();
        } else
        {
            original = settings;
            preferredTimedNotifications = new NotificationModificationsImpl(settings.getPreferredNotifications(1));
            preferredAllDayNotifications = new NotificationModificationsImpl(settings.getPreferredNotifications(2));
            return;
        }
    }

    public final void addPreferredNotification(int i, Notification notification)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 34
    //                   2 45;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalArgumentException("Illegal notification category");
_L2:
        NotificationModificationsImpl notificationmodificationsimpl = preferredTimedNotifications;
_L5:
        notificationmodificationsimpl.addNotification(notification);
        return;
_L3:
        notificationmodificationsimpl = preferredAllDayNotifications;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final boolean arePreferredNotificationsModified(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Illegal notification category");

        case 1: // '\001'
            return preferredTimedNotifications.isModified();

        case 2: // '\002'
            return preferredAllDayNotifications.isModified();
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public final Account getDescriptor()
    {
        return original.getDescriptor();
    }

    public Settings getOriginal()
    {
        return original;
    }

    public final List getPreferredNotifications(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Illegal notification category");

        case 1: // '\001'
            return preferredTimedNotifications.getNotifications();

        case 2: // '\002'
            return preferredAllDayNotifications.getNotifications();
        }
    }

    public boolean isModified()
    {
        return preferredTimedNotifications.isModified() || preferredAllDayNotifications.isModified();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(original, i);
        parcel.writeParcelable(preferredTimedNotifications, i);
        parcel.writeParcelable(preferredAllDayNotifications, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SettingsModificationsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SettingsModificationsImpl[i];
        }

        _cls1()
        {
        }
    }

}
