// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.time;

import java.io.Serializable;
import org.joda.time.Instant;

// Referenced classes of package com.google.common.time:
//            Clock

public class SystemClock
    implements Clock, Serializable
{

    public static final long serialVersionUID = 0L;

    public SystemClock()
    {
    }

    public boolean equals(Object obj)
    {
        return obj instanceof SystemClock;
    }

    public int hashCode()
    {
        return com/google/common/time/SystemClock.hashCode();
    }

    public final Instant now()
    {
        return new Instant();
    }

    public String toString()
    {
        return "SystemClock";
    }
}
