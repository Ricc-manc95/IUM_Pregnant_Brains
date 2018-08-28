// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;


// Referenced classes of package net.fortuna.ical4j.util:
//            HostInfo

public final class SimpleHostInfo
    implements HostInfo
{

    private final String hostName;

    public SimpleHostInfo(String s)
    {
        hostName = s;
    }

    public final String getHostName()
    {
        return hostName;
    }
}
