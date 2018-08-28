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
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public final class RoomFeature extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(RoomFeature.DEFAULT_INSTANCE);
        }
    }

    public static final class DisplayProminence extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final DisplayProminence $VALUES[];
        public static final DisplayProminence DEFAULT_PROMINENCE;
        public static final DisplayProminence PROMINENT;
        public static final DisplayProminence UNRECOGNIZED;
        private final int value;

        public static DisplayProminence forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return DEFAULT_PROMINENCE;

            case 1: // '\001'
                return PROMINENT;
            }
        }

        public static DisplayProminence[] values()
        {
            return (DisplayProminence[])$VALUES.clone();
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
            DEFAULT_PROMINENCE = new DisplayProminence("DEFAULT_PROMINENCE", 0, 0);
            PROMINENT = new DisplayProminence("PROMINENT", 1, 1);
            UNRECOGNIZED = new DisplayProminence("UNRECOGNIZED", 2, -1);
            $VALUES = (new DisplayProminence[] {
                DEFAULT_PROMINENCE, PROMINENT, UNRECOGNIZED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return DisplayProminence.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

            new _cls2();
        }

        private DisplayProminence(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class EquipmentType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final EquipmentType $VALUES[];
        public static final EquipmentType AUDIO;
        public static final EquipmentType UNRECOGNIZED;
        public static final EquipmentType UNSPECIFIED_EQUIPMENT_TYPE;
        public static final EquipmentType VIDEO;
        private final int value;

        public static EquipmentType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNSPECIFIED_EQUIPMENT_TYPE;

            case 1: // '\001'
                return VIDEO;

            case 2: // '\002'
                return AUDIO;
            }
        }

        public static EquipmentType[] values()
        {
            return (EquipmentType[])$VALUES.clone();
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
            UNSPECIFIED_EQUIPMENT_TYPE = new EquipmentType("UNSPECIFIED_EQUIPMENT_TYPE", 0, 0);
            VIDEO = new EquipmentType("VIDEO", 1, 1);
            AUDIO = new EquipmentType("AUDIO", 2, 2);
            UNRECOGNIZED = new EquipmentType("UNRECOGNIZED", 3, -1);
            $VALUES = (new EquipmentType[] {
                UNSPECIFIED_EQUIPMENT_TYPE, VIDEO, AUDIO, UNRECOGNIZED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return EquipmentType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

            new _cls2();
        }

        private EquipmentType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final RoomFeature DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int displayProminence_;
    public int equipmentType_;
    public String name_;

    private RoomFeature()
    {
        name_ = "";
    }

    public static RoomFeature parseFrom(InputStream inputstream)
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
        return (RoomFeature)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 155
    //                   1 160
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 102
    //                   6 106;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new RoomFeature();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        return new RawMessageInfo(DEFAULT_INSTANCE, "\000\003\000\000\001\003\003\000\000\000\001\u0208\002\f\003\f", new Object[] {
            "name_", "equipmentType_", "displayProminence_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/calendar/suggest/v2/RoomFeature;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/calendar/suggest/v2/RoomFeature;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/calendar/suggest/v2/RoomFeature;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new RoomFeature();
        RoomFeature roomfeature = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/calendar/suggest/v2/RoomFeature, roomfeature);
    }
}
