// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import com.google.protobuf.ProtobufArrayList;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

// Referenced classes of package com.google.calendar.suggest.v2:
//            Event, Attendee

public final class Explanation extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(Explanation.DEFAULT_INSTANCE);
        }
    }

    public static final class ConflictType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final ConflictType $VALUES[];
        public static final ConflictType MIGHT_BE_BUSY;
        public static final ConflictType MIGHT_NOT_BE_AVAILABLE;
        private static final ConflictType NO_SEAT_FOUND;
        public static final ConflictType UNRECOGNIZED;
        public final int value;

        public static ConflictType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return MIGHT_NOT_BE_AVAILABLE;

            case 1: // '\001'
                return MIGHT_BE_BUSY;

            case 2: // '\002'
                return NO_SEAT_FOUND;
            }
        }

        public static ConflictType[] values()
        {
            return (ConflictType[])$VALUES.clone();
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
            MIGHT_NOT_BE_AVAILABLE = new ConflictType("MIGHT_NOT_BE_AVAILABLE", 0, 0);
            MIGHT_BE_BUSY = new ConflictType("MIGHT_BE_BUSY", 1, 1);
            NO_SEAT_FOUND = new ConflictType("NO_SEAT_FOUND", 2, 2);
            UNRECOGNIZED = new ConflictType("UNRECOGNIZED", 3, -1);
            $VALUES = (new ConflictType[] {
                MIGHT_NOT_BE_AVAILABLE, MIGHT_BE_BUSY, NO_SEAT_FOUND, UNRECOGNIZED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return ConflictType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

            new _cls2();
        }

        private ConflictType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final Explanation DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Attendee attendee_;
    public int bitField0_;
    public int conflictType_;
    public com.google.protobuf.Internal.ProtobufList conflictingEvents_;

    private Explanation()
    {
        conflictingEvents_ = ProtobufArrayList.EMPTY_LIST;
    }

    public static Explanation parseFrom(InputStream inputstream)
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
        return (Explanation)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 165
    //                   1 170
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 112
    //                   6 116;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new Explanation();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        return new RawMessageInfo(DEFAULT_INSTANCE, "\000\003\000\001\001\003\003\000\001\000\001\t\002\f\003\033", new Object[] {
            "bitField0_", "attendee_", "conflictType_", "conflictingEvents_", com/google/calendar/suggest/v2/Event
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/calendar/suggest/v2/Explanation;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/calendar/suggest/v2/Explanation;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/calendar/suggest/v2/Explanation;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new Explanation();
        Explanation explanation = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/calendar/suggest/v2/Explanation, explanation);
    }
}
