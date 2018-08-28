// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.net.useragent;

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

// Referenced classes of package com.google.common.net.useragent:
//            UserAgentPb

public static final class memoizedIsInitialized extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(UserAgentPb.UserAgentProto.DEFAULT_INSTANCE);
        }
    }

    public static final class Family extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Family $VALUES[];
        private static final Family APPLE;
        private static final Family APPLEWEBKIT;
        private static final Family BLACKBERRY;
        private static final Family DOCOMO;
        private static final Family GECKO;
        private static final Family GOOGLE;
        private static final Family KHTML;
        private static final Family KOREAN;
        private static final Family MICROSOFT;
        private static final Family MSIE;
        private static final Family NETFRONT;
        private static final Family NOKIA;
        private static final Family OBIGO;
        private static final Family OPENWAVE;
        private static final Family OPERA;
        private static final Family OTHER;
        private static final Family POLARIS;
        private static final Family SEMC;
        private static final Family SMIT;
        private static final Family TELECA;
        private static final Family USER_DEFINED;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static Family forNumber(int i)
        {
            switch (i)
            {
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
            case 9: // '\t'
            default:
                return null;

            case 0: // '\0'
                return USER_DEFINED;

            case 1: // '\001'
                return MSIE;

            case 2: // '\002'
                return GECKO;

            case 3: // '\003'
                return APPLEWEBKIT;

            case 4: // '\004'
                return OPERA;

            case 5: // '\005'
                return KHTML;

            case 10: // '\n'
                return OTHER;

            case 11: // '\013'
                return APPLE;

            case 12: // '\f'
                return BLACKBERRY;

            case 13: // '\r'
                return DOCOMO;

            case 14: // '\016'
                return GOOGLE;

            case 15: // '\017'
                return OPENWAVE;

            case 16: // '\020'
                return POLARIS;

            case 17: // '\021'
                return OBIGO;

            case 18: // '\022'
                return TELECA;

            case 19: // '\023'
                return MICROSOFT;

            case 20: // '\024'
                return NOKIA;

            case 21: // '\025'
                return NETFRONT;

            case 22: // '\026'
                return SEMC;

            case 23: // '\027'
                return SMIT;

            case 24: // '\030'
                return KOREAN;
            }
        }

        public static Family[] values()
        {
            return (Family[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            USER_DEFINED = new Family("USER_DEFINED", 0, 0);
            MSIE = new Family("MSIE", 1, 1);
            GECKO = new Family("GECKO", 2, 2);
            APPLEWEBKIT = new Family("APPLEWEBKIT", 3, 3);
            OPERA = new Family("OPERA", 4, 4);
            KHTML = new Family("KHTML", 5, 5);
            OTHER = new Family("OTHER", 6, 10);
            APPLE = new Family("APPLE", 7, 11);
            BLACKBERRY = new Family("BLACKBERRY", 8, 12);
            DOCOMO = new Family("DOCOMO", 9, 13);
            GOOGLE = new Family("GOOGLE", 10, 14);
            OPENWAVE = new Family("OPENWAVE", 11, 15);
            POLARIS = new Family("POLARIS", 12, 16);
            OBIGO = new Family("OBIGO", 13, 17);
            TELECA = new Family("TELECA", 14, 18);
            MICROSOFT = new Family("MICROSOFT", 15, 19);
            NOKIA = new Family("NOKIA", 16, 20);
            NETFRONT = new Family("NETFRONT", 17, 21);
            SEMC = new Family("SEMC", 18, 22);
            SMIT = new Family("SMIT", 19, 23);
            KOREAN = new Family("KOREAN", 20, 24);
            $VALUES = (new Family[] {
                USER_DEFINED, MSIE, GECKO, APPLEWEBKIT, OPERA, KHTML, OTHER, APPLE, BLACKBERRY, DOCOMO, 
                GOOGLE, OPENWAVE, POLARIS, OBIGO, TELECA, MICROSOFT, NOKIA, NETFRONT, SEMC, SMIT, 
                KOREAN
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Family.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Family(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final  DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int family_;
    private byte memoizedIsInitialized;

    public static Family._cls2 parseFrom(InputStream inputstream)
        throws IOException
    {
        Object obj = DEFAULT_INSTANCE;
        if (inputstream == null)
        {
            inputstream = Internal.EMPTY_BYTE_ARRAY;
            inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
        } else
        {
            inputstream = new com.google.protobuf.der(inputstream, 4096);
        }
        obj = GeneratedMessageLite.parsePartialFrom(((GeneratedMessageLite) (obj)), inputstream, ExtensionRegistryLite.getEmptyRegistry());
        if (obj != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.MEMOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                    int i = android.support.v4.content.MEMOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
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
        return (e)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        boolean flag = false;
        i - 1;
        JVM INSTR tableswitch 0 6: default 48
    //                   0 162
    //                   1 170
    //                   2 74
    //                   3 56
    //                   4 66
    //                   5 109
    //                   6 113;
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
        obj = Family.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\001\000\001\001\001\001\000\000\001\001\u050C\0", new Object[] {
            "bitField0_", "family_", obj
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/common/net/useragent/UserAgentPb$UserAgentProto;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.tInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/common/net/useragent/UserAgentPb$UserAgentProto;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/common/net/useragent/UserAgentPb$UserAgentProto;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf(memoizedIsInitialized);
_L3:
        if (obj == null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 1;
        }
        memoizedIsInitialized = (byte)i;
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new <init>();
        memoizedIsInitialized memoizedisinitialized = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/common/net/useragent/UserAgentPb$UserAgentProto, memoizedisinitialized);
    }

    private Family.value()
    {
        memoizedIsInitialized = 2;
    }
}
