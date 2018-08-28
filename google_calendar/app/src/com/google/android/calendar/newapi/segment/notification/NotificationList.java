// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.notification;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

final class NotificationList
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public boolean allDayEvent;
    public ArrayList allDayNotifications;
    public ImmutableList defaultAllDayNotifications;
    public ImmutableList defaultTimedNotifications;
    public ArrayList timedNotifications;

    NotificationList()
    {
    }

    NotificationList(Parcel parcel)
    {
        timedNotifications = parcel.createTypedArrayList(Notification.CREATOR);
        allDayNotifications = parcel.createTypedArrayList(Notification.CREATOR);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(timedNotifications);
        parcel.writeTypedList(allDayNotifications);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new NotificationList(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new NotificationList[i];
        }

        _cls1()
        {
        }
    }

}
