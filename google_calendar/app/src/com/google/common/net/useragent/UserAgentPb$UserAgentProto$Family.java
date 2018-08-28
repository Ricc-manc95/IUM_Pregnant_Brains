// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.net.useragent;


// Referenced classes of package com.google.common.net.useragent:
//            UserAgentPb

public static final class value extends Enum
    implements com.google.protobuf.roto.Family
{

    private static final forNumber $VALUES[];
    private static final forNumber APPLE;
    private static final forNumber APPLEWEBKIT;
    private static final forNumber BLACKBERRY;
    private static final forNumber DOCOMO;
    private static final forNumber GECKO;
    private static final forNumber GOOGLE;
    private static final forNumber KHTML;
    private static final forNumber KOREAN;
    private static final forNumber MICROSOFT;
    private static final forNumber MSIE;
    private static final forNumber NETFRONT;
    private static final forNumber NOKIA;
    private static final forNumber OBIGO;
    private static final forNumber OPENWAVE;
    private static final forNumber OPERA;
    private static final forNumber OTHER;
    private static final forNumber POLARIS;
    private static final forNumber SEMC;
    private static final forNumber SMIT;
    private static final forNumber TELECA;
    private static final forNumber USER_DEFINED;
    public static final com.google.protobuf.roto.Family internalVerifier = new _cls2();
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        default:
            return null;

        case 0: // '\0'
            return USER_DEFINED;

        case 1: // '\001'
            return MSIE;

        case 2: // '\002'
            return GECKO;

        case 3: // '\003'
            return APPLEWEBKIT;

        case 4: // '\004'
            return OPERA;

        case 5: // '\005'
            return KHTML;

        case 10: // '\n'
            return OTHER;

        case 11: // '\013'
            return APPLE;

        case 12: // '\f'
            return BLACKBERRY;

        case 13: // '\r'
            return DOCOMO;

        case 14: // '\016'
            return GOOGLE;

        case 15: // '\017'
            return OPENWAVE;

        case 16: // '\020'
            return POLARIS;

        case 17: // '\021'
            return OBIGO;

        case 18: // '\022'
            return TELECA;

        case 19: // '\023'
            return MICROSOFT;

        case 20: // '\024'
            return NOKIA;

        case 21: // '\025'
            return NETFRONT;

        case 22: // '\026'
            return SEMC;

        case 23: // '\027'
            return SMIT;

        case 24: // '\030'
            return KOREAN;
        }
    }

    public static KOREAN[] values()
    {
        return (KOREAN[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        USER_DEFINED = new <init>("USER_DEFINED", 0, 0);
        MSIE = new <init>("MSIE", 1, 1);
        GECKO = new <init>("GECKO", 2, 2);
        APPLEWEBKIT = new <init>("APPLEWEBKIT", 3, 3);
        OPERA = new <init>("OPERA", 4, 4);
        KHTML = new <init>("KHTML", 5, 5);
        OTHER = new <init>("OTHER", 6, 10);
        APPLE = new <init>("APPLE", 7, 11);
        BLACKBERRY = new <init>("BLACKBERRY", 8, 12);
        DOCOMO = new <init>("DOCOMO", 9, 13);
        GOOGLE = new <init>("GOOGLE", 10, 14);
        OPENWAVE = new <init>("OPENWAVE", 11, 15);
        POLARIS = new <init>("POLARIS", 12, 16);
        OBIGO = new <init>("OBIGO", 13, 17);
        TELECA = new <init>("TELECA", 14, 18);
        MICROSOFT = new <init>("MICROSOFT", 15, 19);
        NOKIA = new <init>("NOKIA", 16, 20);
        NETFRONT = new <init>("NETFRONT", 17, 21);
        SEMC = new <init>("SEMC", 18, 22);
        SMIT = new <init>("SMIT", 19, 23);
        KOREAN = new <init>("KOREAN", 20, 24);
        $VALUES = (new .VALUES[] {
            USER_DEFINED, MSIE, GECKO, APPLEWEBKIT, OPERA, KHTML, OTHER, APPLE, BLACKBERRY, DOCOMO, 
            GOOGLE, OPENWAVE, POLARIS, OBIGO, TELECA, MICROSOFT, NOKIA, NETFRONT, SEMC, SMIT, 
            KOREAN
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return UserAgentPb.UserAgentProto.Family.forNumber(i) != null;
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
