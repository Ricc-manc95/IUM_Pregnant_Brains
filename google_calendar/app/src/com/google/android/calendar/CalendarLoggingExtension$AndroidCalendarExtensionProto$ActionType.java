// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;


// Referenced classes of package com.google.android.calendar:
//            CalendarLoggingExtension

public static final class value extends Enum
    implements com.google.protobuf.ionType
{

    private static final forNumber $VALUES[];
    public static final forNumber ACTIVITY_CREATE;
    public static final forNumber APPLICATION_VIEW;
    public static final forNumber CHANGE_EVENT;
    public static final forNumber CHANGE_GOAL;
    public static final forNumber CHANGE_REMINDER;
    public static final forNumber CREATE_EVENT;
    public static final forNumber CREATE_GOAL;
    public static final forNumber CREATE_REMINDER;
    public static final forNumber RESPONDED_EVENT;
    private static final forNumber UNKNOWN;
    public static final com.google.protobuf.ionType internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN;

        case 1: // '\001'
            return CREATE_EVENT;

        case 2: // '\002'
            return CHANGE_EVENT;

        case 3: // '\003'
            return RESPONDED_EVENT;

        case 4: // '\004'
            return APPLICATION_VIEW;

        case 5: // '\005'
            return CREATE_REMINDER;

        case 6: // '\006'
            return CHANGE_REMINDER;

        case 7: // '\007'
            return CREATE_GOAL;

        case 8: // '\b'
            return CHANGE_GOAL;

        case 9: // '\t'
            return ACTIVITY_CREATE;
        }
    }

    public static ACTIVITY_CREATE[] values()
    {
        return (ACTIVITY_CREATE[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        CREATE_EVENT = new <init>("CREATE_EVENT", 1, 1);
        CHANGE_EVENT = new <init>("CHANGE_EVENT", 2, 2);
        RESPONDED_EVENT = new <init>("RESPONDED_EVENT", 3, 3);
        APPLICATION_VIEW = new <init>("APPLICATION_VIEW", 4, 4);
        CREATE_REMINDER = new <init>("CREATE_REMINDER", 5, 5);
        CHANGE_REMINDER = new <init>("CHANGE_REMINDER", 6, 6);
        CREATE_GOAL = new <init>("CREATE_GOAL", 7, 7);
        CHANGE_GOAL = new <init>("CHANGE_GOAL", 8, 8);
        ACTIVITY_CREATE = new <init>("ACTIVITY_CREATE", 9, 9);
        $VALUES = (new .VALUES[] {
            UNKNOWN, CREATE_EVENT, CHANGE_EVENT, RESPONDED_EVENT, APPLICATION_VIEW, CREATE_REMINDER, CHANGE_REMINDER, CREATE_GOAL, CHANGE_GOAL, ACTIVITY_CREATE
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.forNumber(i) != null;
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
