// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;


public final class  extends Enum
{

    private static final COUNT $VALUES[];
    public static final COUNT COUNT;
    public static final COUNT DATE;
    public static final COUNT INFINITE;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/android/calendar/recurrencepicker/RecurrencePickerState$End, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        INFINITE = new <init>("INFINITE", 0);
        DATE = new <init>("DATE", 1);
        COUNT = new <init>("COUNT", 2);
        $VALUES = (new .VALUES[] {
            INFINITE, DATE, COUNT
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
