// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;


// Referenced classes of package com.google.android.calendar.api.event:
//            NotificationInfo, EventKey

final class AutoValue_NotificationInfo extends NotificationInfo
{

    private final EventKey eventKey;
    private final long expirationMillis;
    private final long triggerMillis;
    private final NotificationInfo.NotificationType type;

    AutoValue_NotificationInfo(EventKey eventkey, NotificationInfo.NotificationType notificationtype, long l, long l1)
    {
        if (eventkey == null)
        {
            throw new NullPointerException("Null eventKey");
        }
        eventKey = eventkey;
        if (notificationtype == null)
        {
            throw new NullPointerException("Null type");
        } else
        {
            type = notificationtype;
            triggerMillis = l;
            expirationMillis = l1;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof NotificationInfo)
            {
                if (!eventKey.equals(((NotificationInfo) (obj = (NotificationInfo)obj)).getEventKey()) || !type.equals(((NotificationInfo) (obj)).getType()) || triggerMillis != ((NotificationInfo) (obj)).getTriggerMillis() || expirationMillis != ((NotificationInfo) (obj)).getExpirationMillis())
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

    public final EventKey getEventKey()
    {
        return eventKey;
    }

    public final long getExpirationMillis()
    {
        return expirationMillis;
    }

    public final long getTriggerMillis()
    {
        return triggerMillis;
    }

    public final NotificationInfo.NotificationType getType()
    {
        return type;
    }

    public final int hashCode()
    {
        return (((eventKey.hashCode() ^ 0xf4243) * 0xf4243 ^ type.hashCode()) * 0xf4243 ^ (int)(triggerMillis >>> 32 ^ triggerMillis)) * 0xf4243 ^ (int)(expirationMillis >>> 32 ^ expirationMillis);
    }

    public final String toString()
    {
        String s = String.valueOf(eventKey);
        String s1 = String.valueOf(type);
        long l = triggerMillis;
        long l1 = expirationMillis;
        return (new StringBuilder(String.valueOf(s).length() + 109 + String.valueOf(s1).length())).append("NotificationInfo{eventKey=").append(s).append(", type=").append(s1).append(", triggerMillis=").append(l).append(", expirationMillis=").append(l1).append("}").toString();
    }
}
