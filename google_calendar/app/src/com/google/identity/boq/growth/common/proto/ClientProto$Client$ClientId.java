// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.boq.growth.common.proto;


// Referenced classes of package com.google.identity.boq.growth.common.proto:
//            ClientProto

public static final class value extends Enum
    implements com.google.protobuf.ntProto.Client.ClientId
{

    private static final forNumber $VALUES[];
    private static final forNumber ANDROID_MWEB;
    private static final forNumber ANDROID_NATIVE_NDN;
    private static final forNumber APPS_RECOMMENDATION;
    private static final forNumber CLIENT_UNSPECIFIED;
    private static final forNumber GOOGLE_TIPS;
    private static final forNumber GROWTH_KIT;
    public static final forNumber GROWTH_KIT_ANDROID;
    private static final forNumber GROWTH_KIT_ANDROID_GMSCORE;
    private static final forNumber MOBILE_WEB_APP;
    private static final forNumber NEW_DEVICE_NOTIFICATION;
    private static final forNumber SECURITY_ADVISOR;
    private static final forNumber WEB_APPLICATION;
    public static final com.google.protobuf.ntProto.Client.ClientId internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return CLIENT_UNSPECIFIED;

        case 1: // '\001'
            return GROWTH_KIT;

        case 2: // '\002'
            return NEW_DEVICE_NOTIFICATION;

        case 3: // '\003'
            return WEB_APPLICATION;

        case 4: // '\004'
            return GOOGLE_TIPS;

        case 5: // '\005'
            return APPS_RECOMMENDATION;

        case 6: // '\006'
            return GROWTH_KIT_ANDROID;

        case 7: // '\007'
            return GROWTH_KIT_ANDROID_GMSCORE;

        case 8: // '\b'
            return ANDROID_NATIVE_NDN;

        case 9: // '\t'
            return ANDROID_MWEB;

        case 10: // '\n'
            return SECURITY_ADVISOR;

        case 11: // '\013'
            return MOBILE_WEB_APP;
        }
    }

    public static MOBILE_WEB_APP[] values()
    {
        return (MOBILE_WEB_APP[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        CLIENT_UNSPECIFIED = new <init>("CLIENT_UNSPECIFIED", 0, 0);
        GROWTH_KIT = new <init>("GROWTH_KIT", 1, 1);
        NEW_DEVICE_NOTIFICATION = new <init>("NEW_DEVICE_NOTIFICATION", 2, 2);
        WEB_APPLICATION = new <init>("WEB_APPLICATION", 3, 3);
        GOOGLE_TIPS = new <init>("GOOGLE_TIPS", 4, 4);
        APPS_RECOMMENDATION = new <init>("APPS_RECOMMENDATION", 5, 5);
        GROWTH_KIT_ANDROID = new <init>("GROWTH_KIT_ANDROID", 6, 6);
        GROWTH_KIT_ANDROID_GMSCORE = new <init>("GROWTH_KIT_ANDROID_GMSCORE", 7, 7);
        ANDROID_NATIVE_NDN = new <init>("ANDROID_NATIVE_NDN", 8, 8);
        ANDROID_MWEB = new <init>("ANDROID_MWEB", 9, 9);
        SECURITY_ADVISOR = new <init>("SECURITY_ADVISOR", 10, 10);
        MOBILE_WEB_APP = new <init>("MOBILE_WEB_APP", 11, 11);
        $VALUES = (new .VALUES[] {
            CLIENT_UNSPECIFIED, GROWTH_KIT, NEW_DEVICE_NOTIFICATION, WEB_APPLICATION, GOOGLE_TIPS, APPS_RECOMMENDATION, GROWTH_KIT_ANDROID, GROWTH_KIT_ANDROID_GMSCORE, ANDROID_NATIVE_NDN, ANDROID_MWEB, 
            SECURITY_ADVISOR, MOBILE_WEB_APP
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return ClientProto.Client.ClientId.forNumber(i) != null;
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
