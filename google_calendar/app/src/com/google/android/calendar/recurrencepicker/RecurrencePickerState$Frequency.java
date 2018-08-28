// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;


public final class  extends Enum
{

    private static final YEARLY $VALUES[];
    public static final YEARLY DAILY;
    public static final YEARLY MONTHLY;
    public static final YEARLY WEEKLY;
    public static final YEARLY YEARLY;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/android/calendar/recurrencepicker/RecurrencePickerState$Frequency, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        DAILY = new <init>("DAILY", 0);
        WEEKLY = new <init>("WEEKLY", 1);
        MONTHLY = new <init>("MONTHLY", 2);
        YEARLY = new <init>("YEARLY", 3);
        $VALUES = (new .VALUES[] {
            DAILY, WEEKLY, MONTHLY, YEARLY
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
