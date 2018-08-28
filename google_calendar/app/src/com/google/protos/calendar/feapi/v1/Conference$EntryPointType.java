// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Conference

public static final class value extends Enum
    implements com.google.protobuf..EntryPointType
{

    private static final forNumber $VALUES[];
    public static final forNumber MORE;
    public static final forNumber PHONE;
    public static final forNumber SIP;
    public static final forNumber STREAM;
    public static final forNumber UNKNOWN_ENTRY_POINT;
    public static final forNumber VIDEO;
    public static final com.google.protobuf..EntryPointType internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_ENTRY_POINT;

        case 1: // '\001'
            return VIDEO;

        case 2: // '\002'
            return PHONE;

        case 3: // '\003'
            return MORE;

        case 4: // '\004'
            return SIP;

        case 5: // '\005'
            return STREAM;
        }
    }

    public static STREAM[] values()
    {
        return (STREAM[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN_ENTRY_POINT = new <init>("UNKNOWN_ENTRY_POINT", 0, 0);
        VIDEO = new <init>("VIDEO", 1, 1);
        PHONE = new <init>("PHONE", 2, 2);
        MORE = new <init>("MORE", 3, 3);
        SIP = new <init>("SIP", 4, 4);
        STREAM = new <init>("STREAM", 5, 5);
        $VALUES = (new .VALUES[] {
            UNKNOWN_ENTRY_POINT, VIDEO, PHONE, MORE, SIP, STREAM
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Conference.EntryPointType.forNumber(i) != null;
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
