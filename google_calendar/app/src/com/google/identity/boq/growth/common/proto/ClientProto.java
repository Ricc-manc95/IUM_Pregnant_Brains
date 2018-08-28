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

public final class ClientProto
{
    public static final class Client extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final Client DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public AppProto.ApplicationIdentifier applicationIdentifier_;
        public int bitField0_;
        public int clientId_;
        public int version_;

        public static Client parseFrom(InputStream inputstream)
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
            return (Client)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 168
        //                       1 173
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 115
        //                       6 119;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new Client();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = ClientId.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\003\000\001\001\003\003\000\000\000\001\f\000\002\004\001\003\t\002", new Object[] {
                "bitField0_", "clientId_", obj, "version_", "applicationIdentifier_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/boq/growth/common/proto/ClientProto$Client;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/boq/growth/common/proto/ClientProto$Client;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/boq/growth/common/proto/ClientProto$Client;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new Client();
            Client client = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/boq/growth/common/proto/ClientProto$Client, client);
        }

        private Client()
        {
        }
    }

    public static final class Client.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Client.Builder()
        {
            super(Client.DEFAULT_INSTANCE);
        }
    }

    public static final class Client.ClientId extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Client.ClientId $VALUES[];
        private static final Client.ClientId ANDROID_MWEB;
        private static final Client.ClientId ANDROID_NATIVE_NDN;
        private static final Client.ClientId APPS_RECOMMENDATION;
        private static final Client.ClientId CLIENT_UNSPECIFIED;
        private static final Client.ClientId GOOGLE_TIPS;
        private static final Client.ClientId GROWTH_KIT;
        public static final Client.ClientId GROWTH_KIT_ANDROID;
        private static final Client.ClientId GROWTH_KIT_ANDROID_GMSCORE;
        private static final Client.ClientId MOBILE_WEB_APP;
        private static final Client.ClientId NEW_DEVICE_NOTIFICATION;
        private static final Client.ClientId SECURITY_ADVISOR;
        private static final Client.ClientId WEB_APPLICATION;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Client.ClientId forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return CLIENT_UNSPECIFIED;

            case 1: // '\001'
                return GROWTH_KIT;

            case 2: // '\002'
                return NEW_DEVICE_NOTIFICATION;

            case 3: // '\003'
                return WEB_APPLICATION;

            case 4: // '\004'
                return GOOGLE_TIPS;

            case 5: // '\005'
                return APPS_RECOMMENDATION;

            case 6: // '\006'
                return GROWTH_KIT_ANDROID;

            case 7: // '\007'
                return GROWTH_KIT_ANDROID_GMSCORE;

            case 8: // '\b'
                return ANDROID_NATIVE_NDN;

            case 9: // '\t'
                return ANDROID_MWEB;

            case 10: // '\n'
                return SECURITY_ADVISOR;

            case 11: // '\013'
                return MOBILE_WEB_APP;
            }
        }

        public static Client.ClientId[] values()
        {
            return (Client.ClientId[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            CLIENT_UNSPECIFIED = new Client.ClientId("CLIENT_UNSPECIFIED", 0, 0);
            GROWTH_KIT = new Client.ClientId("GROWTH_KIT", 1, 1);
            NEW_DEVICE_NOTIFICATION = new Client.ClientId("NEW_DEVICE_NOTIFICATION", 2, 2);
            WEB_APPLICATION = new Client.ClientId("WEB_APPLICATION", 3, 3);
            GOOGLE_TIPS = new Client.ClientId("GOOGLE_TIPS", 4, 4);
            APPS_RECOMMENDATION = new Client.ClientId("APPS_RECOMMENDATION", 5, 5);
            GROWTH_KIT_ANDROID = new Client.ClientId("GROWTH_KIT_ANDROID", 6, 6);
            GROWTH_KIT_ANDROID_GMSCORE = new Client.ClientId("GROWTH_KIT_ANDROID_GMSCORE", 7, 7);
            ANDROID_NATIVE_NDN = new Client.ClientId("ANDROID_NATIVE_NDN", 8, 8);
            ANDROID_MWEB = new Client.ClientId("ANDROID_MWEB", 9, 9);
            SECURITY_ADVISOR = new Client.ClientId("SECURITY_ADVISOR", 10, 10);
            MOBILE_WEB_APP = new Client.ClientId("MOBILE_WEB_APP", 11, 11);
            $VALUES = (new Client.ClientId[] {
                CLIENT_UNSPECIFIED, GROWTH_KIT, NEW_DEVICE_NOTIFICATION, WEB_APPLICATION, GOOGLE_TIPS, APPS_RECOMMENDATION, GROWTH_KIT_ANDROID, GROWTH_KIT_ANDROID_GMSCORE, ANDROID_NATIVE_NDN, ANDROID_MWEB, 
                SECURITY_ADVISOR, MOBILE_WEB_APP
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Client.ClientId.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Client.ClientId(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

}
