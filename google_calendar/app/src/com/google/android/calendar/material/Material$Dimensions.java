// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.material;


public final class dimenResId extends Enum
{

    private static final KEYLINE_2ND_OFFSET $VALUES[];
    public static final KEYLINE_2ND_OFFSET INCREMENT;
    public static final KEYLINE_2ND_OFFSET KEYLINE_1ST;
    private static final KEYLINE_2ND_OFFSET KEYLINE_2ND;
    public static final KEYLINE_2ND_OFFSET KEYLINE_2ND_OFFSET;
    private static final KEYLINE_2ND_OFFSET LIST_VERTICAL_PADDING;
    public final int dimenResId;

    public static dimenResId[] values()
    {
        return (dimenResId[])$VALUES.clone();
    }

    static 
    {
        KEYLINE_1ST = new <init>("KEYLINE_1ST", 0, 0x7f0e0194);
        KEYLINE_2ND = new <init>("KEYLINE_2ND", 1, 0x7f0e0354);
        LIST_VERTICAL_PADDING = new <init>("LIST_VERTICAL_PADDING", 2, 0x7f0e0296);
        INCREMENT = new <init>("INCREMENT", 3, 0x7f0e025c);
        KEYLINE_2ND_OFFSET = new <init>("KEYLINE_2ND_OFFSET", 4, 0x7f0e0356);
        $VALUES = (new .VALUES[] {
            KEYLINE_1ST, KEYLINE_2ND, LIST_VERTICAL_PADDING, INCREMENT, KEYLINE_2ND_OFFSET
        });
    }

    private (String s, int i, int j)
    {
        super(s, i);
        dimenResId = j;
    }
}
