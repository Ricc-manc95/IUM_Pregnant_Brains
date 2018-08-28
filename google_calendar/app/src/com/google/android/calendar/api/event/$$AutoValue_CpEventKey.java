// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;


// Referenced classes of package com.google.android.calendar.api.event:
//            CpEventKey

abstract class $$AutoValue_CpEventKey extends CpEventKey
{

    private final boolean hasStartMillis;
    private final long localId;
    private final long startMillis;

    $$AutoValue_CpEventKey(boolean flag, long l, long l1)
    {
        hasStartMillis = flag;
        startMillis = l;
        localId = l1;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof CpEventKey)
            {
                if (hasStartMillis != ((CpEventKey) (obj = (CpEventKey)obj)).hasStartMillis() || startMillis != ((CpEventKey) (obj)).startMillis() || localId != ((CpEventKey) (obj)).localId())
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

    public final boolean hasStartMillis()
    {
        return hasStartMillis;
    }

    public int hashCode()
    {
        char c;
        if (hasStartMillis)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return ((c ^ 0xf4243) * 0xf4243 ^ (int)(startMillis >>> 32 ^ startMillis)) * 0xf4243 ^ (int)(localId >>> 32 ^ localId);
    }

    public final long localId()
    {
        return localId;
    }

    public final long startMillis()
    {
        return startMillis;
    }

    public String toString()
    {
        boolean flag = hasStartMillis;
        long l = startMillis;
        long l1 = localId;
        return (new StringBuilder(96)).append("CpEventKey{hasStartMillis=").append(flag).append(", startMillis=").append(l).append(", localId=").append(l1).append("}").toString();
    }
}
