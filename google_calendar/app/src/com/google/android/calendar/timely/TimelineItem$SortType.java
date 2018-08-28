// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


public final class  extends Enum
{

    private static final DONE_BUNDLE_REMINDER $VALUES[];
    public static final DONE_BUNDLE_REMINDER BIRTHDAY;
    public static final DONE_BUNDLE_REMINDER DONE_BUNDLE_REMINDER;
    public static final DONE_BUNDLE_REMINDER DONE_SINGLE_REMINDER;
    public static final DONE_BUNDLE_REMINDER EVENT;
    public static final DONE_BUNDLE_REMINDER HOLIDAY;
    public static final DONE_BUNDLE_REMINDER INCOMPLETE_BUNDLE_REMINDER;
    public static final DONE_BUNDLE_REMINDER INCOMPLETE_SINGLE_REMINDER;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        EVENT = new <init>("EVENT", 0);
        INCOMPLETE_SINGLE_REMINDER = new <init>("INCOMPLETE_SINGLE_REMINDER", 1);
        INCOMPLETE_BUNDLE_REMINDER = new <init>("INCOMPLETE_BUNDLE_REMINDER", 2);
        BIRTHDAY = new <init>("BIRTHDAY", 3);
        HOLIDAY = new <init>("HOLIDAY", 4);
        DONE_SINGLE_REMINDER = new <init>("DONE_SINGLE_REMINDER", 5);
        DONE_BUNDLE_REMINDER = new <init>("DONE_BUNDLE_REMINDER", 6);
        $VALUES = (new .VALUES[] {
            EVENT, INCOMPLETE_SINGLE_REMINDER, INCOMPLETE_BUNDLE_REMINDER, BIRTHDAY, HOLIDAY, DONE_SINGLE_REMINDER, DONE_BUNDLE_REMINDER
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
