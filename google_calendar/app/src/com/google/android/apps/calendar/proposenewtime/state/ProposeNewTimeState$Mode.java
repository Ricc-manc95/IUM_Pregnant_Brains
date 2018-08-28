// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.state;


public final class  extends Enum
{

    private static final REVIEW $VALUES[];
    public static final REVIEW PROPOSE;
    public static final REVIEW REVIEW;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/android/apps/calendar/proposenewtime/state/ProposeNewTimeState$Mode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        PROPOSE = new <init>("PROPOSE", 0);
        REVIEW = new <init>("REVIEW", 1);
        $VALUES = (new .VALUES[] {
            PROPOSE, REVIEW
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
