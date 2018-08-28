// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.apps.docs.diagnostics.impressions.proto.sessioninvariants.DistributionInvariants;
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

public final class CalendarLoggingExtension
{
    public static final class AndroidCalendarExtensionProto extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final AndroidCalendarExtensionProto DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int actionType_;
        public int bitField0_;
        public DistributionInvariants distributionInvariants_;

        public static AndroidCalendarExtensionProto parseFrom(InputStream inputstream)
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
            return (AndroidCalendarExtensionProto)obj;
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
            obj = new AndroidCalendarExtensionProto();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = ActionType.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\000\000\001\f\000\002\t\001", new Object[] {
                "bitField0_", "actionType_", obj, "distributionInvariants_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/android/calendar/CalendarLoggingExtension$AndroidCalendarExtensionProto;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/android/calendar/CalendarLoggingExtension$AndroidCalendarExtensionProto;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/android/calendar/CalendarLoggingExtension$AndroidCalendarExtensionProto;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new AndroidCalendarExtensionProto();
            AndroidCalendarExtensionProto androidcalendarextensionproto = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/android/calendar/CalendarLoggingExtension$AndroidCalendarExtensionProto, androidcalendarextensionproto);
        }

        private AndroidCalendarExtensionProto()
        {
        }
    }

    public static final class AndroidCalendarExtensionProto.ActionType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final AndroidCalendarExtensionProto.ActionType $VALUES[];
        public static final AndroidCalendarExtensionProto.ActionType ACTIVITY_CREATE;
        public static final AndroidCalendarExtensionProto.ActionType APPLICATION_VIEW;
        public static final AndroidCalendarExtensionProto.ActionType CHANGE_EVENT;
        public static final AndroidCalendarExtensionProto.ActionType CHANGE_GOAL;
        public static final AndroidCalendarExtensionProto.ActionType CHANGE_REMINDER;
        public static final AndroidCalendarExtensionProto.ActionType CREATE_EVENT;
        public static final AndroidCalendarExtensionProto.ActionType CREATE_GOAL;
        public static final AndroidCalendarExtensionProto.ActionType CREATE_REMINDER;
        public static final AndroidCalendarExtensionProto.ActionType RESPONDED_EVENT;
        private static final AndroidCalendarExtensionProto.ActionType UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static AndroidCalendarExtensionProto.ActionType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return CREATE_EVENT;

            case 2: // '\002'
                return CHANGE_EVENT;

            case 3: // '\003'
                return RESPONDED_EVENT;

            case 4: // '\004'
                return APPLICATION_VIEW;

            case 5: // '\005'
                return CREATE_REMINDER;

            case 6: // '\006'
                return CHANGE_REMINDER;

            case 7: // '\007'
                return CREATE_GOAL;

            case 8: // '\b'
                return CHANGE_GOAL;

            case 9: // '\t'
                return ACTIVITY_CREATE;
            }
        }

        public static AndroidCalendarExtensionProto.ActionType[] values()
        {
            return (AndroidCalendarExtensionProto.ActionType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new AndroidCalendarExtensionProto.ActionType("UNKNOWN", 0, 0);
            CREATE_EVENT = new AndroidCalendarExtensionProto.ActionType("CREATE_EVENT", 1, 1);
            CHANGE_EVENT = new AndroidCalendarExtensionProto.ActionType("CHANGE_EVENT", 2, 2);
            RESPONDED_EVENT = new AndroidCalendarExtensionProto.ActionType("RESPONDED_EVENT", 3, 3);
            APPLICATION_VIEW = new AndroidCalendarExtensionProto.ActionType("APPLICATION_VIEW", 4, 4);
            CREATE_REMINDER = new AndroidCalendarExtensionProto.ActionType("CREATE_REMINDER", 5, 5);
            CHANGE_REMINDER = new AndroidCalendarExtensionProto.ActionType("CHANGE_REMINDER", 6, 6);
            CREATE_GOAL = new AndroidCalendarExtensionProto.ActionType("CREATE_GOAL", 7, 7);
            CHANGE_GOAL = new AndroidCalendarExtensionProto.ActionType("CHANGE_GOAL", 8, 8);
            ACTIVITY_CREATE = new AndroidCalendarExtensionProto.ActionType("ACTIVITY_CREATE", 9, 9);
            $VALUES = (new AndroidCalendarExtensionProto.ActionType[] {
                UNKNOWN, CREATE_EVENT, CHANGE_EVENT, RESPONDED_EVENT, APPLICATION_VIEW, CREATE_REMINDER, CHANGE_REMINDER, CREATE_GOAL, CHANGE_GOAL, ACTIVITY_CREATE
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return AndroidCalendarExtensionProto.ActionType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private AndroidCalendarExtensionProto.ActionType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class AndroidCalendarExtensionProto.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        AndroidCalendarExtensionProto.Builder()
        {
            super(AndroidCalendarExtensionProto.DEFAULT_INSTANCE);
        }
    }

}
