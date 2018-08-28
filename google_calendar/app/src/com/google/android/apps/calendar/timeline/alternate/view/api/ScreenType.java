// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.api;


public final class ScreenType extends Enum
{

    private static final ScreenType $VALUES[];
    public static final ScreenType LARGE_TABLET;
    public static final ScreenType PHONE;
    public static final ScreenType SMALL_TABLET;

    private ScreenType(String s, int i)
    {
        super(s, i);
    }

    public static ScreenType[] values()
    {
        return (ScreenType[])$VALUES.clone();
    }

    static 
    {
        PHONE = new ScreenType("PHONE", 0);
        SMALL_TABLET = new ScreenType("SMALL_TABLET", 1);
        LARGE_TABLET = new ScreenType("LARGE_TABLET", 2);
        $VALUES = (new ScreenType[] {
            PHONE, SMALL_TABLET, LARGE_TABLET
        });
    }
}
