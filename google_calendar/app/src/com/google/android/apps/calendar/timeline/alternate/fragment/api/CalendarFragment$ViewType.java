// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.api;


public final class  extends Enum
{

    private static final MONTH $VALUES[];
    public static final MONTH AGENDA;
    public static final MONTH DAY;
    public static final MONTH MONTH;
    public static final MONTH THREE_DAY;
    public static final MONTH WEEK;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/android/apps/calendar/timeline/alternate/fragment/api/CalendarFragment$ViewType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        DAY = new <init>("DAY", 0);
        THREE_DAY = new <init>("THREE_DAY", 1);
        WEEK = new <init>("WEEK", 2);
        AGENDA = new <init>("AGENDA", 3);
        MONTH = new <init>("MONTH", 4);
        $VALUES = (new .VALUES[] {
            DAY, THREE_DAY, WEEK, AGENDA, MONTH
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
