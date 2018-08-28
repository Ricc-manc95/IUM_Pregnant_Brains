// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.caribou.smartmail;

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

public final class GoTo extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(GoTo.DEFAULT_INSTANCE);
        }
    }

    public static final class Source extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Source $VALUES[];
        private static final Source POPULAR_LINKS;
        public static final Source UNKNOWN_SOURCE;
        private final int value;

        public static Source forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN_SOURCE;

            case 1: // '\001'
                return POPULAR_LINKS;
            }
        }

        public static Source[] values()
        {
            return (Source[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN_SOURCE = new Source("UNKNOWN_SOURCE", 0, 0);
            POPULAR_LINKS = new Source("POPULAR_LINKS", 1, 1);
            $VALUES = (new Source[] {
                UNKNOWN_SOURCE, POPULAR_LINKS
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Source.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

            new _cls2();
        }

        private Source(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class Type extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Type $VALUES[];
        public static final Type CHECK_IN;
        public static final Type EDIT;
        public static final Type LISTEN;
        public static final Type PAY;
        public static final Type PROVIDER;
        public static final Type SHARE;
        public static final Type STRUCTURED;
        public static final Type TRACK;
        public static final Type UNKNOWN;
        public static final Type VIDEO_MEETING;
        public static final Type VIEW;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static Type forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 1: // '\001'
                return UNKNOWN;

            case 2: // '\002'
                return CHECK_IN;

            case 3: // '\003'
                return EDIT;

            case 4: // '\004'
                return TRACK;

            case 5: // '\005'
                return VIEW;

            case 6: // '\006'
                return SHARE;

            case 7: // '\007'
                return LISTEN;

            case 8: // '\b'
                return STRUCTURED;

            case 9: // '\t'
                return VIDEO_MEETING;

            case 10: // '\n'
                return PROVIDER;

            case 11: // '\013'
                return PAY;
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
            UNKNOWN = new Type("UNKNOWN", 0, 1);
            CHECK_IN = new Type("CHECK_IN", 1, 2);
            EDIT = new Type("EDIT", 2, 3);
            TRACK = new Type("TRACK", 3, 4);
            VIEW = new Type("VIEW", 4, 5);
            SHARE = new Type("SHARE", 5, 6);
            LISTEN = new Type("LISTEN", 6, 7);
            STRUCTURED = new Type("STRUCTURED", 7, 8);
            VIDEO_MEETING = new Type("VIDEO_MEETING", 8, 9);
            PROVIDER = new Type("PROVIDER", 9, 10);
            PAY = new Type("PAY", 10, 11);
            $VALUES = (new Type[] {
                UNKNOWN, CHECK_IN, EDIT, TRACK, VIEW, SHARE, LISTEN, STRUCTURED, VIDEO_MEETING, PROVIDER, 
                PAY
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


    public static final GoTo DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public String text_;
    public int type_;
    public String uri_;

    private GoTo()
    {
        type_ = 1;
        uri_ = "";
        text_ = "";
    }

    public static GoTo parseFrom(InputStream inputstream)
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
        return (GoTo)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 168
    //                   1 173
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 115
    //                   6 119;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new GoTo();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = Type.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\003\000\001\001\004\003\000\000\000\001\f\000\003\b\001\004\b\002", new Object[] {
            "bitField0_", "type_", obj, "uri_", "text_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/caribou/smartmail/GoTo;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/caribou/smartmail/GoTo;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/caribou/smartmail/GoTo;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        new _cls1();
        DEFAULT_INSTANCE = new GoTo();
        GoTo goto1 = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/caribou/smartmail/GoTo, goto1);
    }

    private class _cls1
        implements com.google.protobuf.Internal.ListAdapter.Converter
    {

        public final Object convert(Object obj)
        {
            Source source = Source.forNumber(((Integer)obj).intValue());
            obj = source;
            if (source == null)
            {
                obj = Source.UNKNOWN_SOURCE;
            }
            return obj;
        }

        _cls1()
        {
        }
    }

}
