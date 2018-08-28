// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Event

public static final class value extends Enum
    implements com.google.protobuf..ResponseStatus
{

    private static final forNumber $VALUES[];
    public static final forNumber ACCEPTED;
    public static final forNumber DECLINED;
    public static final forNumber NEEDS_ACTION;
    public static final forNumber TENTATIVE;
    public static final com.google.protobuf..ResponseStatus internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return NEEDS_ACTION;

        case 1: // '\001'
            return DECLINED;

        case 2: // '\002'
            return TENTATIVE;

        case 3: // '\003'
            return ACCEPTED;
        }
    }

    public static ACCEPTED[] values()
    {
        return (ACCEPTED[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        NEEDS_ACTION = new <init>("NEEDS_ACTION", 0, 0);
        DECLINED = new <init>("DECLINED", 1, 1);
        TENTATIVE = new <init>("TENTATIVE", 2, 2);
        ACCEPTED = new <init>("ACCEPTED", 3, 3);
        $VALUES = (new .VALUES[] {
            NEEDS_ACTION, DECLINED, TENTATIVE, ACCEPTED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Event.Attendee.ResponseStatus.forNumber(i) != null;
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
