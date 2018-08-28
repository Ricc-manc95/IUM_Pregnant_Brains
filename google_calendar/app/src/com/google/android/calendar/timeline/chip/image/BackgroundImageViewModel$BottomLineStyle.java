// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip.image;


public final class  extends Enum
{

    private static final WITH_BOTTOM_LINE $VALUES[];
    public static final WITH_BOTTOM_LINE WITHOUT_BOTTOM_LINE;
    public static final WITH_BOTTOM_LINE WITH_BOTTOM_LINE;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        WITHOUT_BOTTOM_LINE = new <init>("WITHOUT_BOTTOM_LINE", 0);
        WITH_BOTTOM_LINE = new <init>("WITH_BOTTOM_LINE", 1);
        $VALUES = (new .VALUES[] {
            WITHOUT_BOTTOM_LINE, WITH_BOTTOM_LINE
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
