// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;


// Referenced classes of package net.fortuna.ical4j.model:
//            TimeZoneRegistryFactory, TimeZoneRegistryImpl, TimeZoneRegistry

public final class DefaultTimeZoneRegistryFactory extends TimeZoneRegistryFactory
{

    public DefaultTimeZoneRegistryFactory()
    {
    }

    public final TimeZoneRegistry createRegistry()
    {
        return new TimeZoneRegistryImpl();
    }
}
