// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


public final class DeviceType extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final DeviceType $VALUES[];
    public static final DeviceType ANDROID_PHONE;
    public static final DeviceType ANDROID_TABLET;
    private static final DeviceType BACKEND_SERVICE;
    private static final DeviceType DEBUG_FRONTEND_WEB;
    private static final DeviceType IOS_PHONE;
    private static final DeviceType IOS_TABLET;
    private static final DeviceType WEB;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    public final int value;

    private DeviceType(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static DeviceType forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return BACKEND_SERVICE;

        case 1: // '\001'
            return IOS_PHONE;

        case 2: // '\002'
            return IOS_TABLET;

        case 3: // '\003'
            return ANDROID_PHONE;

        case 4: // '\004'
            return ANDROID_TABLET;

        case 5: // '\005'
            return WEB;

        case 6: // '\006'
            return DEBUG_FRONTEND_WEB;
        }
    }

    public static DeviceType[] values()
    {
        return (DeviceType[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        BACKEND_SERVICE = new DeviceType("BACKEND_SERVICE", 0, 0);
        IOS_PHONE = new DeviceType("IOS_PHONE", 1, 1);
        IOS_TABLET = new DeviceType("IOS_TABLET", 2, 2);
        ANDROID_PHONE = new DeviceType("ANDROID_PHONE", 3, 3);
        ANDROID_TABLET = new DeviceType("ANDROID_TABLET", 4, 4);
        WEB = new DeviceType("WEB", 5, 5);
        DEBUG_FRONTEND_WEB = new DeviceType("DEBUG_FRONTEND_WEB", 6, 6);
        $VALUES = (new DeviceType[] {
            BACKEND_SERVICE, IOS_PHONE, IOS_TABLET, ANDROID_PHONE, ANDROID_TABLET, WEB, DEBUG_FRONTEND_WEB
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return DeviceType.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
