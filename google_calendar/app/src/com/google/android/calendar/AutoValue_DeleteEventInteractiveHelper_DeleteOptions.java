// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.api.event.EventDescriptor;

final class AutoValue_DeleteEventInteractiveHelper_DeleteOptions extends DeleteEventInteractiveHelper.DeleteOptions
{

    private final EventDescriptor descriptor;
    private final com.google.android.calendar.api.event.GooglePrivateData.GuestNotification guestNotification;
    private final int scope;

    AutoValue_DeleteEventInteractiveHelper_DeleteOptions(EventDescriptor eventdescriptor, int i, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification guestnotification)
    {
        if (eventdescriptor == null)
        {
            throw new NullPointerException("Null descriptor");
        }
        descriptor = eventdescriptor;
        scope = i;
        if (guestnotification == null)
        {
            throw new NullPointerException("Null guestNotification");
        } else
        {
            guestNotification = guestnotification;
            return;
        }
    }

    public final EventDescriptor descriptor()
    {
        return descriptor;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof DeleteEventInteractiveHelper.DeleteOptions)
            {
                if (!descriptor.equals(((DeleteEventInteractiveHelper.DeleteOptions) (obj = (DeleteEventInteractiveHelper.DeleteOptions)obj)).descriptor()) || scope != ((DeleteEventInteractiveHelper.DeleteOptions) (obj)).scope() || !guestNotification.equals(((DeleteEventInteractiveHelper.DeleteOptions) (obj)).guestNotification()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final com.google.android.calendar.api.event.GooglePrivateData.GuestNotification guestNotification()
    {
        return guestNotification;
    }

    public final int hashCode()
    {
        return ((descriptor.hashCode() ^ 0xf4243) * 0xf4243 ^ scope) * 0xf4243 ^ guestNotification.hashCode();
    }

    public final int scope()
    {
        return scope;
    }

    public final String toString()
    {
        String s = String.valueOf(descriptor);
        int i = scope;
        String s1 = String.valueOf(guestNotification);
        return (new StringBuilder(String.valueOf(s).length() + 65 + String.valueOf(s1).length())).append("DeleteOptions{descriptor=").append(s).append(", scope=").append(i).append(", guestNotification=").append(s1).append("}").toString();
    }
}
