// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public final class Conference extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(Conference.DEFAULT_INSTANCE);
        }
    }

    public static final class EntryPointType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final EntryPointType $VALUES[];
        public static final EntryPointType MORE;
        public static final EntryPointType PHONE;
        public static final EntryPointType SIP;
        public static final EntryPointType STREAM;
        public static final EntryPointType UNKNOWN_ENTRY_POINT;
        public static final EntryPointType VIDEO;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static EntryPointType forNumber(int i)
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

        public static EntryPointType[] values()
        {
            return (EntryPointType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN_ENTRY_POINT = new EntryPointType("UNKNOWN_ENTRY_POINT", 0, 0);
            VIDEO = new EntryPointType("VIDEO", 1, 1);
            PHONE = new EntryPointType("PHONE", 2, 2);
            MORE = new EntryPointType("MORE", 3, 3);
            SIP = new EntryPointType("SIP", 4, 4);
            STREAM = new EntryPointType("STREAM", 5, 5);
            $VALUES = (new EntryPointType[] {
                UNKNOWN_ENTRY_POINT, VIDEO, PHONE, MORE, SIP, STREAM
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return EntryPointType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private EntryPointType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class Type extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Type $VALUES[];
        public static final Type EVENT_HANGOUT;
        public static final Type EVENT_NAMED_HANGOUT;
        public static final Type MEETING;
        public static final Type MEETING_PHONE_NUMBER;
        public static final Type MEETING_PHONE_NUMBERS_LINK;
        public static final Type UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Type forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return EVENT_HANGOUT;

            case 2: // '\002'
                return EVENT_NAMED_HANGOUT;

            case 3: // '\003'
                return MEETING;

            case 4: // '\004'
                return MEETING_PHONE_NUMBER;

            case 5: // '\005'
                return MEETING_PHONE_NUMBERS_LINK;
            }
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new Type("UNKNOWN", 0, 0);
            EVENT_HANGOUT = new Type("EVENT_HANGOUT", 1, 1);
            EVENT_NAMED_HANGOUT = new Type("EVENT_NAMED_HANGOUT", 2, 2);
            MEETING = new Type("MEETING", 3, 3);
            MEETING_PHONE_NUMBER = new Type("MEETING_PHONE_NUMBER", 4, 4);
            MEETING_PHONE_NUMBERS_LINK = new Type("MEETING_PHONE_NUMBERS_LINK", 5, 5);
            $VALUES = (new Type[] {
                UNKNOWN, EVENT_HANGOUT, EVENT_NAMED_HANGOUT, MEETING, MEETING_PHONE_NUMBER, MEETING_PHONE_NUMBERS_LINK
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Type.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Type(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final Conference DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public String accessCode_;
    public int bitField0_;
    public int entryPointType_;
    public boolean gatewayAccessEnabled_;
    public String label_;
    public String meetingCode_;
    public String name_;
    public String passCode_;
    public String passcode_;
    public String password_;
    public String pin_;
    public String regionCode_;
    public int type_;
    public String uri_;

    private Conference()
    {
        uri_ = "";
        label_ = "";
        name_ = "";
        pin_ = "";
        passCode_ = "";
        regionCode_ = "";
        accessCode_ = "";
        meetingCode_ = "";
        passcode_ = "";
        password_ = "";
    }

    public static Conference parseFrom(InputStream inputstream)
        throws IOException
    {
        GeneratedMessageLite generatedmessagelite = GeneratedMessageLite.parsePartialFrom(DEFAULT_INSTANCE, CodedInputStream.newInstance(inputstream), ExtensionRegistryLite.getEmptyRegistry());
        if (generatedmessagelite != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
            boolean flag;
            if (byte0 == 1)
            {
                flag = true;
            } else
            if (byte0 == 0)
            {
                flag = false;
            } else
            {
                flag = Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).isInitialized(generatedmessagelite);
                if (flag1)
                {
                    int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                    if (flag)
                    {
                        inputstream = generatedmessagelite;
                    } else
                    {
                        inputstream = null;
                    }
                    generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
                }
            }
            if (!flag)
            {
                inputstream = new InvalidProtocolBufferException((new UninitializedMessageException()).getMessage());
                if (inputstream == null)
                {
                    throw null;
                } else
                {
                    throw inputstream;
                }
            }
        }
        return (Conference)generatedmessagelite;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 237
    //                   1 242
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 184
    //                   6 188;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new Conference();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = Type.internalVerifier;
        com.google.protobuf.Internal.EnumVerifier enumverifier = EntryPointType.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\r\000\001\001\017\r\000\000\000\001\f\000\002\b\002\003\b\004\004\b\006\005\b\007\b\f\001\t\b\003\n\b\005\013\b\t\f\b\n\r\b\013\016\b\f\017\007\r", new Object[] {
            "bitField0_", "type_", obj, "uri_", "name_", "passCode_", "regionCode_", "entryPointType_", enumverifier, "label_", 
            "pin_", "accessCode_", "meetingCode_", "passcode_", "password_", "gatewayAccessEnabled_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/protos/calendar/feapi/v1/Conference;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/protos/calendar/feapi/v1/Conference;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/protos/calendar/feapi/v1/Conference;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new Conference();
        Conference conference = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Conference, conference);
    }
}
