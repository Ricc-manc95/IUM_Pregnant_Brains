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

public final class ConferenceSolution extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(ConferenceSolution.DEFAULT_INSTANCE);
        }
    }

    public static final class Key extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final Key DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public AddOnId addOnId_;
        public int bitField0_;
        public int type_;

        public static Key parseFrom(InputStream inputstream)
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
            return (Key)generatedmessagelite;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 163
        //                       1 168
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 110
        //                       6 114;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new Key();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = Type.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\000\000\001\f\000\002\t\001", new Object[] {
                "bitField0_", "type_", obj, "addOnId_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/calendar/feapi/v1/ConferenceSolution$Key;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/calendar/feapi/v1/ConferenceSolution$Key;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/calendar/feapi/v1/ConferenceSolution$Key;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new Key();
            Key key = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/ConferenceSolution$Key, key);
        }

        private Key()
        {
        }
    }

    public static final class Key.AddOnId extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final Key.AddOnId DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String deploymentId_;
        public String solutionId_;

        public static Key.AddOnId parseFrom(InputStream inputstream)
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
            return (Key.AddOnId)generatedmessagelite;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 155
        //                       1 160
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 102
        //                       6 106;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new Key.AddOnId();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\002\003\002\000\000\000\002\b\001\003\b\002", new Object[] {
                "bitField0_", "deploymentId_", "solutionId_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/calendar/feapi/v1/ConferenceSolution$Key$AddOnId;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/calendar/feapi/v1/ConferenceSolution$Key$AddOnId;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/calendar/feapi/v1/ConferenceSolution$Key$AddOnId;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new Key.AddOnId();
            Key.AddOnId addonid = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/ConferenceSolution$Key$AddOnId, addonid);
        }

        private Key.AddOnId()
        {
            deploymentId_ = "";
            solutionId_ = "";
        }
    }

    public static final class Key.AddOnId.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Key.AddOnId.Builder()
        {
            super(Key.AddOnId.DEFAULT_INSTANCE);
        }
    }

    public static final class Key.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Key.Builder()
        {
            super(Key.DEFAULT_INSTANCE);
        }
    }

    public static final class Key.Type extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Key.Type $VALUES[];
        public static final Key.Type ADD_ON;
        public static final Key.Type EVENT_HANGOUT;
        public static final Key.Type EVENT_NAMED_HANGOUT;
        public static final Key.Type HANGOUTS_MEET;
        public static final Key.Type UNKNOWN_CONFERENCE_SOLUTION;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Key.Type forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN_CONFERENCE_SOLUTION;

            case 1: // '\001'
                return EVENT_HANGOUT;

            case 2: // '\002'
                return EVENT_NAMED_HANGOUT;

            case 3: // '\003'
                return HANGOUTS_MEET;

            case 4: // '\004'
                return ADD_ON;
            }
        }

        public static Key.Type[] values()
        {
            return (Key.Type[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN_CONFERENCE_SOLUTION = new Key.Type("UNKNOWN_CONFERENCE_SOLUTION", 0, 0);
            EVENT_HANGOUT = new Key.Type("EVENT_HANGOUT", 1, 1);
            EVENT_NAMED_HANGOUT = new Key.Type("EVENT_NAMED_HANGOUT", 2, 2);
            HANGOUTS_MEET = new Key.Type("HANGOUTS_MEET", 3, 3);
            ADD_ON = new Key.Type("ADD_ON", 4, 4);
            $VALUES = (new Key.Type[] {
                UNKNOWN_CONFERENCE_SOLUTION, EVENT_HANGOUT, EVENT_NAMED_HANGOUT, HANGOUTS_MEET, ADD_ON
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Key.Type.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Key.Type(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final ConferenceSolution DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public String iconUri_;
    public Key key_;
    public String name_;

    private ConferenceSolution()
    {
        name_ = "";
        iconUri_ = "";
    }

    public static ConferenceSolution parseFrom(InputStream inputstream)
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
        return (ConferenceSolution)generatedmessagelite;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 160
    //                   1 165
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 107
    //                   6 111;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new ConferenceSolution();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\003\000\001\001\003\003\000\000\000\001\t\000\002\b\001\003\b\002", new Object[] {
            "bitField0_", "key_", "name_", "iconUri_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/protos/calendar/feapi/v1/ConferenceSolution;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/protos/calendar/feapi/v1/ConferenceSolution;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/protos/calendar/feapi/v1/ConferenceSolution;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new ConferenceSolution();
        ConferenceSolution conferencesolution = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/ConferenceSolution, conferencesolution);
    }
}