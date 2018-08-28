// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.contracts;

import com.google.common.base.Optional;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.contracts:
//            UserNotificationCheckSchedule

final class AutoValue_UserNotificationCheckSchedule extends UserNotificationCheckSchedule
{

    private final Optional nonWakingCheckMillis;
    private final int pluginId;
    private final Optional wakingCheckMillis;

    AutoValue_UserNotificationCheckSchedule(int i, Optional optional, Optional optional1)
    {
        pluginId = i;
        if (optional == null)
        {
            throw new NullPointerException("Null wakingCheckMillis");
        }
        wakingCheckMillis = optional;
        if (optional1 == null)
        {
            throw new NullPointerException("Null nonWakingCheckMillis");
        } else
        {
            nonWakingCheckMillis = optional1;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof UserNotificationCheckSchedule)
            {
                if (pluginId != ((UserNotificationCheckSchedule) (obj = (UserNotificationCheckSchedule)obj)).getPluginId() || !wakingCheckMillis.equals(((UserNotificationCheckSchedule) (obj)).getWakingCheckMillis()) || !nonWakingCheckMillis.equals(((UserNotificationCheckSchedule) (obj)).getNonWakingCheckMillis()))
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

    public final Optional getNonWakingCheckMillis()
    {
        return nonWakingCheckMillis;
    }

    public final int getPluginId()
    {
        return pluginId;
    }

    public final Optional getWakingCheckMillis()
    {
        return wakingCheckMillis;
    }

    public final int hashCode()
    {
        return ((pluginId ^ 0xf4243) * 0xf4243 ^ wakingCheckMillis.hashCode()) * 0xf4243 ^ nonWakingCheckMillis.hashCode();
    }

    public final String toString()
    {
        int i = pluginId;
        String s = String.valueOf(wakingCheckMillis);
        String s1 = String.valueOf(nonWakingCheckMillis);
        return (new StringBuilder(String.valueOf(s).length() + 94 + String.valueOf(s1).length())).append("UserNotificationCheckSchedule{pluginId=").append(i).append(", wakingCheckMillis=").append(s).append(", nonWakingCheckMillis=").append(s1).append("}").toString();
    }
}
