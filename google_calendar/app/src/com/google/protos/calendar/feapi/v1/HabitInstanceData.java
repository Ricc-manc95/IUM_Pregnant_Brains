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

public final class HabitInstanceData extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(HabitInstanceData.DEFAULT_INSTANCE);
        }
    }

    public static final class Status extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Status $VALUES[];
        public static final Status ACTIVE;
        public static final Status COMPLETE;
        public static final Status DEFERRAL_REQUESTED;
        public static final Status UNDEFERRABLE;
        public static final Status UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Status forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return ACTIVE;

            case 2: // '\002'
                return DEFERRAL_REQUESTED;

            case 3: // '\003'
                return COMPLETE;

            case 4: // '\004'
                return UNDEFERRABLE;
            }
        }

        public static Status[] values()
        {
            return (Status[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new Status("UNKNOWN", 0, 0);
            ACTIVE = new Status("ACTIVE", 1, 1);
            DEFERRAL_REQUESTED = new Status("DEFERRAL_REQUESTED", 2, 2);
            COMPLETE = new Status("COMPLETE", 3, 3);
            UNDEFERRABLE = new Status("UNDEFERRABLE", 4, 4);
            $VALUES = (new Status[] {
                UNKNOWN, ACTIVE, DEFERRAL_REQUESTED, COMPLETE, UNDEFERRABLE
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Status.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Status(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final HabitInstanceData DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public boolean statusInferred_;
    public int status_;
    public int type_;

    private HabitInstanceData()
    {
    }

    public static HabitInstanceData parseFrom(InputStream inputstream)
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
        return (HabitInstanceData)generatedmessagelite;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 177
    //                   1 182
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 124
    //                   6 128;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new HabitInstanceData();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = Status.internalVerifier;
        com.google.protobuf.Internal.EnumVerifier enumverifier = HabitData.Type.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\003\000\001\001\003\003\000\000\000\001\f\000\002\f\002\003\007\001", new Object[] {
            "bitField0_", "status_", obj, "type_", enumverifier, "statusInferred_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/protos/calendar/feapi/v1/HabitInstanceData;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/protos/calendar/feapi/v1/HabitInstanceData;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/protos/calendar/feapi/v1/HabitInstanceData;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new HabitInstanceData();
        HabitInstanceData habitinstancedata = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/HabitInstanceData, habitinstancedata);
    }
}
