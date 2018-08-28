// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.contracts;


// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.contracts:
//            UserNotification

abstract class $AutoValue_UserNotification extends UserNotification
{

    private final String entityFingerprint;
    private final long expirationMillis;
    private final int pluginId;
    private final long triggerMillis;
    private final int type;

    $AutoValue_UserNotification(int i, String s, int j, long l, long l1)
    {
        pluginId = i;
        if (s == null)
        {
            throw new NullPointerException("Null entityFingerprint");
        } else
        {
            entityFingerprint = s;
            type = j;
            triggerMillis = l;
            expirationMillis = l1;
            return;
        }
    }

    public final String getEntityFingerprint()
    {
        return entityFingerprint;
    }

    public final long getExpirationMillis()
    {
        return expirationMillis;
    }

    public final int getPluginId()
    {
        return pluginId;
    }

    public final long getTriggerMillis()
    {
        return triggerMillis;
    }

    public final int getType()
    {
        return type;
    }

    public String toString()
    {
        int i = pluginId;
        String s = entityFingerprint;
        int j = type;
        long l = triggerMillis;
        long l1 = expirationMillis;
        return (new StringBuilder(String.valueOf(s).length() + 151)).append("UserNotification{pluginId=").append(i).append(", entityFingerprint=").append(s).append(", type=").append(j).append(", triggerMillis=").append(l).append(", expirationMillis=").append(l1).append("}").toString();
    }
}
