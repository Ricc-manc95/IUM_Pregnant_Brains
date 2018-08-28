// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Contract

public static final class value extends Enum
    implements com.google.protobuf.ntract.Interval
{

    private static final forNumber $VALUES[];
    private static final forNumber DAILY;
    private static final forNumber MONTHLY;
    public static final forNumber UNKNOWN;
    private static final forNumber WEEKLY;
    public static final com.google.protobuf.ier internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN;

        case 1: // '\001'
            return DAILY;

        case 2: // '\002'
            return WEEKLY;

        case 3: // '\003'
            return MONTHLY;
        }
    }

    public static MONTHLY[] values()
    {
        return (MONTHLY[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        DAILY = new <init>("DAILY", 1, 1);
        WEEKLY = new <init>("WEEKLY", 2, 2);
        MONTHLY = new <init>("MONTHLY", 3, 3);
        $VALUES = (new .VALUES[] {
            UNKNOWN, DAILY, WEEKLY, MONTHLY
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Contract.Interval.forNumber(i) != null;
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
