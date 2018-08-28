// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;

import com.google.identity.boq.growth.common.proto.ConnectivityState;
import com.google.identity.boq.growth.common.proto.IosPermissionType;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.IntArrayList;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import com.google.protobuf.ProtobufArrayList;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import com.google.type.Color;
import com.google.type.DayOfWeek;
import com.google.type.TimeOfDay;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public final class Promotion
{
    public static final class AndroidIntentTarget extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final AndroidIntentTarget DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public String action_;
        public int bitField0_;
        public String className_;
        public String data_;
        public com.google.protobuf.Internal.ProtobufList extraData_;
        public int flags_;
        public int intentType_;
        public String packageName_;

        public static AndroidIntentTarget parseFrom(InputStream inputstream)
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
            return (AndroidIntentTarget)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 198
        //                       1 203
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 145
        //                       6 149;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new AndroidIntentTarget();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = IntentType.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\007\000\001\001\007\007\000\001\000\001\b\000\002\b\001\003\b\002\004\b\003\005\f\004\006\033\007\004\005", new Object[] {
                "bitField0_", "packageName_", "action_", "className_", "data_", "intentType_", obj, "extraData_", com/google/identity/growth/proto/Promotion$KeyValuePair, "flags_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$AndroidIntentTarget;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$AndroidIntentTarget;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$AndroidIntentTarget;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new AndroidIntentTarget();
            AndroidIntentTarget androidintenttarget = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$AndroidIntentTarget, androidintenttarget);
        }

        private AndroidIntentTarget()
        {
            packageName_ = "";
            action_ = "";
            className_ = "";
            data_ = "";
            extraData_ = ProtobufArrayList.EMPTY_LIST;
        }
    }

    public static final class AndroidIntentTarget.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        AndroidIntentTarget.Builder()
        {
            super(AndroidIntentTarget.DEFAULT_INSTANCE);
        }
    }

    public static final class AndroidIntentTarget.IntentType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final AndroidIntentTarget.IntentType $VALUES[];
        public static final AndroidIntentTarget.IntentType ACTIVITY;
        private static final AndroidIntentTarget.IntentType ACTIVITY_WITH_RESULT;
        public static final AndroidIntentTarget.IntentType BROADCAST;
        public static final AndroidIntentTarget.IntentType SERVICE;
        public static final AndroidIntentTarget.IntentType UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static AndroidIntentTarget.IntentType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return ACTIVITY;

            case 2: // '\002'
                return SERVICE;

            case 3: // '\003'
                return BROADCAST;

            case 4: // '\004'
                return ACTIVITY_WITH_RESULT;
            }
        }

        public static AndroidIntentTarget.IntentType[] values()
        {
            return (AndroidIntentTarget.IntentType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new AndroidIntentTarget.IntentType("UNKNOWN", 0, 0);
            ACTIVITY = new AndroidIntentTarget.IntentType("ACTIVITY", 1, 1);
            SERVICE = new AndroidIntentTarget.IntentType("SERVICE", 2, 2);
            BROADCAST = new AndroidIntentTarget.IntentType("BROADCAST", 3, 3);
            ACTIVITY_WITH_RESULT = new AndroidIntentTarget.IntentType("ACTIVITY_WITH_RESULT", 4, 4);
            $VALUES = (new AndroidIntentTarget.IntentType[] {
                UNKNOWN, ACTIVITY, SERVICE, BROADCAST, ACTIVITY_WITH_RESULT
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return AndroidIntentTarget.IntentType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private AndroidIntentTarget.IntentType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class AndroidNotificationUi extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final AndroidNotificationUi DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String title_;

        public static AndroidNotificationUi parseFrom(InputStream inputstream)
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
            return (AndroidNotificationUi)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 150
        //                       1 155
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 97
        //                       6 101;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new AndroidNotificationUi();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\001\000\001\001\001\001\000\000\000\001\b\0", new Object[] {
                "bitField0_", "title_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$AndroidNotificationUi;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$AndroidNotificationUi;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$AndroidNotificationUi;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new AndroidNotificationUi();
            AndroidNotificationUi androidnotificationui = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$AndroidNotificationUi, androidnotificationui);
        }

        private AndroidNotificationUi()
        {
            title_ = "";
        }
    }

    public static final class AndroidNotificationUi.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        AndroidNotificationUi.Builder()
        {
            super(AndroidNotificationUi.DEFAULT_INSTANCE);
        }
    }

    public static final class AndroidSystemEvent extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final AndroidSystemEvent DEFAULT_INSTANCE;
        private static volatile Parser PARSER;

        public static AndroidSystemEvent parseFrom(InputStream inputstream)
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
            return (AndroidSystemEvent)obj;
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
            obj = new AndroidSystemEvent();
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
            com/google/identity/growth/proto/Promotion$AndroidSystemEvent;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$AndroidSystemEvent;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$AndroidSystemEvent;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new AndroidSystemEvent();
            AndroidSystemEvent androidsystemevent = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$AndroidSystemEvent, androidsystemevent);
        }

        private AndroidSystemEvent()
        {
        }
    }

    public static final class AndroidSystemEvent.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        AndroidSystemEvent.Builder()
        {
            super(AndroidSystemEvent.DEFAULT_INSTANCE);
        }
    }

    public static final class AppStatePredicate extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final AppStatePredicate DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int satisfiedConditionsCase_;
        public Object satisfiedConditions_;
        public String stateId_;

        public static AppStatePredicate parseFrom(InputStream inputstream)
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
            return (AppStatePredicate)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 171
        //                       1 176
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 118
        //                       6 122;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new AppStatePredicate();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\003\001\001\001\003\003\000\000\000\001\b\000\002<\000\003<\0", new Object[] {
                "satisfiedConditions_", "satisfiedConditionsCase_", "bitField0_", "stateId_", com/google/identity/growth/proto/Promotion$IntRange, com/google/identity/growth/proto/Promotion$StringList
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$AppStatePredicate;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$AppStatePredicate;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$AppStatePredicate;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new AppStatePredicate();
            AppStatePredicate appstatepredicate = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$AppStatePredicate, appstatepredicate);
        }

        private AppStatePredicate()
        {
            satisfiedConditionsCase_ = 0;
            stateId_ = "";
        }
    }

    public static final class AppStatePredicate.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        AppStatePredicate.Builder()
        {
            super(AppStatePredicate.DEFAULT_INSTANCE);
        }
    }

    public static final class ClearcutEvent extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final ClearcutEvent DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String bundleIdentifier_;
        public int eventCode_;
        public int logSource_;

        public static ClearcutEvent parseFrom(InputStream inputstream)
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
            return (ClearcutEvent)obj;
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
            obj = new ClearcutEvent();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\003\000\001\001\003\003\000\000\000\001\004\000\002\004\001\003\b\002", new Object[] {
                "bitField0_", "logSource_", "eventCode_", "bundleIdentifier_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$ClearcutEvent;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$ClearcutEvent;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$ClearcutEvent;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new ClearcutEvent();
            ClearcutEvent clearcutevent = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$ClearcutEvent, clearcutevent);
        }

        private ClearcutEvent()
        {
            bundleIdentifier_ = "";
        }
    }

    public static final class ClearcutEvent.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        ClearcutEvent.Builder()
        {
            super(ClearcutEvent.DEFAULT_INSTANCE);
        }
    }

    public static final class ClientSideTargetingRule extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final ClientSideTargetingRule DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public com.google.protobuf.Internal.ProtobufList clause_;

        public static ClientSideTargetingRule parseFrom(InputStream inputstream)
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
            return (ClientSideTargetingRule)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 150
        //                       1 155
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 97
        //                       6 101;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new ClientSideTargetingRule();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\001\000\000\001\001\001\000\001\000\001\033", new Object[] {
                "clause_", com/google/identity/growth/proto/Promotion$ClientSideTargetingRule$TargetingClause
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$ClientSideTargetingRule;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$ClientSideTargetingRule;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$ClientSideTargetingRule;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new ClientSideTargetingRule();
            ClientSideTargetingRule clientsidetargetingrule = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$ClientSideTargetingRule, clientsidetargetingrule);
        }

        private ClientSideTargetingRule()
        {
            clause_ = ProtobufArrayList.EMPTY_LIST;
        }
    }

    public static final class ClientSideTargetingRule.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        ClientSideTargetingRule.Builder()
        {
            super(ClientSideTargetingRule.DEFAULT_INSTANCE);
        }
    }

    public static final class ClientSideTargetingRule.TargetingClause extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final ClientSideTargetingRule.TargetingClause DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public com.google.protobuf.Internal.ProtobufList term_;

        public static ClientSideTargetingRule.TargetingClause parseFrom(InputStream inputstream)
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
            return (ClientSideTargetingRule.TargetingClause)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 150
        //                       1 155
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 97
        //                       6 101;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new ClientSideTargetingRule.TargetingClause();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\001\000\000\001\001\001\000\001\000\001\033", new Object[] {
                "term_", com/google/identity/growth/proto/Promotion$ClientSideTargetingRule$TargetingTerm
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$ClientSideTargetingRule$TargetingClause;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$ClientSideTargetingRule$TargetingClause;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$ClientSideTargetingRule$TargetingClause;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new ClientSideTargetingRule.TargetingClause();
            ClientSideTargetingRule.TargetingClause targetingclause = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$ClientSideTargetingRule$TargetingClause, targetingclause);
        }

        private ClientSideTargetingRule.TargetingClause()
        {
            term_ = ProtobufArrayList.EMPTY_LIST;
        }
    }

    public static final class ClientSideTargetingRule.TargetingClause.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        ClientSideTargetingRule.TargetingClause.Builder()
        {
            super(ClientSideTargetingRule.TargetingClause.DEFAULT_INSTANCE);
        }
    }

    public static final class ClientSideTargetingRule.TargetingTerm extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final ClientSideTargetingRule.TargetingTerm DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public boolean negate_;
        public int predicateCase_;
        public Object predicate_;

        public static ClientSideTargetingRule.TargetingTerm parseFrom(InputStream inputstream)
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
            return (ClientSideTargetingRule.TargetingTerm)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 177
        //                       1 182
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 124
        //                       6 128;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new ClientSideTargetingRule.TargetingTerm();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\004\001\001\001\004\004\000\000\000\001\007\000\002<\000\003<\000\004<\0", new Object[] {
                "predicate_", "predicateCase_", "bitField0_", "negate_", com/google/identity/growth/proto/Promotion$EventCountPredicate, com/google/identity/growth/proto/Promotion$AppStatePredicate, com/google/identity/growth/proto/Promotion$IosPermissionStatePredicate
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$ClientSideTargetingRule$TargetingTerm;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$ClientSideTargetingRule$TargetingTerm;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$ClientSideTargetingRule$TargetingTerm;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new ClientSideTargetingRule.TargetingTerm();
            ClientSideTargetingRule.TargetingTerm targetingterm = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$ClientSideTargetingRule$TargetingTerm, targetingterm);
        }

        private ClientSideTargetingRule.TargetingTerm()
        {
            predicateCase_ = 0;
        }
    }

    public static final class ClientSideTargetingRule.TargetingTerm.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        ClientSideTargetingRule.TargetingTerm.Builder()
        {
            super(ClientSideTargetingRule.TargetingTerm.DEFAULT_INSTANCE);
        }
    }

    public static final class ClientSideTargetingRule.TargetingTerm.PredicateCase extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final ClientSideTargetingRule.TargetingTerm.PredicateCase $VALUES[];
        public static final ClientSideTargetingRule.TargetingTerm.PredicateCase APP_STATE;
        public static final ClientSideTargetingRule.TargetingTerm.PredicateCase EVENT_COUNT;
        private static final ClientSideTargetingRule.TargetingTerm.PredicateCase PERMISSION;
        private static final ClientSideTargetingRule.TargetingTerm.PredicateCase PREDICATE_NOT_SET;
        private final int value;

        public static ClientSideTargetingRule.TargetingTerm.PredicateCase forNumber(int i)
        {
            switch (i)
            {
            case 1: // '\001'
            default:
                return null;

            case 2: // '\002'
                return EVENT_COUNT;

            case 3: // '\003'
                return APP_STATE;

            case 4: // '\004'
                return PERMISSION;

            case 0: // '\0'
                return PREDICATE_NOT_SET;
            }
        }

        public static ClientSideTargetingRule.TargetingTerm.PredicateCase[] values()
        {
            return (ClientSideTargetingRule.TargetingTerm.PredicateCase[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            EVENT_COUNT = new ClientSideTargetingRule.TargetingTerm.PredicateCase("EVENT_COUNT", 0, 2);
            APP_STATE = new ClientSideTargetingRule.TargetingTerm.PredicateCase("APP_STATE", 1, 3);
            PERMISSION = new ClientSideTargetingRule.TargetingTerm.PredicateCase("PERMISSION", 2, 4);
            PREDICATE_NOT_SET = new ClientSideTargetingRule.TargetingTerm.PredicateCase("PREDICATE_NOT_SET", 3, 0);
            $VALUES = (new ClientSideTargetingRule.TargetingTerm.PredicateCase[] {
                EVENT_COUNT, APP_STATE, PERMISSION, PREDICATE_NOT_SET
            });
        }

        private ClientSideTargetingRule.TargetingTerm.PredicateCase(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class ClientTargetingEvent extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final ClientTargetingEvent DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int eventCase_;
        public Object event_;

        public static ClientTargetingEvent parseFrom(InputStream inputstream)
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
            return (ClientTargetingEvent)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 165
        //                       1 170
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 112
        //                       6 116;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new ClientTargetingEvent();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\001\001\001\002\002\000\000\000\001<\000\002<\0", new Object[] {
                "event_", "eventCase_", "bitField0_", com/google/identity/growth/proto/Promotion$ClearcutEvent, com/google/identity/growth/proto/Promotion$VisualElementEvent
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$ClientTargetingEvent;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$ClientTargetingEvent;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$ClientTargetingEvent;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new ClientTargetingEvent();
            ClientTargetingEvent clienttargetingevent = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$ClientTargetingEvent, clienttargetingevent);
        }

        private ClientTargetingEvent()
        {
            eventCase_ = 0;
        }
    }

    public static final class ClientTargetingEvent.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        ClientTargetingEvent.Builder()
        {
            super(ClientTargetingEvent.DEFAULT_INSTANCE);
        }
    }

    public static final class ClientTargetingEvent.EventCase extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final ClientTargetingEvent.EventCase $VALUES[];
        public static final ClientTargetingEvent.EventCase CLEARCUT_EVENT;
        private static final ClientTargetingEvent.EventCase EVENT_NOT_SET;
        public static final ClientTargetingEvent.EventCase VE_EVENT;
        private final int value;

        public static ClientTargetingEvent.EventCase forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 1: // '\001'
                return CLEARCUT_EVENT;

            case 2: // '\002'
                return VE_EVENT;

            case 0: // '\0'
                return EVENT_NOT_SET;
            }
        }

        public static ClientTargetingEvent.EventCase[] values()
        {
            return (ClientTargetingEvent.EventCase[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            CLEARCUT_EVENT = new ClientTargetingEvent.EventCase("CLEARCUT_EVENT", 0, 1);
            VE_EVENT = new ClientTargetingEvent.EventCase("VE_EVENT", 1, 2);
            EVENT_NOT_SET = new ClientTargetingEvent.EventCase("EVENT_NOT_SET", 2, 0);
            $VALUES = (new ClientTargetingEvent.EventCase[] {
                CLEARCUT_EVENT, VE_EVENT, EVENT_NOT_SET
            });
        }

        private ClientTargetingEvent.EventCase(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class EventCountPredicate extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final EventCountPredicate DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public com.google.protobuf.Internal.ProtobufList clientEvent_;
        public int eventCase_;
        public Object event_;
        public int maxValueExclusive_;
        public int minValueInclusive_;

        public static EventCountPredicate parseFrom(InputStream inputstream)
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
            return (EventCountPredicate)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 183
        //                       1 188
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 130
        //                       6 134;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new EventCountPredicate();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\004\001\001\001\004\004\000\001\000\001<\000\002\004\001\003\004\002\004\033", new Object[] {
                "event_", "eventCase_", "bitField0_", com/google/identity/growth/proto/Promotion$ClearcutEvent, "minValueInclusive_", "maxValueExclusive_", "clientEvent_", com/google/identity/growth/proto/Promotion$ClientTargetingEvent
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$EventCountPredicate;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$EventCountPredicate;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$EventCountPredicate;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new EventCountPredicate();
            EventCountPredicate eventcountpredicate = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$EventCountPredicate, eventcountpredicate);
        }

        private EventCountPredicate()
        {
            eventCase_ = 0;
            clientEvent_ = ProtobufArrayList.EMPTY_LIST;
        }
    }

    public static final class EventCountPredicate.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        EventCountPredicate.Builder()
        {
            super(EventCountPredicate.DEFAULT_INSTANCE);
        }
    }

    public static final class GeneralPromptUi extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final GeneralPromptUi DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String bodyText_;
        public String headlineText_;
        public String imageUrl_;
        public com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier promotedApp_;
        public int style_;
        public com.google.protobuf.Internal.ProtobufList userAction_;

        public static GeneralPromptUi parseFrom(InputStream inputstream)
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
            return (GeneralPromptUi)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 192
        //                       1 197
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 139
        //                       6 143;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new GeneralPromptUi();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = Style.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\006\000\001\001\007\006\000\001\000\001\b\000\002\b\001\003\033\005\b\006\006\f\t\007\t\007", new Object[] {
                "bitField0_", "headlineText_", "bodyText_", "userAction_", com/google/identity/growth/proto/Promotion$GeneralPromptUi$Action, "imageUrl_", "style_", obj, "promotedApp_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$GeneralPromptUi;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$GeneralPromptUi;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$GeneralPromptUi;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new GeneralPromptUi();
            GeneralPromptUi generalpromptui = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$GeneralPromptUi, generalpromptui);
        }

        private GeneralPromptUi()
        {
            headlineText_ = "";
            bodyText_ = "";
            userAction_ = ProtobufArrayList.EMPTY_LIST;
            imageUrl_ = "";
        }
    }

    public static final class GeneralPromptUi.Action extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final GeneralPromptUi.Action DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int actionType_;
        public int bitField0_;
        public String buttonText_;
        public int targetCase_;
        public Object target_;
        public Color textColor_;

        public static GeneralPromptUi.Action parseFrom(InputStream inputstream)
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
            return (GeneralPromptUi.Action)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 186
        //                       1 191
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 133
        //                       6 137;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new GeneralPromptUi.Action();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = ActionType.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\005\001\001\001\b\005\000\000\000\001\f\000\002\b\001\003;\000\006\t\002\b<\0", new Object[] {
                "target_", "targetCase_", "bitField0_", "actionType_", obj, "buttonText_", "textColor_", com/google/identity/growth/proto/Promotion$AndroidIntentTarget
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$GeneralPromptUi$Action;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$GeneralPromptUi$Action;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$GeneralPromptUi$Action;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new GeneralPromptUi.Action();
            GeneralPromptUi.Action action = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$GeneralPromptUi$Action, action);
        }

        private GeneralPromptUi.Action()
        {
            targetCase_ = 0;
            buttonText_ = "";
        }
    }

    public static final class GeneralPromptUi.Action.ActionType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final GeneralPromptUi.Action.ActionType $VALUES[];
        public static final GeneralPromptUi.Action.ActionType ACTION_ACKNOWLEDGE;
        public static final GeneralPromptUi.Action.ActionType ACTION_DISMISS;
        public static final GeneralPromptUi.Action.ActionType ACTION_NEGATIVE;
        public static final GeneralPromptUi.Action.ActionType ACTION_POSITIVE;
        public static final GeneralPromptUi.Action.ActionType ACTION_UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static GeneralPromptUi.Action.ActionType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return ACTION_UNKNOWN;

            case 1: // '\001'
                return ACTION_POSITIVE;

            case 2: // '\002'
                return ACTION_NEGATIVE;

            case 3: // '\003'
                return ACTION_DISMISS;

            case 4: // '\004'
                return ACTION_ACKNOWLEDGE;
            }
        }

        public static GeneralPromptUi.Action.ActionType[] values()
        {
            return (GeneralPromptUi.Action.ActionType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            ACTION_UNKNOWN = new GeneralPromptUi.Action.ActionType("ACTION_UNKNOWN", 0, 0);
            ACTION_POSITIVE = new GeneralPromptUi.Action.ActionType("ACTION_POSITIVE", 1, 1);
            ACTION_NEGATIVE = new GeneralPromptUi.Action.ActionType("ACTION_NEGATIVE", 2, 2);
            ACTION_DISMISS = new GeneralPromptUi.Action.ActionType("ACTION_DISMISS", 3, 3);
            ACTION_ACKNOWLEDGE = new GeneralPromptUi.Action.ActionType("ACTION_ACKNOWLEDGE", 4, 4);
            $VALUES = (new GeneralPromptUi.Action.ActionType[] {
                ACTION_UNKNOWN, ACTION_POSITIVE, ACTION_NEGATIVE, ACTION_DISMISS, ACTION_ACKNOWLEDGE
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return GeneralPromptUi.Action.ActionType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private GeneralPromptUi.Action.ActionType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class GeneralPromptUi.Action.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        GeneralPromptUi.Action.Builder()
        {
            super(GeneralPromptUi.Action.DEFAULT_INSTANCE);
        }
    }

    public static final class GeneralPromptUi.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        GeneralPromptUi.Builder()
        {
            super(GeneralPromptUi.DEFAULT_INSTANCE);
        }
    }

    public static final class GeneralPromptUi.Style extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final GeneralPromptUi.Style $VALUES[];
        public static final GeneralPromptUi.Style BLOCKING_BOTTOMSHEET;
        private static final GeneralPromptUi.Style BLUE_HEADER_DIALOG;
        public static final GeneralPromptUi.Style MATERIAL_ALERT_DIALOG;
        private static final GeneralPromptUi.Style NON_BLOCKING_BOTTOMSHEET;
        private static final GeneralPromptUi.Style RED_HEADER_DIALOG;
        private static final GeneralPromptUi.Style SYSTEM_DIALOG;
        public static final GeneralPromptUi.Style UNKNOWN_STYLE;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static GeneralPromptUi.Style forNumber(int i)
        {
            switch (i)
            {
            case 1: // '\001'
            case 7: // '\007'
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN_STYLE;

            case 2: // '\002'
                return RED_HEADER_DIALOG;

            case 3: // '\003'
                return SYSTEM_DIALOG;

            case 4: // '\004'
                return MATERIAL_ALERT_DIALOG;

            case 5: // '\005'
                return BLUE_HEADER_DIALOG;

            case 6: // '\006'
                return BLOCKING_BOTTOMSHEET;

            case 8: // '\b'
                return NON_BLOCKING_BOTTOMSHEET;
            }
        }

        public static GeneralPromptUi.Style[] values()
        {
            return (GeneralPromptUi.Style[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN_STYLE = new GeneralPromptUi.Style("UNKNOWN_STYLE", 0, 0);
            RED_HEADER_DIALOG = new GeneralPromptUi.Style("RED_HEADER_DIALOG", 1, 2);
            SYSTEM_DIALOG = new GeneralPromptUi.Style("SYSTEM_DIALOG", 2, 3);
            MATERIAL_ALERT_DIALOG = new GeneralPromptUi.Style("MATERIAL_ALERT_DIALOG", 3, 4);
            BLUE_HEADER_DIALOG = new GeneralPromptUi.Style("BLUE_HEADER_DIALOG", 4, 5);
            BLOCKING_BOTTOMSHEET = new GeneralPromptUi.Style("BLOCKING_BOTTOMSHEET", 5, 6);
            NON_BLOCKING_BOTTOMSHEET = new GeneralPromptUi.Style("NON_BLOCKING_BOTTOMSHEET", 6, 8);
            $VALUES = (new GeneralPromptUi.Style[] {
                UNKNOWN_STYLE, RED_HEADER_DIALOG, SYSTEM_DIALOG, MATERIAL_ALERT_DIALOG, BLUE_HEADER_DIALOG, BLOCKING_BOTTOMSHEET, NON_BLOCKING_BOTTOMSHEET
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return GeneralPromptUi.Style.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private GeneralPromptUi.Style(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class InstalledAppCondition extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final InstalledAppCondition DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier appId_;
        public int bitField0_;
        public int requiredInstallStatus_;

        public static InstalledAppCondition parseFrom(InputStream inputstream)
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
            return (InstalledAppCondition)obj;
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
            obj = new InstalledAppCondition();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = InstallStatus.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\000\000\001\t\000\002\f\001", new Object[] {
                "bitField0_", "appId_", "requiredInstallStatus_", obj
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$InstalledAppCondition;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$InstalledAppCondition;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$InstalledAppCondition;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new InstalledAppCondition();
            InstalledAppCondition installedappcondition = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$InstalledAppCondition, installedappcondition);
        }

        private InstalledAppCondition()
        {
        }
    }

    public static final class InstalledAppCondition.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        InstalledAppCondition.Builder()
        {
            super(InstalledAppCondition.DEFAULT_INSTANCE);
        }
    }

    public static final class InstalledAppCondition.InstallStatus extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final InstalledAppCondition.InstallStatus $VALUES[];
        public static final InstalledAppCondition.InstallStatus INSTALLED;
        public static final InstalledAppCondition.InstallStatus NOT_INSTALLED;
        public static final InstalledAppCondition.InstallStatus UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static InstalledAppCondition.InstallStatus forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return NOT_INSTALLED;

            case 2: // '\002'
                return INSTALLED;
            }
        }

        public static InstalledAppCondition.InstallStatus[] values()
        {
            return (InstalledAppCondition.InstallStatus[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new InstalledAppCondition.InstallStatus("UNKNOWN", 0, 0);
            NOT_INSTALLED = new InstalledAppCondition.InstallStatus("NOT_INSTALLED", 1, 1);
            INSTALLED = new InstalledAppCondition.InstallStatus("INSTALLED", 2, 2);
            $VALUES = (new InstalledAppCondition.InstallStatus[] {
                UNKNOWN, NOT_INSTALLED, INSTALLED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return InstalledAppCondition.InstallStatus.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private InstalledAppCondition.InstallStatus(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class IntRange extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final IntRange DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int maxValueExclusive_;
        public int minValueInclusive_;

        public static IntRange parseFrom(InputStream inputstream)
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
            return (IntRange)obj;
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
            obj = new IntRange();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\000\000\001\004\000\002\004\001", new Object[] {
                "bitField0_", "minValueInclusive_", "maxValueExclusive_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$IntRange;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$IntRange;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$IntRange;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new IntRange();
            IntRange intrange = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$IntRange, intrange);
        }

        private IntRange()
        {
        }
    }

    public static final class IntRange.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        IntRange.Builder()
        {
            super(IntRange.DEFAULT_INSTANCE);
        }
    }

    public static final class IosPermissionStatePredicate extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final IosPermissionStatePredicate DEFAULT_INSTANCE;
        private static volatile Parser PARSER;

        public static IosPermissionStatePredicate parseFrom(InputStream inputstream)
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
            return (IosPermissionStatePredicate)obj;
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
            obj = new IosPermissionStatePredicate();
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
            com/google/identity/growth/proto/Promotion$IosPermissionStatePredicate;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$IosPermissionStatePredicate;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$IosPermissionStatePredicate;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new IosPermissionStatePredicate();
            IosPermissionStatePredicate iospermissionstatepredicate = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$IosPermissionStatePredicate, iospermissionstatepredicate);
        }

        private IosPermissionStatePredicate()
        {
        }
    }

    public static final class IosPermissionStatePredicate.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        IosPermissionStatePredicate.Builder()
        {
            super(IosPermissionStatePredicate.DEFAULT_INSTANCE);
        }
    }

    public static final class KeyValuePair extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final KeyValuePair DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String key_;
        public int valueTypesCase_;
        public Object valueTypes_;

        public static KeyValuePair parseFrom(InputStream inputstream)
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
            return (KeyValuePair)obj;
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
            obj = new KeyValuePair();
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
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
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
            DEFAULT_INSTANCE = new KeyValuePair();
            KeyValuePair keyvaluepair = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$KeyValuePair, keyvaluepair);
        }

        private KeyValuePair()
        {
            valueTypesCase_ = 0;
            key_ = "";
        }
    }

    public static final class KeyValuePair.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        KeyValuePair.Builder()
        {
            super(KeyValuePair.DEFAULT_INSTANCE);
        }
    }

    public static final class KeyValuePair.ClientValue extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final KeyValuePair.ClientValue $VALUES[];
        public static final KeyValuePair.ClientValue CLIENT_VALUE_ACCOUNT_NAME;
        public static final KeyValuePair.ClientValue CLIENT_VALUE_UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static KeyValuePair.ClientValue forNumber(int i)
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

        public static KeyValuePair.ClientValue[] values()
        {
            return (KeyValuePair.ClientValue[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            CLIENT_VALUE_UNKNOWN = new KeyValuePair.ClientValue("CLIENT_VALUE_UNKNOWN", 0, 0);
            CLIENT_VALUE_ACCOUNT_NAME = new KeyValuePair.ClientValue("CLIENT_VALUE_ACCOUNT_NAME", 1, 1);
            $VALUES = (new KeyValuePair.ClientValue[] {
                CLIENT_VALUE_UNKNOWN, CLIENT_VALUE_ACCOUNT_NAME
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return KeyValuePair.ClientValue.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private KeyValuePair.ClientValue(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class NetworkCondition extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final NetworkCondition DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int connectivity_;

        public static NetworkCondition parseFrom(InputStream inputstream)
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
            return (NetworkCondition)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 158
        //                       1 163
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 105
        //                       6 109;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new NetworkCondition();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = ConnectivityState.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\001\000\001\001\001\001\000\000\000\001\f\0", new Object[] {
                "bitField0_", "connectivity_", obj
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$NetworkCondition;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$NetworkCondition;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$NetworkCondition;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new NetworkCondition();
            NetworkCondition networkcondition = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$NetworkCondition, networkcondition);
        }

        private NetworkCondition()
        {
        }
    }

    public static final class NetworkCondition.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        NetworkCondition.Builder()
        {
            super(NetworkCondition.DEFAULT_INSTANCE);
        }
    }

    public static final class PermissionPromptUi extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final PermissionPromptUi DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int permissionForPlatformCase_;
        public Object permissionForPlatform_;
        public GeneralPromptUi warmupPromptUi_;

        public static PermissionPromptUi parseFrom(InputStream inputstream)
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
            return (PermissionPromptUi)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 165
        //                       1 170
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 112
        //                       6 116;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new PermissionPromptUi();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\001\001\001\002\002\000\000\000\001\t\000\002<\0", new Object[] {
                "permissionForPlatform_", "permissionForPlatformCase_", "bitField0_", "warmupPromptUi_", com/google/identity/growth/proto/Promotion$PermissionPromptUi$IosPermissionRequest
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$PermissionPromptUi;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$PermissionPromptUi;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$PermissionPromptUi;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new PermissionPromptUi();
            PermissionPromptUi permissionpromptui = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$PermissionPromptUi, permissionpromptui);
        }

        private PermissionPromptUi()
        {
            permissionForPlatformCase_ = 0;
        }
    }

    public static final class PermissionPromptUi.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        PermissionPromptUi.Builder()
        {
            super(PermissionPromptUi.DEFAULT_INSTANCE);
        }
    }

    public static final class PermissionPromptUi.IosPermissionRequest extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final PermissionPromptUi.IosPermissionRequest DEFAULT_INSTANCE;
        private static volatile Parser PARSER;

        public static PermissionPromptUi.IosPermissionRequest parseFrom(InputStream inputstream)
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
            return (PermissionPromptUi.IosPermissionRequest)obj;
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
            obj = new PermissionPromptUi.IosPermissionRequest();
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
            com/google/identity/growth/proto/Promotion$PermissionPromptUi$IosPermissionRequest;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$PermissionPromptUi$IosPermissionRequest;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$PermissionPromptUi$IosPermissionRequest;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            class _cls1
                implements com.google.protobuf.Internal.ListAdapter.Converter
            {

                public final Object convert(Object obj)
                {
                    IosPermissionType iospermissiontype = IosPermissionType.forNumber(((Integer)obj).intValue());
                    obj = iospermissiontype;
                    if (iospermissiontype == null)
                    {
                        obj = IosPermissionType.INVALID_PERMISSION_TYPE;
                    }
                    return obj;
                }

                _cls1()
                {
                }
            }

            new _cls1();
            DEFAULT_INSTANCE = new PermissionPromptUi.IosPermissionRequest();
            PermissionPromptUi.IosPermissionRequest iospermissionrequest = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$PermissionPromptUi$IosPermissionRequest, iospermissionrequest);
        }

        private PermissionPromptUi.IosPermissionRequest()
        {
        }
    }

    public static final class PermissionPromptUi.IosPermissionRequest.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        PermissionPromptUi.IosPermissionRequest.Builder()
        {
            super(PermissionPromptUi.IosPermissionRequest.DEFAULT_INSTANCE);
        }
    }

    public static final class PromoUi extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final PromoUi DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int uiTemplateCase_;
        public Object uiTemplate_;
        public int uiType_;

        public static PromoUi parseFrom(InputStream inputstream)
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
            return (PromoUi)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 198
        //                       1 203
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 145
        //                       6 149;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new PromoUi();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = UiType.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\006\001\001\001\006\006\000\000\000\001\f\000\002<\000\003<\000\004<\000\005<\000\006<\0", new Object[] {
                "uiTemplate_", "uiTemplateCase_", "bitField0_", "uiType_", obj, com/google/identity/growth/proto/Promotion$GeneralPromptUi, com/google/identity/growth/proto/Promotion$TapTargetUi, com/google/identity/growth/proto/Promotion$AndroidNotificationUi, com/google/identity/growth/proto/Promotion$TooltipUi, com/google/identity/growth/proto/Promotion$PermissionPromptUi
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$PromoUi;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$PromoUi;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$PromoUi;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new PromoUi();
            PromoUi promoui = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$PromoUi, promoui);
        }

        private PromoUi()
        {
            uiTemplateCase_ = 0;
        }
    }

    public static final class PromoUi.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        PromoUi.Builder()
        {
            super(PromoUi.DEFAULT_INSTANCE);
        }
    }

    public static final class PromoUi.UiTemplateCase extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final PromoUi.UiTemplateCase $VALUES[];
        public static final PromoUi.UiTemplateCase NOTIFICATION_UI;
        public static final PromoUi.UiTemplateCase PERMISSION_UI;
        public static final PromoUi.UiTemplateCase RATING_PROMPT_UI;
        private static final PromoUi.UiTemplateCase TAP_TARGET_UI;
        public static final PromoUi.UiTemplateCase TOOLTIP_UI;
        private static final PromoUi.UiTemplateCase UITEMPLATE_NOT_SET;
        private final int value;

        public static PromoUi.UiTemplateCase forNumber(int i)
        {
            switch (i)
            {
            case 1: // '\001'
            default:
                return null;

            case 2: // '\002'
                return RATING_PROMPT_UI;

            case 3: // '\003'
                return TAP_TARGET_UI;

            case 4: // '\004'
                return NOTIFICATION_UI;

            case 5: // '\005'
                return TOOLTIP_UI;

            case 6: // '\006'
                return PERMISSION_UI;

            case 0: // '\0'
                return UITEMPLATE_NOT_SET;
            }
        }

        public static PromoUi.UiTemplateCase[] values()
        {
            return (PromoUi.UiTemplateCase[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            RATING_PROMPT_UI = new PromoUi.UiTemplateCase("RATING_PROMPT_UI", 0, 2);
            TAP_TARGET_UI = new PromoUi.UiTemplateCase("TAP_TARGET_UI", 1, 3);
            NOTIFICATION_UI = new PromoUi.UiTemplateCase("NOTIFICATION_UI", 2, 4);
            TOOLTIP_UI = new PromoUi.UiTemplateCase("TOOLTIP_UI", 3, 5);
            PERMISSION_UI = new PromoUi.UiTemplateCase("PERMISSION_UI", 4, 6);
            UITEMPLATE_NOT_SET = new PromoUi.UiTemplateCase("UITEMPLATE_NOT_SET", 5, 0);
            $VALUES = (new PromoUi.UiTemplateCase[] {
                RATING_PROMPT_UI, TAP_TARGET_UI, NOTIFICATION_UI, TOOLTIP_UI, PERMISSION_UI, UITEMPLATE_NOT_SET
            });
        }

        private PromoUi.UiTemplateCase(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class PromoUi.UiType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final PromoUi.UiType $VALUES[];
        public static final PromoUi.UiType UITYPE_DIALOG;
        public static final PromoUi.UiType UITYPE_DO_NOT_DISPLAY;
        private static final PromoUi.UiType UITYPE_GM_DIALOG;
        private static final PromoUi.UiType UITYPE_GM_TAP_TARGET;
        private static final PromoUi.UiType UITYPE_GM_TOOLTIP;
        public static final PromoUi.UiType UITYPE_NONE;
        private static final PromoUi.UiType UITYPE_NON_BLOCKING_BOTTOMSHEET;
        private static final PromoUi.UiType UITYPE_NOTIFICATION;
        private static final PromoUi.UiType UITYPE_PERMISSION;
        private static final PromoUi.UiType UITYPE_RATING_DEFAULT;
        public static final PromoUi.UiType UITYPE_RATING_MATERIAL_DIALOG;
        private static final PromoUi.UiType UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG;
        public static final PromoUi.UiType UITYPE_RATING_PREFERRED_BOTTOMSHEET;
        private static final PromoUi.UiType UITYPE_RATING_PREFERRED_DIALOG;
        private static final PromoUi.UiType UITYPE_RATING_SYSTEM_DIALOG;
        public static final PromoUi.UiType UITYPE_TAP_TARGET;
        public static final PromoUi.UiType UITYPE_TOOLTIP;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static PromoUi.UiType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UITYPE_NONE;

            case 1: // '\001'
                return UITYPE_DO_NOT_DISPLAY;

            case 2: // '\002'
                return UITYPE_RATING_DEFAULT;

            case 3: // '\003'
                return UITYPE_RATING_SYSTEM_DIALOG;

            case 4: // '\004'
                return UITYPE_RATING_MATERIAL_DIALOG;

            case 5: // '\005'
                return UITYPE_RATING_PREFERRED_DIALOG;

            case 6: // '\006'
                return UITYPE_RATING_PREFERRED_BOTTOMSHEET;

            case 7: // '\007'
                return UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG;

            case 8: // '\b'
                return UITYPE_NON_BLOCKING_BOTTOMSHEET;

            case 12: // '\f'
                return UITYPE_DIALOG;

            case 9: // '\t'
                return UITYPE_TAP_TARGET;

            case 11: // '\013'
                return UITYPE_TOOLTIP;

            case 10: // '\n'
                return UITYPE_NOTIFICATION;

            case 13: // '\r'
                return UITYPE_PERMISSION;

            case 14: // '\016'
                return UITYPE_GM_DIALOG;

            case 15: // '\017'
                return UITYPE_GM_TAP_TARGET;

            case 16: // '\020'
                return UITYPE_GM_TOOLTIP;
            }
        }

        public static PromoUi.UiType[] values()
        {
            return (PromoUi.UiType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UITYPE_NONE = new PromoUi.UiType("UITYPE_NONE", 0, 0);
            UITYPE_DO_NOT_DISPLAY = new PromoUi.UiType("UITYPE_DO_NOT_DISPLAY", 1, 1);
            UITYPE_RATING_DEFAULT = new PromoUi.UiType("UITYPE_RATING_DEFAULT", 2, 2);
            UITYPE_RATING_SYSTEM_DIALOG = new PromoUi.UiType("UITYPE_RATING_SYSTEM_DIALOG", 3, 3);
            UITYPE_RATING_MATERIAL_DIALOG = new PromoUi.UiType("UITYPE_RATING_MATERIAL_DIALOG", 4, 4);
            UITYPE_RATING_PREFERRED_DIALOG = new PromoUi.UiType("UITYPE_RATING_PREFERRED_DIALOG", 5, 5);
            UITYPE_RATING_PREFERRED_BOTTOMSHEET = new PromoUi.UiType("UITYPE_RATING_PREFERRED_BOTTOMSHEET", 6, 6);
            UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG = new PromoUi.UiType("UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG", 7, 7);
            UITYPE_NON_BLOCKING_BOTTOMSHEET = new PromoUi.UiType("UITYPE_NON_BLOCKING_BOTTOMSHEET", 8, 8);
            UITYPE_DIALOG = new PromoUi.UiType("UITYPE_DIALOG", 9, 12);
            UITYPE_TAP_TARGET = new PromoUi.UiType("UITYPE_TAP_TARGET", 10, 9);
            UITYPE_TOOLTIP = new PromoUi.UiType("UITYPE_TOOLTIP", 11, 11);
            UITYPE_NOTIFICATION = new PromoUi.UiType("UITYPE_NOTIFICATION", 12, 10);
            UITYPE_PERMISSION = new PromoUi.UiType("UITYPE_PERMISSION", 13, 13);
            UITYPE_GM_DIALOG = new PromoUi.UiType("UITYPE_GM_DIALOG", 14, 14);
            UITYPE_GM_TAP_TARGET = new PromoUi.UiType("UITYPE_GM_TAP_TARGET", 15, 15);
            UITYPE_GM_TOOLTIP = new PromoUi.UiType("UITYPE_GM_TOOLTIP", 16, 16);
            $VALUES = (new PromoUi.UiType[] {
                UITYPE_NONE, UITYPE_DO_NOT_DISPLAY, UITYPE_RATING_DEFAULT, UITYPE_RATING_SYSTEM_DIALOG, UITYPE_RATING_MATERIAL_DIALOG, UITYPE_RATING_PREFERRED_DIALOG, UITYPE_RATING_PREFERRED_BOTTOMSHEET, UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG, UITYPE_NON_BLOCKING_BOTTOMSHEET, UITYPE_DIALOG, 
                UITYPE_TAP_TARGET, UITYPE_TOOLTIP, UITYPE_NOTIFICATION, UITYPE_PERMISSION, UITYPE_GM_DIALOG, UITYPE_GM_TAP_TARGET, UITYPE_GM_TOOLTIP
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return PromoUi.UiType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private PromoUi.UiType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class ScheduledEvent extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final ScheduledEvent DEFAULT_INSTANCE;
        private static volatile Parser PARSER;

        public static ScheduledEvent parseFrom(InputStream inputstream)
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
            return (ScheduledEvent)obj;
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
            obj = new ScheduledEvent();
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
            com/google/identity/growth/proto/Promotion$ScheduledEvent;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$ScheduledEvent;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$ScheduledEvent;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new ScheduledEvent();
            ScheduledEvent scheduledevent = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$ScheduledEvent, scheduledevent);
        }

        private ScheduledEvent()
        {
        }
    }

    public static final class ScheduledEvent.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        ScheduledEvent.Builder()
        {
            super(ScheduledEvent.DEFAULT_INSTANCE);
        }
    }

    public static final class StringList extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final StringList DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public com.google.protobuf.Internal.ProtobufList element_;

        public static StringList parseFrom(InputStream inputstream)
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
            return (StringList)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 145
        //                       1 150
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 92
        //                       6 96;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new StringList();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\001\000\000\001\001\001\000\001\000\001\032", new Object[] {
                "element_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$StringList;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$StringList;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$StringList;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new StringList();
            StringList stringlist = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$StringList, stringlist);
        }

        private StringList()
        {
            element_ = ProtobufArrayList.EMPTY_LIST;
        }
    }

    public static final class StringList.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        StringList.Builder()
        {
            super(StringList.DEFAULT_INSTANCE);
        }
    }

    public static final class TapTargetUi extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final TapTargetUi DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public GeneralPromptUi.Action action_;
        public Color backgroundColor_;
        public int bitField0_;
        public String bodyText_;
        public String headlineText_;
        public Color innerColor_;
        public boolean overrideElementColor_;
        public int targetCase_;
        public Object target_;
        public Color textColor_;

        public static TapTargetUi parseFrom(InputStream inputstream)
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
            return (TapTargetUi)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 195
        //                       1 200
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 142
        //                       6 146;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new TapTargetUi();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\n\001\001\001\013\n\000\000\000\001;\000\002\t\003\003\t\004\004\b\007\005\b\b\006\t\005\007\007\006\b\t\t\n;\000\0137\0", new Object[] {
                "target_", "targetCase_", "bitField0_", "backgroundColor_", "innerColor_", "headlineText_", "bodyText_", "textColor_", "overrideElementColor_", "action_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$TapTargetUi;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$TapTargetUi;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$TapTargetUi;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new TapTargetUi();
            TapTargetUi taptargetui = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$TapTargetUi, taptargetui);
        }

        private TapTargetUi()
        {
            targetCase_ = 0;
            headlineText_ = "";
            bodyText_ = "";
        }
    }

    public static final class TapTargetUi.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        TapTargetUi.Builder()
        {
            super(TapTargetUi.DEFAULT_INSTANCE);
        }
    }

    public static final class TapTargetUi.TargetCase extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final TapTargetUi.TargetCase $VALUES[];
        public static final TapTargetUi.TargetCase ELEMENT_NAME;
        public static final TapTargetUi.TargetCase ELEMENT_TAG;
        private static final TapTargetUi.TargetCase TARGET_NOT_SET;
        public static final TapTargetUi.TargetCase VISUAL_ELEMENT_ID;
        private final int value;

        public static TapTargetUi.TargetCase forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 1: // '\001'
                return ELEMENT_NAME;

            case 10: // '\n'
                return ELEMENT_TAG;

            case 11: // '\013'
                return VISUAL_ELEMENT_ID;

            case 0: // '\0'
                return TARGET_NOT_SET;
            }
        }

        public static TapTargetUi.TargetCase[] values()
        {
            return (TapTargetUi.TargetCase[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            ELEMENT_NAME = new TapTargetUi.TargetCase("ELEMENT_NAME", 0, 1);
            ELEMENT_TAG = new TapTargetUi.TargetCase("ELEMENT_TAG", 1, 10);
            VISUAL_ELEMENT_ID = new TapTargetUi.TargetCase("VISUAL_ELEMENT_ID", 2, 11);
            TARGET_NOT_SET = new TapTargetUi.TargetCase("TARGET_NOT_SET", 3, 0);
            $VALUES = (new TapTargetUi.TargetCase[] {
                ELEMENT_NAME, ELEMENT_TAG, VISUAL_ELEMENT_ID, TARGET_NOT_SET
            });
        }

        private TapTargetUi.TargetCase(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class TimeConstraintCondition extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final TimeConstraintCondition DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public static final com.google.protobuf.Internal.ListAdapter.Converter allowedDays_converter_ = new _cls1();
        public com.google.protobuf.Internal.IntList allowedDays_;
        public int bitField0_;
        public TimeOfDay endTime_;
        public TimeOfDay startTime_;

        public static TimeConstraintCondition parseFrom(InputStream inputstream)
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
            return (TimeConstraintCondition)obj;
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
            obj = new TimeConstraintCondition();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = DayOfWeek.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\003\000\001\001\003\003\000\001\000\001\t\000\002\t\001\003\036", new Object[] {
                "bitField0_", "startTime_", "endTime_", "allowedDays_", obj
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$TimeConstraintCondition;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$TimeConstraintCondition;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$TimeConstraintCondition;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            class _cls1
                implements com.google.protobuf.Internal.ListAdapter.Converter
            {

                public final Object convert(Object obj)
                {
                    DayOfWeek dayofweek = DayOfWeek.forNumber(((Integer)obj).intValue());
                    obj = dayofweek;
                    if (dayofweek == null)
                    {
                        obj = DayOfWeek.DAY_OF_WEEK_UNSPECIFIED;
                    }
                    return obj;
                }

                _cls1()
                {
                }
            }

            DEFAULT_INSTANCE = new TimeConstraintCondition();
            TimeConstraintCondition timeconstraintcondition = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$TimeConstraintCondition, timeconstraintcondition);
        }

        private TimeConstraintCondition()
        {
            allowedDays_ = IntArrayList.EMPTY_LIST;
        }
    }

    public static final class TimeConstraintCondition.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        TimeConstraintCondition.Builder()
        {
            super(TimeConstraintCondition.DEFAULT_INSTANCE);
        }
    }

    public static final class TooltipUi extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final TooltipUi DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public GeneralPromptUi.Action action_;
        public Color backgroundColor_;
        public int bitField0_;
        public String bodyText_;
        public String headlineText_;
        public int placement_;
        public int targetCase_;
        public Object target_;
        public Color textColor_;

        public static TooltipUi parseFrom(InputStream inputstream)
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
            return (TooltipUi)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 198
        //                       1 203
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 145
        //                       6 149;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new TooltipUi();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = Placement.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\t\001\001\001\t\t\000\000\000\001;\000\002\t\003\003\t\004\004\b\005\005\b\006\006\t\007\007\f\b\b;\000\t7\0", new Object[] {
                "target_", "targetCase_", "bitField0_", "backgroundColor_", "textColor_", "headlineText_", "bodyText_", "action_", "placement_", obj
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$TooltipUi;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$TooltipUi;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$TooltipUi;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new TooltipUi();
            TooltipUi tooltipui = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$TooltipUi, tooltipui);
        }

        private TooltipUi()
        {
            targetCase_ = 0;
            headlineText_ = "";
            bodyText_ = "";
        }
    }

    public static final class TooltipUi.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        TooltipUi.Builder()
        {
            super(TooltipUi.DEFAULT_INSTANCE);
        }
    }

    public static final class TooltipUi.Placement extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final TooltipUi.Placement $VALUES[];
        public static final TooltipUi.Placement ABOVE;
        public static final TooltipUi.Placement BELOW;
        public static final TooltipUi.Placement UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static TooltipUi.Placement forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return ABOVE;

            case 2: // '\002'
                return BELOW;
            }
        }

        public static TooltipUi.Placement[] values()
        {
            return (TooltipUi.Placement[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new TooltipUi.Placement("UNKNOWN", 0, 0);
            ABOVE = new TooltipUi.Placement("ABOVE", 1, 1);
            BELOW = new TooltipUi.Placement("BELOW", 2, 2);
            $VALUES = (new TooltipUi.Placement[] {
                UNKNOWN, ABOVE, BELOW
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return TooltipUi.Placement.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private TooltipUi.Placement(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class TooltipUi.TargetCase extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final TooltipUi.TargetCase $VALUES[];
        public static final TooltipUi.TargetCase ELEMENT_NAME;
        public static final TooltipUi.TargetCase ELEMENT_TAG;
        private static final TooltipUi.TargetCase TARGET_NOT_SET;
        public static final TooltipUi.TargetCase VISUAL_ELEMENT_ID;
        private final int value;

        public static TooltipUi.TargetCase forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 1: // '\001'
                return ELEMENT_NAME;

            case 8: // '\b'
                return ELEMENT_TAG;

            case 9: // '\t'
                return VISUAL_ELEMENT_ID;

            case 0: // '\0'
                return TARGET_NOT_SET;
            }
        }

        public static TooltipUi.TargetCase[] values()
        {
            return (TooltipUi.TargetCase[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            ELEMENT_NAME = new TooltipUi.TargetCase("ELEMENT_NAME", 0, 1);
            ELEMENT_TAG = new TooltipUi.TargetCase("ELEMENT_TAG", 1, 8);
            VISUAL_ELEMENT_ID = new TooltipUi.TargetCase("VISUAL_ELEMENT_ID", 2, 9);
            TARGET_NOT_SET = new TooltipUi.TargetCase("TARGET_NOT_SET", 3, 0);
            $VALUES = (new TooltipUi.TargetCase[] {
                ELEMENT_NAME, ELEMENT_TAG, VISUAL_ELEMENT_ID, TARGET_NOT_SET
            });
        }

        private TooltipUi.TargetCase(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class TriggeringRule extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final TriggeringRule DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public TriggeringConditions conditions_;
        public com.google.protobuf.Internal.ProtobufList event_;

        public static TriggeringRule parseFrom(InputStream inputstream)
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
            return (TriggeringRule)obj;
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
            obj = new TriggeringRule();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\001\000\001\033\002\t\0", new Object[] {
                "bitField0_", "event_", com/google/identity/growth/proto/Promotion$TriggeringRule$TriggeringEvent, "conditions_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$TriggeringRule;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$TriggeringRule;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$TriggeringRule;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new TriggeringRule();
            TriggeringRule triggeringrule = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$TriggeringRule, triggeringrule);
        }

        private TriggeringRule()
        {
            event_ = ProtobufArrayList.EMPTY_LIST;
        }
    }

    public static final class TriggeringRule.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        TriggeringRule.Builder()
        {
            super(TriggeringRule.DEFAULT_INSTANCE);
        }
    }

    public static final class TriggeringRule.TriggeringConditions extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final TriggeringRule.TriggeringConditions DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public boolean displayWithoutNewSync_;
        public com.google.protobuf.Internal.ProtobufList installedApp_;
        public int minBatteryPercentage_;
        public NetworkCondition network_;
        public com.google.protobuf.Internal.ProtobufList timeConstraint_;

        public static TriggeringRule.TriggeringConditions parseFrom(InputStream inputstream)
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
            return (TriggeringRule.TriggeringConditions)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 183
        //                       1 188
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 130
        //                       6 134;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new TriggeringRule.TriggeringConditions();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\005\000\001\001\006\005\000\002\000\001\t\000\002\004\001\003\033\005\007\003\006\033", new Object[] {
                "bitField0_", "network_", "minBatteryPercentage_", "installedApp_", com/google/identity/growth/proto/Promotion$InstalledAppCondition, "displayWithoutNewSync_", "timeConstraint_", com/google/identity/growth/proto/Promotion$TimeConstraintCondition
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$TriggeringRule$TriggeringConditions;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$TriggeringRule$TriggeringConditions;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$TriggeringRule$TriggeringConditions;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new TriggeringRule.TriggeringConditions();
            TriggeringRule.TriggeringConditions triggeringconditions = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$TriggeringRule$TriggeringConditions, triggeringconditions);
        }

        private TriggeringRule.TriggeringConditions()
        {
            installedApp_ = ProtobufArrayList.EMPTY_LIST;
            timeConstraint_ = ProtobufArrayList.EMPTY_LIST;
        }
    }

    public static final class TriggeringRule.TriggeringConditions.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        TriggeringRule.TriggeringConditions.Builder()
        {
            super(TriggeringRule.TriggeringConditions.DEFAULT_INSTANCE);
        }
    }

    public static final class TriggeringRule.TriggeringEvent extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final TriggeringRule.TriggeringEvent DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int eventCase_;
        public Object event_;

        public static TriggeringRule.TriggeringEvent parseFrom(InputStream inputstream)
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
            return (TriggeringRule.TriggeringEvent)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 186
        //                       1 191
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 133
        //                       6 137;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new TriggeringRule.TriggeringEvent();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = IosPermissionType.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\005\001\001\001\005\005\000\000\000\001<\000\002<\000\003<\000\004<\000\005?\0", new Object[] {
                "event_", "eventCase_", "bitField0_", com/google/identity/growth/proto/Promotion$ClearcutEvent, com/google/identity/growth/proto/Promotion$ScheduledEvent, com/google/identity/growth/proto/Promotion$AndroidSystemEvent, com/google/identity/growth/proto/Promotion$VisualElementEvent, obj
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$TriggeringRule$TriggeringEvent;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$TriggeringRule$TriggeringEvent;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$TriggeringRule$TriggeringEvent;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new TriggeringRule.TriggeringEvent();
            TriggeringRule.TriggeringEvent triggeringevent = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$TriggeringRule$TriggeringEvent, triggeringevent);
        }

        private TriggeringRule.TriggeringEvent()
        {
            eventCase_ = 0;
        }
    }

    public static final class TriggeringRule.TriggeringEvent.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        TriggeringRule.TriggeringEvent.Builder()
        {
            super(TriggeringRule.TriggeringEvent.DEFAULT_INSTANCE);
        }
    }

    public static final class VisualElementEvent extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final VisualElementEvent DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int action_;
        public int bitField0_;
        public com.google.protobuf.Internal.IntList nodeIdPath_;

        public static VisualElementEvent parseFrom(InputStream inputstream)
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
            return (VisualElementEvent)obj;
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
            obj = new VisualElementEvent();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = Action.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\001\000\001\026\002\f\0", new Object[] {
                "bitField0_", "nodeIdPath_", "action_", obj
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$VisualElementEvent;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$VisualElementEvent;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$VisualElementEvent;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new VisualElementEvent();
            VisualElementEvent visualelementevent = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$VisualElementEvent, visualelementevent);
        }

        private VisualElementEvent()
        {
            nodeIdPath_ = IntArrayList.EMPTY_LIST;
        }
    }

    public static final class VisualElementEvent.Action extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final VisualElementEvent.Action $VALUES[];
        private static final VisualElementEvent.Action DISPLAYED;
        private static final VisualElementEvent.Action TAPPED;
        public static final VisualElementEvent.Action UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static VisualElementEvent.Action forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return DISPLAYED;

            case 2: // '\002'
                return TAPPED;
            }
        }

        public static VisualElementEvent.Action[] values()
        {
            return (VisualElementEvent.Action[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new VisualElementEvent.Action("UNKNOWN", 0, 0);
            DISPLAYED = new VisualElementEvent.Action("DISPLAYED", 1, 1);
            TAPPED = new VisualElementEvent.Action("TAPPED", 2, 2);
            $VALUES = (new VisualElementEvent.Action[] {
                UNKNOWN, DISPLAYED, TAPPED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return VisualElementEvent.Action.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private VisualElementEvent.Action(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class VisualElementEvent.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        VisualElementEvent.Builder()
        {
            super(VisualElementEvent.DEFAULT_INSTANCE);
        }
    }

}
