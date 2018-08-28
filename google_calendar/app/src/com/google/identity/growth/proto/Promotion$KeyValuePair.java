// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;

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

// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class key_ extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(Promotion.KeyValuePair.DEFAULT_INSTANCE);
        }
    }

    public static final class ClientValue extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final ClientValue $VALUES[];
        public static final ClientValue CLIENT_VALUE_ACCOUNT_NAME;
        public static final ClientValue CLIENT_VALUE_UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static ClientValue forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return CLIENT_VALUE_UNKNOWN;

            case 1: // '\001'
                return CLIENT_VALUE_ACCOUNT_NAME;
            }
        }

        public static ClientValue[] values()
        {
            return (ClientValue[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            CLIENT_VALUE_UNKNOWN = new ClientValue("CLIENT_VALUE_UNKNOWN", 0, 0);
            CLIENT_VALUE_ACCOUNT_NAME = new ClientValue("CLIENT_VALUE_ACCOUNT_NAME", 1, 1);
            $VALUES = (new ClientValue[] {
                CLIENT_VALUE_UNKNOWN, CLIENT_VALUE_ACCOUNT_NAME
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return ClientValue.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private ClientValue(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final Map DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public String key_;
    public int valueTypesCase_;
    public Object valueTypes_;

    public static ClientValue._cls2 parseFrom(InputStream inputstream)
        throws IOException
    {
        Object obj = DEFAULT_INSTANCE;
        if (inputstream == null)
        {
            inputstream = Internal.EMPTY_BYTE_ARRAY;
            inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
        } else
        {
            inputstream = new com.google.protobuf.Decoder(inputstream, 4096);
        }
        obj = GeneratedMessageLite.parsePartialFrom(((GeneratedMessageLite) (obj)), inputstream, ExtensionRegistryLite.getEmptyRegistry());
        if (obj != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.GET_MEMOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                    int i = android.support.v4.content.SET_MEMOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
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
        return (sage)obj;
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
        obj = new <init>();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = ClientValue.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\003\001\001\001\003\003\000\000\000\001\b\000\002;\000\003?\0", new Object[] {
            "valueTypes_", "valueTypesCase_", "bitField0_", "key_", obj
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/identity/growth/proto/Promotion$KeyValuePair;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.faultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/identity/growth/proto/Promotion$KeyValuePair;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/identity/growth/proto/Promotion$KeyValuePair;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new <init>();
        PARSER parser = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$KeyValuePair, parser);
    }

    private ClientValue.value()
    {
        valueTypesCase_ = 0;
        key_ = "";
    }
}
