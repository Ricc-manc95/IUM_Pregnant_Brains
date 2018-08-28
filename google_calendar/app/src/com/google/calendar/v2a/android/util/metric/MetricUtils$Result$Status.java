// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;


public final class  extends Enum
{

    private static final CANCEL $VALUES[];
    public static final CANCEL CANCEL;
    public static final CANCEL FAILURE;
    public static final CANCEL SUCCESS;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        SUCCESS = new <init>("SUCCESS", 0);
        FAILURE = new <init>("FAILURE", 1);
        CANCEL = new <init>("CANCEL", 2);
        $VALUES = (new .VALUES[] {
            SUCCESS, FAILURE, CANCEL
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
