// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


public final class TemperatureUnit extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final TemperatureUnit $VALUES[];
    public static final TemperatureUnit CELSIUS;
    public static final TemperatureUnit FAHRENHEIT;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    private final int value;

    private TemperatureUnit(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static TemperatureUnit forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return FAHRENHEIT;

        case 1: // '\001'
            return CELSIUS;
        }
    }

    public static TemperatureUnit[] values()
    {
        return (TemperatureUnit[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        FAHRENHEIT = new TemperatureUnit("FAHRENHEIT", 0, 0);
        CELSIUS = new TemperatureUnit("CELSIUS", 1, 1);
        $VALUES = (new TemperatureUnit[] {
            FAHRENHEIT, CELSIUS
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return TemperatureUnit.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
