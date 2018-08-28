// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Event

public static final class value extends Enum
    implements com.google.protobuf.Lite
{

    private static final forNumber $VALUES[];
    public static final forNumber CANCELLED;
    public static final forNumber CONFIRMED;
    public static final forNumber TENTATIVE;
    public static final com.google.protobuf.Verifier internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return CONFIRMED;

        case 1: // '\001'
            return TENTATIVE;

        case 2: // '\002'
            return CANCELLED;
        }
    }

    public static CANCELLED[] values()
    {
        return (CANCELLED[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        CONFIRMED = new <init>("CONFIRMED", 0, 0);
        TENTATIVE = new <init>("TENTATIVE", 1, 1);
        CANCELLED = new <init>("CANCELLED", 2, 2);
        $VALUES = (new .VALUES[] {
            CONFIRMED, TENTATIVE, CANCELLED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Event.Status.forNumber(i) != null;
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
