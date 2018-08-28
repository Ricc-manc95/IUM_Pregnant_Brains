// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;


public final class  extends Enum
{

    private static final LIGHT $VALUES[];
    public static final LIGHT INVERTED;
    public static final LIGHT LIGHT;
    public static final LIGHT REGULAR;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        REGULAR = new <init>("REGULAR", 0);
        INVERTED = new <init>("INVERTED", 1);
        LIGHT = new <init>("LIGHT", 2);
        $VALUES = (new .VALUES[] {
            REGULAR, INVERTED, LIGHT
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
