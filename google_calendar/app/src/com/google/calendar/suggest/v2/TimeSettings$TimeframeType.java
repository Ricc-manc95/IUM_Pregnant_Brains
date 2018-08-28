// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;


// Referenced classes of package com.google.calendar.suggest.v2:
//            TimeSettings

public static final class value extends Enum
    implements com.google.protobuf.eframeType
{

    private static final _cls2 $VALUES[];
    public static final _cls2 AROUND_DATE;
    private static final _cls2 EXACT_WINDOW;
    private static final _cls2 MULTIPLE_WINDOWS;
    public static final _cls2 UNRECOGNIZED;
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return EXACT_WINDOW;

        case 1: // '\001'
            return AROUND_DATE;

        case 2: // '\002'
            return MULTIPLE_WINDOWS;
        }
    }

    public static MULTIPLE_WINDOWS[] values()
    {
        return (MULTIPLE_WINDOWS[])$VALUES.clone();
    }

    public final int getNumber()
    {
        if (this == UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        } else
        {
            return value;
        }
    }

    static 
    {
        EXACT_WINDOW = new <init>("EXACT_WINDOW", 0, 0);
        AROUND_DATE = new <init>("AROUND_DATE", 1, 1);
        MULTIPLE_WINDOWS = new <init>("MULTIPLE_WINDOWS", 2, 2);
        UNRECOGNIZED = new <init>("UNRECOGNIZED", 3, -1);
        $VALUES = (new .VALUES[] {
            EXACT_WINDOW, AROUND_DATE, MULTIPLE_WINDOWS, UNRECOGNIZED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return TimeSettings.TimeframeType.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

        new _cls2();
    }

    private _cls2(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
