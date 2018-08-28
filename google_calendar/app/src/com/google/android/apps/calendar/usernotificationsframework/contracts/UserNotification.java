// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.contracts;

import android.os.Parcelable;
import java.util.Arrays;

public abstract class UserNotification
    implements Parcelable
{

    public UserNotification()
    {
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = true;
        if (obj != null && (obj instanceof UserNotification)) goto _L2; else goto _L1
_L1:
        flag1 = false;
_L4:
        return flag1;
_L2:
        Object obj1 = (UserNotification)obj;
        if (getPluginId() != ((UserNotification) (obj1)).getPluginId() || getType() != ((UserNotification) (obj1)).getType() || getTriggerMillis() != ((UserNotification) (obj1)).getTriggerMillis())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = getEntityFingerprint();
        obj1 = ((UserNotification) (obj1)).getEntityFingerprint();
        boolean flag;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L4; else goto _L3
_L3:
        return false;
    }

    public abstract String getEntityFingerprint();

    public abstract long getExpirationMillis();

    public abstract int getPluginId();

    public abstract long getTriggerMillis();

    public abstract int getType();

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(getPluginId()), getEntityFingerprint(), Integer.valueOf(getType()), Long.valueOf(getTriggerMillis())
        });
    }
}
