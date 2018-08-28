// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event:
//            AutoValue_GooglePrivateData

public abstract class GooglePrivateData
    implements Parcelable
{
    public static final class GuestNotification extends Enum
    {

        private static final GuestNotification $VALUES[];
        public static final GuestNotification DISABLED;
        public static final GuestNotification ENABLED;
        public static final GuestNotification UNDECIDED;

        public static GuestNotification getGuestNotificationFromInteger(int i)
        {
            if (i < 0 || i >= values().length)
            {
                return UNDECIDED;
            } else
            {
                return values()[i];
            }
        }

        public static GuestNotification valueOf(String s)
        {
            return (GuestNotification)Enum.valueOf(com/google/android/calendar/api/event/GooglePrivateData$GuestNotification, s);
        }

        public static GuestNotification[] values()
        {
            return (GuestNotification[])$VALUES.clone();
        }

        static 
        {
            UNDECIDED = new GuestNotification("UNDECIDED", 0);
            DISABLED = new GuestNotification("DISABLED", 1);
            ENABLED = new GuestNotification("ENABLED", 2);
            $VALUES = (new GuestNotification[] {
                UNDECIDED, DISABLED, ENABLED
            });
        }

        private GuestNotification(String s, int i)
        {
            super(s, i);
        }
    }


    public static final GooglePrivateData DEFAULT;

    public GooglePrivateData()
    {
    }

    static GooglePrivateData create(GuestNotification guestnotification, boolean flag, boolean flag1)
    {
        return new AutoValue_GooglePrivateData(guestnotification, flag, flag1);
    }

    public abstract GuestNotification getGuestNotification();

    public abstract boolean hasEveryoneDeclined();

    public abstract boolean isEveryoneDeclinedDismissed();

    static 
    {
        DEFAULT = new AutoValue_GooglePrivateData(GuestNotification.UNDECIDED, false, false);
    }
}
