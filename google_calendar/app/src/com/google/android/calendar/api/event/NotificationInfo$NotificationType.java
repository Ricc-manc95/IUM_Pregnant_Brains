// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;


public final class value extends Enum
{

    private static final HABIT_FIT_CHECK $VALUES[];
    public static final HABIT_FIT_CHECK EVENT;
    public static final HABIT_FIT_CHECK HABIT_FIT_CHECK;
    public static final HABIT_FIT_CHECK HABIT_FOLLOWUP;
    public static final HABIT_FIT_CHECK HABIT_PREINSTANCE;
    public static final HABIT_FIT_CHECK HABIT_RECOMMIT;
    public static final HABIT_FIT_CHECK HABIT_RESOLVED_BY_FIT;
    public static final HABIT_FIT_CHECK UNKNOWN;
    public final int value;

    public static value fromInteger(int i)
    {
        value avalue[] = values();
        int k = avalue.length;
        for (int j = 0; j < k; j++)
        {
            value value1 = avalue[j];
            if (value1.value == i)
            {
                return value1;
            }
        }

        return UNKNOWN;
    }

    public static UNKNOWN[] values()
    {
        return (UNKNOWN[])$VALUES.clone();
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        EVENT = new <init>("EVENT", 1, 1);
        HABIT_PREINSTANCE = new <init>("HABIT_PREINSTANCE", 2, 2);
        HABIT_RECOMMIT = new <init>("HABIT_RECOMMIT", 3, 3);
        HABIT_FOLLOWUP = new <init>("HABIT_FOLLOWUP", 4, 4);
        HABIT_RESOLVED_BY_FIT = new <init>("HABIT_RESOLVED_BY_FIT", 5, 5);
        HABIT_FIT_CHECK = new <init>("HABIT_FIT_CHECK", 6, 6);
        $VALUES = (new .VALUES[] {
            UNKNOWN, EVENT, HABIT_PREINSTANCE, HABIT_RECOMMIT, HABIT_FOLLOWUP, HABIT_RESOLVED_BY_FIT, HABIT_FIT_CHECK
        });
    }

    private (String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
