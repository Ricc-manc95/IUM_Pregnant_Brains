// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;

import java.util.Collections;
import java.util.Set;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.tz:
//            Provider

public final class UTCProvider
    implements Provider
{

    public UTCProvider()
    {
    }

    public final Set getAvailableIDs()
    {
        return Collections.singleton("UTC");
    }

    public final DateTimeZone getZone(String s)
    {
        if ("UTC".equalsIgnoreCase(s))
        {
            return DateTimeZone.UTC;
        } else
        {
            return null;
        }
    }
}
