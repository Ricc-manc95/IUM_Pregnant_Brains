// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.client.events.logging;

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

public final class RoomInfo extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(RoomInfo.DEFAULT_INSTANCE);
        }
    }

    public static final class UiEntryPointType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final UiEntryPointType $VALUES[];
        public static final UiEntryPointType LISTING;
        private static final UiEntryPointType SRB_LISTING;
        private static final UiEntryPointType SRB_LOCATION_CARD;
        private static final UiEntryPointType SRB_LOCATION_CARD_LISTING;
        private static final UiEntryPointType SRB_SUGGESTIONS;
        private static final UiEntryPointType STRUCTURED_ROOM_BOOKING;
        public static final UiEntryPointType SUGGESTIONS;
        public static final UiEntryPointType UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static UiEntryPointType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return SUGGESTIONS;

            case 2: // '\002'
                return LISTING;

            case 3: // '\003'
                return STRUCTURED_ROOM_BOOKING;

            case 4: // '\004'
                return SRB_LOCATION_CARD;

            case 7: // '\007'
                return SRB_LOCATION_CARD_LISTING;

            case 5: // '\005'
                return SRB_SUGGESTIONS;

            case 6: // '\006'
                return SRB_LISTING;
            }
        }

        public static UiEntryPointType[] values()
        {
            return (UiEntryPointType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new UiEntryPointType("UNKNOWN", 0, 0);
            SUGGESTIONS = new UiEntryPointType("SUGGESTIONS", 1, 1);
            LISTING = new UiEntryPointType("LISTING", 2, 2);
            STRUCTURED_ROOM_BOOKING = new UiEntryPointType("STRUCTURED_ROOM_BOOKING", 3, 3);
            SRB_LOCATION_CARD = new UiEntryPointType("SRB_LOCATION_CARD", 4, 4);
            SRB_LOCATION_CARD_LISTING = new UiEntryPointType("SRB_LOCATION_CARD_LISTING", 5, 7);
            SRB_SUGGESTIONS = new UiEntryPointType("SRB_SUGGESTIONS", 6, 5);
            SRB_LISTING = new UiEntryPointType("SRB_LISTING", 7, 6);
            $VALUES = (new UiEntryPointType[] {
                UNKNOWN, SUGGESTIONS, LISTING, STRUCTURED_ROOM_BOOKING, SRB_LOCATION_CARD, SRB_LOCATION_CARD_LISTING, SRB_SUGGESTIONS, SRB_LISTING
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return UiEntryPointType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private UiEntryPointType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final RoomInfo DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public String roomEmail_;
    public int uiEntryPointType_;

    private RoomInfo()
    {
        roomEmail_ = "";
    }

    public static RoomInfo parseFrom(InputStream inputstream)
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
        return (RoomInfo)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 163
    //                   1 168
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 110
    //                   6 114;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new RoomInfo();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = UiEntryPointType.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\000\000\001\b\000\002\f\001", new Object[] {
            "bitField0_", "roomEmail_", "uiEntryPointType_", obj
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/calendar/client/events/logging/RoomInfo;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/calendar/client/events/logging/RoomInfo;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/calendar/client/events/logging/RoomInfo;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new RoomInfo();
        RoomInfo roominfo = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/calendar/client/events/logging/RoomInfo, roominfo);
    }
}
