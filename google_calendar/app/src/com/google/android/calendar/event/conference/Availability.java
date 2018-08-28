// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.conference;


public final class Availability extends Enum
{

    private static final Availability $VALUES[];
    public static final Availability INTERNAL_ONLY;
    public static final Availability NONE;
    public static final Availability PUBLIC;

    private Availability(String s, int i)
    {
        super(s, i);
    }

    public static Availability valueOf(String s)
    {
        return (Availability)Enum.valueOf(com/google/android/calendar/event/conference/Availability, s);
    }

    public static Availability[] values()
    {
        return (Availability[])$VALUES.clone();
    }

    static 
    {
        PUBLIC = new Availability("PUBLIC", 0);
        INTERNAL_ONLY = new Availability("INTERNAL_ONLY", 1);
        NONE = new Availability("NONE", 2);
        $VALUES = (new Availability[] {
            PUBLIC, INTERNAL_ONLY, NONE
        });
    }
}
