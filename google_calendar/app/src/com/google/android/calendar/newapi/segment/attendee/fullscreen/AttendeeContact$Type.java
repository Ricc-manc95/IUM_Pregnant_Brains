// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;


final class Y extends Enum
{

    private static final ADDED_FINAL $VALUES[];
    public static final ADDED_FINAL ADDED_FINAL;
    public static final ADDED_FINAL ADDED_REMOVABLE;
    public static final ADDED_FINAL SUGGESTION;

    public static Y valueOf(String s)
    {
        return (Y)Enum.valueOf(com/google/android/calendar/newapi/segment/attendee/fullscreen/AttendeeContact$Type, s);
    }

    public static Y[] values()
    {
        return (Y[])$VALUES.clone();
    }

    static 
    {
        SUGGESTION = new <init>("SUGGESTION", 0);
        ADDED_REMOVABLE = new <init>("ADDED_REMOVABLE", 1);
        ADDED_FINAL = new <init>("ADDED_FINAL", 2);
        $VALUES = (new .VALUES[] {
            SUGGESTION, ADDED_REMOVABLE, ADDED_FINAL
        });
    }

    private Y(String s, int i)
    {
        super(s, i);
    }
}
