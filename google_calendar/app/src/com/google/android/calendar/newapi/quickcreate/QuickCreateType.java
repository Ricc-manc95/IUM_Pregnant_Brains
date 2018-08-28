// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;


public final class QuickCreateType extends Enum
{

    private static final QuickCreateType $VALUES[];
    public static final QuickCreateType EVENT;
    public static final QuickCreateType REMINDER;

    private QuickCreateType(String s, int i)
    {
        super(s, i);
    }

    public static QuickCreateType valueOf(String s)
    {
        return (QuickCreateType)Enum.valueOf(com/google/android/calendar/newapi/quickcreate/QuickCreateType, s);
    }

    public static QuickCreateType[] values()
    {
        return (QuickCreateType[])$VALUES.clone();
    }

    static 
    {
        EVENT = new QuickCreateType("EVENT", 0);
        REMINDER = new QuickCreateType("REMINDER", 1);
        $VALUES = (new QuickCreateType[] {
            EVENT, REMINDER
        });
    }
}
