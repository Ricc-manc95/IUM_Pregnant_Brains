// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;


public final class  extends Enum
{

    private static final BADGED $VALUES[];
    public static final BADGED BADGED;
    public static final BADGED REGULAR;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        REGULAR = new <init>("REGULAR", 0);
        BADGED = new <init>("BADGED", 1);
        $VALUES = (new .VALUES[] {
            REGULAR, BADGED
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
