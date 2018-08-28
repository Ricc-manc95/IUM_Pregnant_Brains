// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.api;


public final class  extends Enum
{

    private static final BACKWARD $VALUES[];
    public static final BACKWARD BACKWARD;
    public static final BACKWARD FORWARD;
    public static final BACKWARD NONE;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        NONE = new <init>("NONE", 0);
        FORWARD = new <init>("FORWARD", 1);
        BACKWARD = new <init>("BACKWARD", 2);
        $VALUES = (new .VALUES[] {
            NONE, FORWARD, BACKWARD
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
