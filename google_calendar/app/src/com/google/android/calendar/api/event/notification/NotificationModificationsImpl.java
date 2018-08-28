// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.notification;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.notification:
//            NotificationModifications, Notification

public class NotificationModificationsImpl
    implements Parcelable, NotificationModifications
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private ArrayList notifications;
    private final List original;

    NotificationModificationsImpl(Parcel parcel)
    {
        notifications = null;
        original = parcel.createTypedArrayList(Notification.CREATOR);
        notifications = parcel.createTypedArrayList(Notification.CREATOR);
    }

    public NotificationModificationsImpl(List list)
    {
        notifications = null;
        original = list;
        if (original != null)
        {
            notifications = new ArrayList(list);
        }
    }

    public final void addNotification(Notification notification)
    {
        if (notification == null)
        {
            throw new NullPointerException();
        }
        if (notifications == null)
        {
            notifications = new ArrayList();
        }
        notifications.remove(notification);
        notifications.add(0, notification);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof NotificationModificationsImpl)
        {
            obj = (NotificationModificationsImpl)obj;
            List list = original;
            List list1 = ((NotificationModificationsImpl) (obj)).original;
            boolean flag;
            if (list == list1 || list != null && list.equals(list1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                ArrayList arraylist = notifications;
                obj = ((NotificationModificationsImpl) (obj)).notifications;
                if (arraylist == obj || arraylist != null && arraylist.equals(obj))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public final List getNotifications()
    {
        if (isModified())
        {
            if (notifications != null)
            {
                return Collections.unmodifiableList(notifications);
            } else
            {
                return null;
            }
        } else
        {
            return original;
        }
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            original, notifications
        });
    }

    public final boolean isModified()
    {
        if (original != null || notifications != null)
        {
            if (original == null || notifications == null)
            {
                return true;
            }
            if (notifications.size() != original.size() || !notifications.equals(original))
            {
                return true;
            }
        }
        return false;
    }

    public final void setNotifications(List list)
    {
        if (list == null)
        {
            throw new NullPointerException();
        } else
        {
            notifications = new ArrayList(list);
            return;
        }
    }

    public final void useCalendarDefaults()
    {
        notifications = null;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(original);
        parcel.writeTypedList(notifications);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new NotificationModificationsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new NotificationModificationsImpl[i];
        }

        _cls1()
        {
        }
    }

}
