// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            ConferenceSolution

public static final class value extends Enum
    implements com.google.protobuf.lution.Key.Type
{

    private static final forNumber $VALUES[];
    public static final forNumber ADD_ON;
    public static final forNumber EVENT_HANGOUT;
    public static final forNumber EVENT_NAMED_HANGOUT;
    public static final forNumber HANGOUTS_MEET;
    public static final forNumber UNKNOWN_CONFERENCE_SOLUTION;
    public static final com.google.protobuf.lution.Key.Type internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_CONFERENCE_SOLUTION;

        case 1: // '\001'
            return EVENT_HANGOUT;

        case 2: // '\002'
            return EVENT_NAMED_HANGOUT;

        case 3: // '\003'
            return HANGOUTS_MEET;

        case 4: // '\004'
            return ADD_ON;
        }
    }

    public static ADD_ON[] values()
    {
        return (ADD_ON[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN_CONFERENCE_SOLUTION = new <init>("UNKNOWN_CONFERENCE_SOLUTION", 0, 0);
        EVENT_HANGOUT = new <init>("EVENT_HANGOUT", 1, 1);
        EVENT_NAMED_HANGOUT = new <init>("EVENT_NAMED_HANGOUT", 2, 2);
        HANGOUTS_MEET = new <init>("HANGOUTS_MEET", 3, 3);
        ADD_ON = new <init>("ADD_ON", 4, 4);
        $VALUES = (new .VALUES[] {
            UNKNOWN_CONFERENCE_SOLUTION, EVENT_HANGOUT, EVENT_NAMED_HANGOUT, HANGOUTS_MEET, ADD_ON
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return ConferenceSolution.Key.Type.forNumber(i) != null;
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
