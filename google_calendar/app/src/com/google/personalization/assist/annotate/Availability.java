// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


public final class Availability extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final Availability $VALUES[];
    public static final Availability AVAILABLE;
    public static final Availability PARTIALLY_AVAILABLE;
    public static final Availability UNAVAILABLE;
    public static final Availability UNKNOWN_AVAILABILITY;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    private final int value;

    private Availability(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static Availability forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_AVAILABILITY;

        case 1: // '\001'
            return AVAILABLE;

        case 2: // '\002'
            return PARTIALLY_AVAILABLE;

        case 3: // '\003'
            return UNAVAILABLE;
        }
    }

    public static Availability[] values()
    {
        return (Availability[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN_AVAILABILITY = new Availability("UNKNOWN_AVAILABILITY", 0, 0);
        AVAILABLE = new Availability("AVAILABLE", 1, 1);
        PARTIALLY_AVAILABLE = new Availability("PARTIALLY_AVAILABLE", 2, 2);
        UNAVAILABLE = new Availability("UNAVAILABLE", 3, 3);
        $VALUES = (new Availability[] {
            UNKNOWN_AVAILABILITY, AVAILABLE, PARTIALLY_AVAILABLE, UNAVAILABLE
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return Availability.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
