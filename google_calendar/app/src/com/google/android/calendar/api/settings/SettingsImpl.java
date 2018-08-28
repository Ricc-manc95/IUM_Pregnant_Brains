// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.calendar.api.event.notification.Notification;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.settings:
//            Settings

public class SettingsImpl
    implements Settings
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final Account account;
    private final List preferredAllDayNotifications;
    private final List preferredTimedNotifications;

    SettingsImpl(Account account1, Notification anotification[], Notification anotification1[])
    {
        if (account1 == null)
        {
            throw new NullPointerException();
        }
        account = (Account)account1;
        if (anotification == null)
        {
            throw new NullPointerException();
        }
        if (((Notification[])anotification).length == 0)
        {
            account1 = Collections.emptyList();
        } else
        {
            account1 = Collections.unmodifiableList(Arrays.asList(anotification));
        }
        preferredTimedNotifications = account1;
        if (anotification1 == null)
        {
            throw new NullPointerException();
        }
        if (((Notification[])anotification1).length == 0)
        {
            account1 = Collections.emptyList();
        } else
        {
            account1 = Collections.unmodifiableList(Arrays.asList(anotification1));
        }
        preferredAllDayNotifications = account1;
    }

    SettingsImpl(Parcel parcel)
    {
        this((Account)parcel.readParcelable(android/accounts/Account.getClassLoader()), (Notification[])parcel.createTypedArray(Notification.CREATOR), (Notification[])parcel.createTypedArray(Notification.CREATOR));
    }

    public int describeContents()
    {
        return 0;
    }

    public final Account getDescriptor()
    {
        return account;
    }

    public final List getPreferredNotifications(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Illegal notification category");

        case 1: // '\001'
            return preferredTimedNotifications;

        case 2: // '\002'
            return preferredAllDayNotifications;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(account, i);
        parcel.writeTypedArray((Notification[])preferredTimedNotifications.toArray(new Notification[preferredTimedNotifications.size()]), i);
        parcel.writeTypedArray((Notification[])preferredAllDayNotifications.toArray(new Notification[preferredAllDayNotifications.size()]), i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SettingsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SettingsImpl[i];
        }

        _cls1()
        {
        }
    }

}
