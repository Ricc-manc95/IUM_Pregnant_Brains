// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;


public final class  extends Enum
{

    private static final LAST $VALUES[];
    public static final LAST LAST;
    public static final LAST MONTHDAY;
    public static final LAST WEEKDAY;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/android/calendar/recurrencepicker/RecurrencePickerState$MonthFrequency, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        MONTHDAY = new <init>("MONTHDAY", 0);
        WEEKDAY = new <init>("WEEKDAY", 1);
        LAST = new <init>("LAST", 2);
        $VALUES = (new .VALUES[] {
            MONTHDAY, WEEKDAY, LAST
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
