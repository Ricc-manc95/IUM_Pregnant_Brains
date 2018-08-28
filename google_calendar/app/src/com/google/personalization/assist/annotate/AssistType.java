// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


public final class AssistType extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final AssistType $VALUES[];
    public static final AssistType CALL;
    public static final AssistType CREATE_EVENT;
    public static final AssistType EMAIL_ASSIST;
    private static final AssistType PICK_CONTACT;
    private static final AssistType RECURRING_SNOOZE;
    private static final AssistType REMINDER_ASSIST;
    private static final AssistType SET_ALIAS;
    private static final AssistType SET_DATE;
    private static final AssistType SMS;
    private static final AssistType SNOOZE;
    public static final AssistType UNKNOWN_ASSIST_TYPE;
    public static final AssistType URL_ASSIST;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    public final int value;

    private AssistType(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static AssistType forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_ASSIST_TYPE;

        case 1: // '\001'
            return CALL;

        case 2: // '\002'
            return CREATE_EVENT;

        case 3: // '\003'
            return EMAIL_ASSIST;

        case 4: // '\004'
            return PICK_CONTACT;

        case 5: // '\005'
            return RECURRING_SNOOZE;

        case 11: // '\013'
            return REMINDER_ASSIST;

        case 6: // '\006'
            return SET_ALIAS;

        case 7: // '\007'
            return SET_DATE;

        case 8: // '\b'
            return SMS;

        case 9: // '\t'
            return SNOOZE;

        case 10: // '\n'
            return URL_ASSIST;
        }
    }

    public static AssistType[] values()
    {
        return (AssistType[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN_ASSIST_TYPE = new AssistType("UNKNOWN_ASSIST_TYPE", 0, 0);
        CALL = new AssistType("CALL", 1, 1);
        CREATE_EVENT = new AssistType("CREATE_EVENT", 2, 2);
        EMAIL_ASSIST = new AssistType("EMAIL_ASSIST", 3, 3);
        PICK_CONTACT = new AssistType("PICK_CONTACT", 4, 4);
        RECURRING_SNOOZE = new AssistType("RECURRING_SNOOZE", 5, 5);
        REMINDER_ASSIST = new AssistType("REMINDER_ASSIST", 6, 11);
        SET_ALIAS = new AssistType("SET_ALIAS", 7, 6);
        SET_DATE = new AssistType("SET_DATE", 8, 7);
        SMS = new AssistType("SMS", 9, 8);
        SNOOZE = new AssistType("SNOOZE", 10, 9);
        URL_ASSIST = new AssistType("URL_ASSIST", 11, 10);
        $VALUES = (new AssistType[] {
            UNKNOWN_ASSIST_TYPE, CALL, CREATE_EVENT, EMAIL_ASSIST, PICK_CONTACT, RECURRING_SNOOZE, REMINDER_ASSIST, SET_ALIAS, SET_DATE, SMS, 
            SNOOZE, URL_ASSIST
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return AssistType.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
