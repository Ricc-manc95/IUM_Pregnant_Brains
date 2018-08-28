// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            ConferenceRequestStatus

public static final class value extends Enum
    implements com.google.protobuf.atus.StatusCode
{

    private static final forNumber $VALUES[];
    public static final forNumber FAILURE;
    public static final forNumber PENDING;
    public static final forNumber SUCCESS;
    public static final forNumber UNKNOWN;
    public static final com.google.protobuf.atus.StatusCode internalVerifier = new _cls2();
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN;

        case 1: // '\001'
            return PENDING;

        case 2: // '\002'
            return SUCCESS;

        case 3: // '\003'
            return FAILURE;
        }
    }

    public static FAILURE[] values()
    {
        return (FAILURE[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        PENDING = new <init>("PENDING", 1, 1);
        SUCCESS = new <init>("SUCCESS", 2, 2);
        FAILURE = new <init>("FAILURE", 3, 3);
        $VALUES = (new .VALUES[] {
            UNKNOWN, PENDING, SUCCESS, FAILURE
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return ConferenceRequestStatus.StatusCode.forNumber(i) != null;
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
