// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.job;


public final class I extends Enum
{

    private static final HARD_ERROR $VALUES[];
    public static final HARD_ERROR HARD_ERROR;
    public static final HARD_ERROR SOFT_ERROR;
    public static final HARD_ERROR SUCCESS;

    public static I[] values()
    {
        return (I[])$VALUES.clone();
    }

    static 
    {
        SUCCESS = new <init>("SUCCESS", 0);
        SOFT_ERROR = new <init>("SOFT_ERROR", 1);
        HARD_ERROR = new <init>("HARD_ERROR", 2);
        $VALUES = (new .VALUES[] {
            SUCCESS, SOFT_ERROR, HARD_ERROR
        });
    }

    private I(String s, int i)
    {
        super(s, i);
    }
}
