// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.caribou.smartmail;


// Referenced classes of package com.google.caribou.smartmail:
//            GoTo

public static final class value extends Enum
    implements com.google.protobuf.numLite
{

    private static final forNumber $VALUES[];
    public static final forNumber CHECK_IN;
    public static final forNumber EDIT;
    public static final forNumber LISTEN;
    public static final forNumber PAY;
    public static final forNumber PROVIDER;
    public static final forNumber SHARE;
    public static final forNumber STRUCTURED;
    public static final forNumber TRACK;
    public static final forNumber UNKNOWN;
    public static final forNumber VIDEO_MEETING;
    public static final forNumber VIEW;
    public static final com.google.protobuf.numVerifier internalVerifier = new _cls2();
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
            return UNKNOWN;

        case 2: // '\002'
            return CHECK_IN;

        case 3: // '\003'
            return EDIT;

        case 4: // '\004'
            return TRACK;

        case 5: // '\005'
            return VIEW;

        case 6: // '\006'
            return SHARE;

        case 7: // '\007'
            return LISTEN;

        case 8: // '\b'
            return STRUCTURED;

        case 9: // '\t'
            return VIDEO_MEETING;

        case 10: // '\n'
            return PROVIDER;

        case 11: // '\013'
            return PAY;
        }
    }

    public static PAY[] values()
    {
        return (PAY[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 1);
        CHECK_IN = new <init>("CHECK_IN", 1, 2);
        EDIT = new <init>("EDIT", 2, 3);
        TRACK = new <init>("TRACK", 3, 4);
        VIEW = new <init>("VIEW", 4, 5);
        SHARE = new <init>("SHARE", 5, 6);
        LISTEN = new <init>("LISTEN", 6, 7);
        STRUCTURED = new <init>("STRUCTURED", 7, 8);
        VIDEO_MEETING = new <init>("VIDEO_MEETING", 8, 9);
        PROVIDER = new <init>("PROVIDER", 9, 10);
        PAY = new <init>("PAY", 10, 11);
        $VALUES = (new .VALUES[] {
            UNKNOWN, CHECK_IN, EDIT, TRACK, VIEW, SHARE, LISTEN, STRUCTURED, VIDEO_MEETING, PROVIDER, 
            PAY
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return GoTo.Type.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

    }

    private er(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
