// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;


// Referenced classes of package com.google.android.calendar.api.event:
//            GooglePrivateData

abstract class $AutoValue_GooglePrivateData extends GooglePrivateData
{

    private final GooglePrivateData.GuestNotification getGuestNotification;
    private final boolean hasEveryoneDeclined;
    private final boolean isEveryoneDeclinedDismissed;

    $AutoValue_GooglePrivateData(GooglePrivateData.GuestNotification guestnotification, boolean flag, boolean flag1)
    {
        if (guestnotification == null)
        {
            throw new NullPointerException("Null getGuestNotification");
        } else
        {
            getGuestNotification = guestnotification;
            hasEveryoneDeclined = flag;
            isEveryoneDeclinedDismissed = flag1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof GooglePrivateData)
            {
                if (!getGuestNotification.equals(((GooglePrivateData) (obj = (GooglePrivateData)obj)).getGuestNotification()) || hasEveryoneDeclined != ((GooglePrivateData) (obj)).hasEveryoneDeclined() || isEveryoneDeclinedDismissed != ((GooglePrivateData) (obj)).isEveryoneDeclinedDismissed())
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

    public final GooglePrivateData.GuestNotification getGuestNotification()
    {
        return getGuestNotification;
    }

    public final boolean hasEveryoneDeclined()
    {
        return hasEveryoneDeclined;
    }

    public int hashCode()
    {
        char c1 = '\u04CF';
        int i = getGuestNotification.hashCode();
        char c;
        if (hasEveryoneDeclined)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (!isEveryoneDeclinedDismissed)
        {
            c1 = '\u04D5';
        }
        return (c ^ (i ^ 0xf4243) * 0xf4243) * 0xf4243 ^ c1;
    }

    public final boolean isEveryoneDeclinedDismissed()
    {
        return isEveryoneDeclinedDismissed;
    }

    public String toString()
    {
        String s = String.valueOf(getGuestNotification);
        boolean flag = hasEveryoneDeclined;
        boolean flag1 = isEveryoneDeclinedDismissed;
        return (new StringBuilder(String.valueOf(s).length() + 102)).append("GooglePrivateData{getGuestNotification=").append(s).append(", hasEveryoneDeclined=").append(flag).append(", isEveryoneDeclinedDismissed=").append(flag1).append("}").toString();
    }
}
