// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;


public final class  extends Enum
{

    private static final DISPLAY_ONLY $VALUES[];
    public static final DISPLAY_ONLY DISPLAY_ONLY;
    public static final DISPLAY_ONLY REGULAR;
    public static final DISPLAY_ONLY VIRTUAL_ONLY;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        REGULAR = new <init>("REGULAR", 0);
        VIRTUAL_ONLY = new <init>("VIRTUAL_ONLY", 1);
        DISPLAY_ONLY = new <init>("DISPLAY_ONLY", 2);
        $VALUES = (new .VALUES[] {
            REGULAR, VIRTUAL_ONLY, DISPLAY_ONLY
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
