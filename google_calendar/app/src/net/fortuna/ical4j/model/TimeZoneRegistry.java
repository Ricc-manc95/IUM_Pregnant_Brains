// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;


// Referenced classes of package net.fortuna.ical4j.model:
//            TimeZone

public interface TimeZoneRegistry
{

    public abstract void clear();

    public abstract TimeZone getTimeZone(String s);

    public abstract void register(TimeZone timezone);
}
