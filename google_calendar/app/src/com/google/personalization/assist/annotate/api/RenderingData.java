// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate.api;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
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

public final class RenderingData extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class AssistanceType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final AssistanceType $VALUES[];
        public static final AssistanceType BOOK;
        public static final AssistanceType CALENDAR_EVENT;
        public static final AssistanceType CALL;
        public static final AssistanceType CONNECTOR_CONTACT;
        public static final AssistanceType CONNECTOR_LOCATION;
        public static final AssistanceType CONNECTOR_TIME;
        public static final AssistanceType CONTACT;
        public static final AssistanceType DATE_TIME;
        public static final AssistanceType DEADLINE;
        public static final AssistanceType DOCUMENT;
        public static final AssistanceType EMAIL;
        public static final AssistanceType FLIGHT;
        private static final AssistanceType GENERIC_ASSIST;
        private static final AssistanceType GROCERY;
        public static final AssistanceType HOTEL;
        public static final AssistanceType LOCAL;
        public static final AssistanceType LOCATION;
        public static final AssistanceType MAP_HOME;
        public static final AssistanceType MAP_WORK;
        public static final AssistanceType MOVIE;
        public static final AssistanceType NONE;
        public static final AssistanceType PAY;
        private static final AssistanceType PPT;
        public static final AssistanceType PREVIOUS_REMINDER;
        private static final AssistanceType PRODUCT;
        public static final AssistanceType RETURN_PRODUCT;
        private static final AssistanceType SERVICE;
        public static final AssistanceType SMS;
        public static final AssistanceType STOCK;
        public static final AssistanceType WEATHER;
        public static final AssistanceType WEBSITE;
        public static final AssistanceType YOUTUBE;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static AssistanceType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return NONE;

            case 24: // '\030'
                return DATE_TIME;

            case 25: // '\031'
                return MAP_HOME;

            case 26: // '\032'
                return MAP_WORK;

            case 21: // '\025'
                return PREVIOUS_REMINDER;

            case 23: // '\027'
                return BOOK;

            case 5: // '\005'
                return CALENDAR_EVENT;

            case 6: // '\006'
                return CALL;

            case 7: // '\007'
                return EMAIL;

            case 17: // '\021'
                return DEADLINE;

            case 8: // '\b'
                return DOCUMENT;

            case 9: // '\t'
                return FLIGHT;

            case 3: // '\003'
                return GROCERY;

            case 10: // '\n'
                return HOTEL;

            case 18: // '\022'
                return LOCAL;

            case 11: // '\013'
                return MOVIE;

            case 12: // '\f'
                return PAY;

            case 13: // '\r'
                return PRODUCT;

            case 14: // '\016'
                return RETURN_PRODUCT;

            case 22: // '\026'
                return SMS;

            case 19: // '\023'
                return STOCK;

            case 15: // '\017'
                return WEATHER;

            case 20: // '\024'
                return WEBSITE;

            case 16: // '\020'
                return YOUTUBE;

            case 27: // '\033'
                return CONNECTOR_CONTACT;

            case 28: // '\034'
                return CONNECTOR_LOCATION;

            case 29: // '\035'
                return CONNECTOR_TIME;

            case 2: // '\002'
                return CONTACT;

            case 30: // '\036'
                return LOCATION;

            case 31: // '\037'
                return PPT;

            case 1: // '\001'
                return GENERIC_ASSIST;

            case 4: // '\004'
                return SERVICE;
            }
        }

        public static AssistanceType[] values()
        {
            return (AssistanceType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            NONE = new AssistanceType("NONE", 0, 0);
            DATE_TIME = new AssistanceType("DATE_TIME", 1, 24);
            MAP_HOME = new AssistanceType("MAP_HOME", 2, 25);
            MAP_WORK = new AssistanceType("MAP_WORK", 3, 26);
            PREVIOUS_REMINDER = new AssistanceType("PREVIOUS_REMINDER", 4, 21);
            BOOK = new AssistanceType("BOOK", 5, 23);
            CALENDAR_EVENT = new AssistanceType("CALENDAR_EVENT", 6, 5);
            CALL = new AssistanceType("CALL", 7, 6);
            EMAIL = new AssistanceType("EMAIL", 8, 7);
            DEADLINE = new AssistanceType("DEADLINE", 9, 17);
            DOCUMENT = new AssistanceType("DOCUMENT", 10, 8);
            FLIGHT = new AssistanceType("FLIGHT", 11, 9);
            GROCERY = new AssistanceType("GROCERY", 12, 3);
            HOTEL = new AssistanceType("HOTEL", 13, 10);
            LOCAL = new AssistanceType("LOCAL", 14, 18);
            MOVIE = new AssistanceType("MOVIE", 15, 11);
            PAY = new AssistanceType("PAY", 16, 12);
            PRODUCT = new AssistanceType("PRODUCT", 17, 13);
            RETURN_PRODUCT = new AssistanceType("RETURN_PRODUCT", 18, 14);
            SMS = new AssistanceType("SMS", 19, 22);
            STOCK = new AssistanceType("STOCK", 20, 19);
            WEATHER = new AssistanceType("WEATHER", 21, 15);
            WEBSITE = new AssistanceType("WEBSITE", 22, 20);
            YOUTUBE = new AssistanceType("YOUTUBE", 23, 16);
            CONNECTOR_CONTACT = new AssistanceType("CONNECTOR_CONTACT", 24, 27);
            CONNECTOR_LOCATION = new AssistanceType("CONNECTOR_LOCATION", 25, 28);
            CONNECTOR_TIME = new AssistanceType("CONNECTOR_TIME", 26, 29);
            CONTACT = new AssistanceType("CONTACT", 27, 2);
            LOCATION = new AssistanceType("LOCATION", 28, 30);
            PPT = new AssistanceType("PPT", 29, 31);
            GENERIC_ASSIST = new AssistanceType("GENERIC_ASSIST", 30, 1);
            SERVICE = new AssistanceType("SERVICE", 31, 4);
            $VALUES = (new AssistanceType[] {
                NONE, DATE_TIME, MAP_HOME, MAP_WORK, PREVIOUS_REMINDER, BOOK, CALENDAR_EVENT, CALL, EMAIL, DEADLINE, 
                DOCUMENT, FLIGHT, GROCERY, HOTEL, LOCAL, MOVIE, PAY, PRODUCT, RETURN_PRODUCT, SMS, 
                STOCK, WEATHER, WEBSITE, YOUTUBE, CONNECTOR_CONTACT, CONNECTOR_LOCATION, CONNECTOR_TIME, CONTACT, LOCATION, PPT, 
                GENERIC_ASSIST, SERVICE
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return AssistanceType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private AssistanceType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(RenderingData.DEFAULT_INSTANCE);
        }
    }


    public static final RenderingData DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int assistanceType_;
    public int bitField0_;

    private RenderingData()
    {
    }

    public static RenderingData parseFrom(InputStream inputstream)
        throws IOException
    {
        Object obj = DEFAULT_INSTANCE;
        if (inputstream == null)
        {
            inputstream = Internal.EMPTY_BYTE_ARRAY;
            inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
        } else
        {
            inputstream = new com.google.protobuf.CodedInputStream.StreamDecoder(inputstream, 4096);
        }
        obj = GeneratedMessageLite.parsePartialFrom(((GeneratedMessageLite) (obj)), inputstream, ExtensionRegistryLite.getEmptyRegistry());
        if (obj != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                flag = Protobuf.INSTANCE.schemaFor(obj.getClass()).isInitialized(obj);
                if (flag1)
                {
                    int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                    if (flag)
                    {
                        inputstream = ((InputStream) (obj));
                    } else
                    {
                        inputstream = null;
                    }
                    ((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
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
        return (RenderingData)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 158
    //                   1 163
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 105
    //                   6 109;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new RenderingData();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = AssistanceType.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\001\000\001\003\003\001\000\000\000\003\f\002", new Object[] {
            "bitField0_", "assistanceType_", obj
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/personalization/assist/annotate/api/RenderingData;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/personalization/assist/annotate/api/RenderingData;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/personalization/assist/annotate/api/RenderingData;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new RenderingData();
        RenderingData renderingdata = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/personalization/assist/annotate/api/RenderingData, renderingdata);
    }
}
