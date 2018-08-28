// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.common.ical4jutils;

import net.fortuna.ical4j.model.TimeZoneRegistryFactory;

public final class Q
{

    public static final TimeZoneRegistryFactory timeZoneRegistryFactory;

    static 
    {
        System.setProperty("net.fortuna.ical4j.timezone.update.enabled", "false");
        timeZoneRegistryFactory = TimeZoneRegistryFactory.instance;
    }
}
