// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates;


public final class  extends Enum
{

    private static final DISPLAY_WITHOUT_NEW_SYNC $VALUES[];
    public static final DISPLAY_WITHOUT_NEW_SYNC BATTERY;
    public static final DISPLAY_WITHOUT_NEW_SYNC DISPLAY_WITHOUT_NEW_SYNC;
    public static final DISPLAY_WITHOUT_NEW_SYNC INSTALLED_APPS;
    public static final DISPLAY_WITHOUT_NEW_SYNC LANGUAGE;
    public static final DISPLAY_WITHOUT_NEW_SYNC NETWORK;
    public static final DISPLAY_WITHOUT_NEW_SYNC TIME_CONSTRAINT;
    private static final DISPLAY_WITHOUT_NEW_SYNC UNKNOWN;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0);
        BATTERY = new <init>("BATTERY", 1);
        INSTALLED_APPS = new <init>("INSTALLED_APPS", 2);
        NETWORK = new <init>("NETWORK", 3);
        LANGUAGE = new <init>("LANGUAGE", 4);
        TIME_CONSTRAINT = new <init>("TIME_CONSTRAINT", 5);
        DISPLAY_WITHOUT_NEW_SYNC = new <init>("DISPLAY_WITHOUT_NEW_SYNC", 6);
        $VALUES = (new .VALUES[] {
            UNKNOWN, BATTERY, INSTALLED_APPS, NETWORK, LANGUAGE, TIME_CONSTRAINT, DISPLAY_WITHOUT_NEW_SYNC
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
