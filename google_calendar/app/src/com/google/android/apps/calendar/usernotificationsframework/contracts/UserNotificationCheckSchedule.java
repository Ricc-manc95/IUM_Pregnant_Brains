// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.contracts;

import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.contracts:
//            AutoValue_UserNotificationCheckSchedule

public abstract class UserNotificationCheckSchedule
{

    public static final UserNotificationCheckSchedule EMPTY = create(-1, null, null);

    public UserNotificationCheckSchedule()
    {
    }

    public static UserNotificationCheckSchedule create(int i, Long long1, Long long2)
    {
        if (long1 == null)
        {
            long1 = Absent.INSTANCE;
        } else
        {
            long1 = new Present(long1);
        }
        if (long2 == null)
        {
            long2 = Absent.INSTANCE;
        } else
        {
            long2 = new Present(long2);
        }
        return new AutoValue_UserNotificationCheckSchedule(i, long1, long2);
    }

    public abstract Optional getNonWakingCheckMillis();

    public abstract int getPluginId();

    public abstract Optional getWakingCheckMillis();

}
