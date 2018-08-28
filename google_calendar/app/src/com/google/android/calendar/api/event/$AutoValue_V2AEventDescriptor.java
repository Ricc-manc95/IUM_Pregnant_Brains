// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;


// Referenced classes of package com.google.android.calendar.api.event:
//            V2AEventDescriptor, V2AEventKey, EventKey

abstract class $AutoValue_V2AEventDescriptor extends V2AEventDescriptor
{

    private final V2AEventKey key;
    public final long originalStart;
    public final boolean recurringException;
    public final boolean recurringPhantom;

    $AutoValue_V2AEventDescriptor(boolean flag, boolean flag1, long l, V2AEventKey v2aeventkey)
    {
        recurringException = flag;
        recurringPhantom = flag1;
        originalStart = l;
        if (v2aeventkey == null)
        {
            throw new NullPointerException("Null key");
        } else
        {
            key = v2aeventkey;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof V2AEventDescriptor)
        {
            obj = (V2AEventDescriptor)obj;
            return recurringException == ((V2AEventDescriptor) (obj)).isRecurringException() && recurringPhantom == ((V2AEventDescriptor) (obj)).isRecurringPhantom() && originalStart == ((V2AEventDescriptor) (obj)).getOriginalStart() && key.equals((V2AEventKey)((V2AEventDescriptor) (obj)).getKey());
        } else
        {
            return false;
        }
    }

    public final volatile EventKey getKey()
    {
        return getKey();
    }

    public final V2AEventKey getKey()
    {
        return key;
    }

    public final long getOriginalStart()
    {
        return originalStart;
    }

    public int hashCode()
    {
        char c1 = '\u04CF';
        char c;
        if (recurringException)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (!recurringPhantom)
        {
            c1 = '\u04D5';
        }
        return (((c ^ 0xf4243) * 0xf4243 ^ c1) * 0xf4243 ^ (int)(originalStart >>> 32 ^ originalStart)) * 0xf4243 ^ key.hashCode();
    }

    public final boolean isRecurringException()
    {
        return recurringException;
    }

    public final boolean isRecurringPhantom()
    {
        return recurringPhantom;
    }

    public String toString()
    {
        boolean flag = recurringException;
        boolean flag1 = recurringPhantom;
        long l = originalStart;
        String s = String.valueOf(key);
        return (new StringBuilder(String.valueOf(s).length() + 110)).append("V2AEventDescriptor{recurringException=").append(flag).append(", recurringPhantom=").append(flag1).append(", originalStart=").append(l).append(", key=").append(s).append("}").toString();
    }
}
