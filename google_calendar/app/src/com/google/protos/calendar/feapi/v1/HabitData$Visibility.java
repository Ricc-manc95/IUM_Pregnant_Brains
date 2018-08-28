// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            HabitData

public static final class value extends Enum
    implements com.google.protobuf.Data.Visibility
{

    private static final forNumber $VALUES[];
    public static final forNumber DEFAULT;
    private static final forNumber PRIVATE;
    private static final forNumber PUBLIC;
    public static final com.google.protobuf. internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return DEFAULT;

        case 1: // '\001'
            return PUBLIC;

        case 2: // '\002'
            return PRIVATE;
        }
    }

    public static PRIVATE[] values()
    {
        return (PRIVATE[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        DEFAULT = new <init>("DEFAULT", 0, 0);
        PUBLIC = new <init>("PUBLIC", 1, 1);
        PRIVATE = new <init>("PRIVATE", 2, 2);
        $VALUES = (new .VALUES[] {
            DEFAULT, PUBLIC, PRIVATE
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return HabitData.Visibility.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

    }

    private _cls2(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
