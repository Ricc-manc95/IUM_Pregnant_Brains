// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.boq.growth.common.proto;

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

// Referenced classes of package com.google.identity.boq.growth.common.proto:
//            DeviceInfoProto

public static final class osVersion_ extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class BondHardwareInfo extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final BondHardwareInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String deviceBrandName_;
        public String deviceMarketingName_;
        public String deviceModel_;

        public static BondHardwareInfo parseFrom(InputStream inputstream)
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
            return (BondHardwareInfo)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 160
        //                       1 165
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 107
        //                       6 111;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new BondHardwareInfo();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\003\000\001\001\003\003\000\000\000\001\b\000\002\b\001\003\b\002", new Object[] {
                "bitField0_", "deviceBrandName_", "deviceMarketingName_", "deviceModel_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/boq/growth/common/proto/DeviceInfoProto$DeviceInfo$BondHardwareInfo;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/boq/growth/common/proto/DeviceInfoProto$DeviceInfo$BondHardwareInfo;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/boq/growth/common/proto/DeviceInfoProto$DeviceInfo$BondHardwareInfo;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new BondHardwareInfo();
            BondHardwareInfo bondhardwareinfo = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/boq/growth/common/proto/DeviceInfoProto$DeviceInfo$BondHardwareInfo, bondhardwareinfo);
        }

        private BondHardwareInfo()
        {
            deviceBrandName_ = "";
            deviceMarketingName_ = "";
            deviceModel_ = "";
        }
    }

    public static final class BondHardwareInfo.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        BondHardwareInfo.Builder()
        {
            super(BondHardwareInfo.DEFAULT_INSTANCE);
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(DeviceInfoProto.DeviceInfo.DEFAULT_INSTANCE);
        }
    }

    public static final class IOSHardwareInfo extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final IOSHardwareInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;

        public static IOSHardwareInfo parseFrom(InputStream inputstream)
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
            return (IOSHardwareInfo)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 140
        //                       1 145
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 87
        //                       6 91;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new IOSHardwareInfo();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\0", new Object[0]);
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/boq/growth/common/proto/DeviceInfoProt