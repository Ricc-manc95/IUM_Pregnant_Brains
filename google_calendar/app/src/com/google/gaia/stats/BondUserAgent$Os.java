// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gaia.stats;


// Referenced classes of package com.google.gaia.stats:
//            BondUserAgent

public static final class value extends Enum
    implements com.google.protobuf.
{

    private static final forNumber $VALUES[];
    public static final forNumber ANDROID_OS;
    private static final forNumber ANDROID_THINGS_OS;
    private static final forNumber APPLE_TV_OS;
    private static final forNumber BLACKBERRY_OS;
    private static final forNumber CAST_OS;
    private static final forNumber CHROME_OS;
    private static final forNumber FIRE_OS;
    private static final forNumber IOS_OS;
    private static final forNumber KAI_OS;
    private static final forNumber LINUX_OS;
    private static final forNumber MAC_OS;
    private static final forNumber MAX_MOBILE_OS_VALUE;
    private static final forNumber PLAYSTATION_OS;
    private static final forNumber TIZEN_OS;
    private static final forNumber UNKNOWN_OS;
    private static final forNumber WINDOWS_OS;
    private static final forNumber WIN_PHONE_OS;
    private static final forNumber XBOX_OS;
    public static final com.google.protobuf.fier internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_OS;

        case 1: // '\001'
            return ANDROID_OS;

        case 2: // '\002'
            return IOS_OS;

        case 3: // '\003'
            return BLACKBERRY_OS;

        case 4: // '\004'
            return WIN_PHONE_OS;

        case 5: // '\005'
            return FIRE_OS;

        case 99: // 'c'
            return MAX_MOBILE_OS_VALUE;

        case 101: // 'e'
            return WINDOWS_OS;

        case 102: // 'f'
            return LINUX_OS;

        case 103: // 'g'
            return MAC_OS;

        case 104: // 'h'
            return CHROME_OS;

        case 110: // 'n'
            return PLAYSTATION_OS;

        case 111: // 'o'
            return XBOX_OS;

        case 112: // 'p'
            return TIZEN_OS;

        case 113: // 'q'
            return APPLE_TV_OS;

        case 114: // 'r'
            return KAI_OS;

        case 115: // 's'
            return ANDROID_THINGS_OS;

        case 116: // 't'
            return CAST_OS;
        }
    }

    public static CAST_OS[] values()
    {
        return (CAST_OS[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN_OS = new <init>("UNKNOWN_OS", 0, 0);
        ANDROID_OS = new <init>("ANDROID_OS", 1, 1);
        IOS_OS = new <init>("IOS_OS", 2, 2);
        BLACKBERRY_OS = new <init>("BLACKBERRY_OS", 3, 3);
        WIN_PHONE_OS = new <init>("WIN_PHONE_OS", 4, 4);
        FIRE_OS = new <init>("FIRE_OS", 5, 5);
        MAX_MOBILE_OS_VALUE = new <init>("MAX_MOBILE_OS_VALUE", 6, 99);
        WINDOWS_OS = new <init>("WINDOWS_OS", 7, 101);
        LINUX_OS = new <init>("LINUX_OS", 8, 102);
        MAC_OS = new <init>("MAC_OS", 9, 103);
        CHROME_OS = new <init>("CHROME_OS", 10, 104);
        PLAYSTATION_OS = new <init>("PLAYSTATION_OS", 11, 110);
        XBOX_OS = new <init>("XBOX_OS", 12, 111);
        TIZEN_OS = new <init>("TIZEN_OS", 13, 112);
        APPLE_TV_OS = new <init>("APPLE_TV_OS", 14, 113);
        KAI_OS = new <init>("KAI_OS", 15, 114);
        ANDROID_THINGS_OS = new <init>("ANDROID_THINGS_OS", 16, 115);
        CAST_OS = new <init>("CAST_OS", 17, 116);
        $VALUES = (new .VALUES[] {
            UNKNOWN_OS, ANDROID_OS, IOS_OS, BLACKBERRY_OS, WIN_PHONE_OS, FIRE_OS, MAX_MOBILE_OS_VALUE, WINDOWS_OS, LINUX_OS, MAC_OS, 
            CHROME_OS, PLAYSTATION_OS, XBOX_OS, TIZEN_OS, APPLE_TV_OS, KAI_OS, ANDROID_THINGS_OS, CAST_OS
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return BondUserAgent.Os.forNumber(i) != null;
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
