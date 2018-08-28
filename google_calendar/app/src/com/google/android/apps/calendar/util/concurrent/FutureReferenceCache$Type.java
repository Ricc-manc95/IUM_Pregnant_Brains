// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;


public final class referenceCacheType extends Enum
{

    private static final SOFT $VALUES[];
    public static final SOFT SOFT;
    private static final SOFT WEAK;
    public final com.google.android.apps.calendar.util.collect.pe referenceCacheType;

    public static referenceCacheType[] values()
    {
        return (referenceCacheType[])$VALUES.clone();
    }

    static 
    {
        WEAK = new <init>("WEAK", 0, com.google.android.apps.calendar.util.collect.pe);
        SOFT = new <init>("SOFT", 1, com.google.android.apps.calendar.util.collect.pe);
        $VALUES = (new .VALUES[] {
            WEAK, SOFT
        });
    }

    private (String s, int i, com.google.android.apps.calendar.util.collect.pe pe)
    {
        super(s, i);
        referenceCacheType = pe;
    }
}
