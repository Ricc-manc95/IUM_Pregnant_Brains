// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.boq.growth.common.proto;


public final class IosPermissionType extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final IosPermissionType $VALUES[];
    private static final IosPermissionType BLUETOOTH;
    private static final IosPermissionType CALENDARS;
    private static final IosPermissionType CAMERA;
    private static final IosPermissionType CONTACTS;
    private static final IosPermissionType FACE_ID;
    private static final IosPermissionType HOMEKIT;
    public static final IosPermissionType INVALID_PERMISSION_TYPE;
    private static final IosPermissionType LOCATION_ALWAYS;
    private static final IosPermissionType LOCATION_WHEN_IN_USE;
    private static final IosPermissionType MEDIA_LIBRARY;
    private static final IosPermissionType MICROPHONE;
    private static final IosPermissionType MOTION;
    private static final IosPermissionType NFC;
    private static final IosPermissionType NOTIFICATION_ALERT;
    private static final IosPermissionType NOTIFICATION_BADGE;
    private static final IosPermissionType NOTIFICATION_CAR_PLAY;
    private static final IosPermissionType NOTIFICATION_SOUND;
    private static final IosPermissionType PHOTOS;
    private static final IosPermissionType PHOTOS_ADD_ONLY;
    private static final IosPermissionType REMINDERS;
    private static final IosPermissionType SPEECH_RECOGNITION;
    private static final IosPermissionType UNRECOGNIZED;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    private final int value;

    private IosPermissionType(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static IosPermissionType forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return INVALID_PERMISSION_TYPE;

        case 1: // '\001'
            return NFC;

        case 2: // '\002'
            return BLUETOOTH;

        case 3: // '\003'
            return MICROPHONE;

        case 4: // '\004'
            return SPEECH_RECOGNITION;

        case 5: // '\005'
            return CAMERA;

        case 6: // '\006'
            return MOTION;

        case 7: // '\007'
            return LOCATION_ALWAYS;

        case 8: // '\b'
            return LOCATION_WHEN_IN_USE;

        case 9: // '\t'
            return CONTACTS;

        case 10: // '\n'
            return CALENDARS;

        case 11: // '\013'
            return REMINDERS;

        case 12: // '\f'
            return PHOTOS;

        case 13: // '\r'
            return PHOTOS_ADD_ONLY;

        case 14: // '\016'
            return MEDIA_LIBRARY;

        case 15: // '\017'
            return HOMEKIT;

        case 16: // '\020'
            return FACE_ID;

        case 17: // '\021'
            return NOTIFICATION_BADGE;

        case 18: // '\022'
            return NOTIFICATION_SOUND;

        case 19: // '\023'
            return NOTIFICATION_ALERT;

        case 20: // '\024'
            return NOTIFICATION_CAR_PLAY;
        }
    }

    public static IosPermissionType[] values()
    {
        return (IosPermissionType[])$VALUES.clone();
    }

    public final int getNumber()
    {
        if (this == UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        } else
        {
            return value;
        }
    }

    static 
    {
        INVALID_PERMISSION_TYPE = new IosPermissionType("INVALID_PERMISSION_TYPE", 0, 0);
        NFC = new IosPermissionType("NFC", 1, 1);
        BLUETOOTH = new IosPermissionType("BLUETOOTH", 2, 2);
        MICROPHONE = new IosPermissionType("MICROPHONE", 3, 3);
        SPEECH_RECOGNITION = new IosPermissionType("SPEECH_RECOGNITION", 4, 4);
        CAMERA = new IosPermissionType("CAMERA", 5, 5);
        MOTION = new IosPermissionType("MOTION", 6, 6);
        LOCATION_ALWAYS = new IosPermissionType("LOCATION_ALWAYS", 7, 7);
        LOCATION_WHEN_IN_USE = new IosPermissionType("LOCATION_WHEN_IN_USE", 8, 8);
        CONTACTS = new IosPermissionType("CONTACTS", 9, 9);
        CALENDARS = new IosPermissionType("CALENDARS", 10, 10);
        REMINDERS = new IosPermissionType("REMINDERS", 11, 11);
        PHOTOS = new IosPermissionType("PHOTOS", 12, 12);
        PHOTOS_ADD_ONLY = new IosPermissionType("PHOTOS_ADD_ONLY", 13, 13);
        MEDIA_LIBRARY = new IosPermissionType("MEDIA_LIBRARY", 14, 14);
        HOMEKIT = new IosPermissionType("HOMEKIT", 15, 15);
        FACE_ID = new IosPermissionType("FACE_ID", 16, 16);
        NOTIFICATION_BADGE = new IosPermissionType("NOTIFICATION_BADGE", 17, 17);
        NOTIFICATION_SOUND = new IosPermissionType("NOTIFICATION_SOUND", 18, 18);
        NOTIFICATION_ALERT = new IosPermissionType("NOTIFICATION_ALERT", 19, 19);
        NOTIFICATION_CAR_PLAY = new IosPermissionType("NOTIFICATION_CAR_PLAY", 20, 20);
        UNRECOGNIZED = new IosPermissionType("UNRECOGNIZED", 21, -1);
        $VALUES = (new IosPermissionType[] {
            INVALID_PERMISSION_TYPE, NFC, BLUETOOTH, MICROPHONE, SPEECH_RECOGNITION, CAMERA, MOTION, LOCATION_ALWAYS, LOCATION_WHEN_IN_USE, CONTACTS, 
            CALENDARS, REMINDERS, PHOTOS, PHOTOS_ADD_ONLY, MEDIA_LIBRARY, HOMEKIT, FACE_ID, NOTIFICATION_BADGE, NOTIFICATION_SOUND, NOTIFICATION_ALERT, 
            NOTIFICATION_CAR_PLAY, UNRECOGNIZED
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return IosPermissionType.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
