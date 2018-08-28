// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2.client.service.api.time;


// Referenced classes of package com.google.calendar.v2.client.service.api.time:
//            TimeZone

public interface Instant
    extends Comparable
{

    public abstract long getMillis();

    public abstract TimeZone getTimeZone();

    public abstract boolean isAfter(Instant instant);
}
