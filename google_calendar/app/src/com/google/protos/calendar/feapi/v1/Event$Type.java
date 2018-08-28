// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Event

public static final class value extends Enum
    implements com.google.protobuf.umLite
{

    private static final forNumber $VALUES[];
    public static final forNumber PLUS_EVENT;
    public static final forNumber UNKNOWN;
    public static final com.google.protobuf.umVerifier internalVerifier = new _cls2();
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
            return PLUS_EVENT;
        }
    }

    public static PLUS_EVENT[] values()
    {
        return (PLUS_EVENT[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        PLUS_EVENT = new <init>("PLUS_EVENT", 1, 1);
        $VALUES = (new .VALUES[] {
            UNKNOWN, PLUS_EVENT
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Event.Type.forNumber(i) != null;
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
