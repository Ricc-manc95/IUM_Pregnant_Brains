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
import com.google.protobuf.ProtobufArrayList;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Reminder, ConferenceData, DateOrDateTime, EventSource, 
//            UserStatus, PrivateEventData, StructuredLocation, RecurrenceData, 
//            TimeChangeProposal, HabitInstanceData

public final class Event extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Attachment extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final Attachment DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String fileId_;
        public String fileUrl_;
        public String iconUrl_;
        public String mimeType_;
        public String title_;

        public static Attachment parseFrom(InputStream inputstream)
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
            return (Attachment)generatedmessagelite;
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
            obj = new Attachment();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\005\000\001\001\005\005\000\000\000\001\b\000\002\b\001\003\b\003\004\b\002\005\b\004", new Object[] {
                "bitField0_", "fileUrl_", "title_", "iconUrl_", "mimeType_", "fileId_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/calendar/feapi/v1/Event$Attachment;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/calendar/feapi/v1/Event$Attachment;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/calendar/feapi/v1/Event$Attachment;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new Attachment();
            Attachment attachment = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Event$Attachment, attachment);
        }

        private Attachment()
        {
            fileUrl_ = "";
            title_ = "";
            mimeType_ = "";
            iconUrl_ = "";
            fileId_ = "";
        }
    }

    public static final class Attachment.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Attachment.Builder()
        {
            super(Attachment.DEFAULT_INSTANCE);
        }
    }

    public static final class Attendee extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final Attendee DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int additionalGuests_;
        public int bitField0_;
        public String displayName_;
        public String email_;
        public String id_;
        public boolean optionalAttendee_;
        public boolean organizer_;
        public boolean resource_;
        public String responseComment_;
        public int responseStatus_;
        public boolean self_;
        public TimeChangeProposal timeChangeProposal_;

        public static Attendee parseFrom(InputStream inputstream)
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
            return (Attendee)generatedmessagelite;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 216
        //                       1 221
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 163
        //                       6 167;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new Attendee();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = ResponseStatus.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\013\000\001\001\020\013\000\000\000\001\b\001\002\b\002\004\007\003\005\007\004\006\f\005\007\b\006\b\004\007\t\007\n\n\007\013\013\b\000\020\t\b", new Object[] {
                "bitField0_", "email_", "displayName_", "resource_", "optionalAttendee_", "responseStatus_", obj, "responseComment_", "additionalGuests_", "organizer_", 
                "self_", "id_", "timeChangeProposal_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/calendar/feapi/v1/Event$Attendee;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/calendar/feapi/v1/Event$Attendee;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/calendar/feapi/v1/Event$Attendee;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new Attendee();
            Attendee attendee = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Event$Attendee, attendee);
        }

        private Attendee()
        {
            id_ = "";
            email_ = "";
            displayName_ = "";
            responseComment_ = "";
        }
    }

    public static final class Attendee.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Attendee.Builder()
        {
            super(Attendee.DEFAULT_INSTANCE);
        }
    }

    public static final class Attendee.ResponseStatus extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Attendee.ResponseStatus $VALUES[];
        public static final Attendee.ResponseStatus ACCEPTED;
        public static final Attendee.ResponseStatus DECLINED;
        public static final Attendee.ResponseStatus NEEDS_ACTION;
        public static final Attendee.ResponseStatus TENTATIVE;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Attendee.ResponseStatus forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return NEEDS_ACTION;

            case 1: // '\001'
                return DECLINED;

            case 2: // '\002'
                return TENTATIVE;

            case 3: // '\003'
                return ACCEPTED;
            }
        }

        public static Attendee.ResponseStatus[] values()
        {
            return (Attendee.ResponseStatus[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            NEEDS_ACTION = new Attendee.ResponseStatus("NEEDS_ACTION", 0, 0);
            DECLINED = new Attendee.ResponseStatus("DECLINED", 1, 1);
            TENTATIVE = new Attendee.ResponseStatus("TENTATIVE", 2, 2);
            ACCEPTED = new Attendee.ResponseStatus("ACCEPTED", 3, 3);
            $VALUES = (new Attendee.ResponseStatus[] {
                NEEDS_ACTION, DECLINED, TENTATIVE, ACCEPTED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Attendee.ResponseStatus.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Attendee.ResponseStatus(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(Event.DEFAULT_INSTANCE);
        }
    }

    public static final class EventGadget extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final EventGadget DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public com.google.protobuf.Internal.ProtobufList preference_;

        public static EventGadget parseFrom(InputStream inputstream)
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
            return (EventGadget)generatedmessagelite;
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
            obj = new EventGadget();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\001\000\001\b\b\001\000\001\000\b\033", new Object[] {
                "bitField0_", "preference_", com/google/protos/calendar/feapi/v1/Event$EventGadget$Preference
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/calendar/feapi/v1/Event$EventGadget;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/calendar/feapi/v1/Event$EventGadget;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/calendar/feapi/v1/Event$EventGadget;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new EventGadget();
            EventGadget eventgadget = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Event$EventGadget, eventgadget);
        }

        private EventGadget()
        {
            preference_ = ProtobufArrayList.EMPTY_LIST;
        }
    }

    public static final class EventGadget.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        EventGadget.Builder()
        {
            super(EventGadget.DEFAULT_INSTANCE);
        }
    }

    public static final class EventGadget.Preference extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final EventGadget.Preference DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String name_;
        public String value_;

        public static EventGadget.Preference parseFrom(InputStream inputstream)
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
            return (EventGadget.Preference)generatedmessagelite;
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
            obj = new EventGadget.Preference();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\000\000\001\b\000\002\b\001", new Object[] {
                "bitField0_", "name_", "value_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/calendar/feapi/v1/Event$EventGadget$Preference;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/calendar/feapi/v1/Event$EventGadget$Preference;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/calendar/feapi/v1/Event$EventGadget$Preference;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new EventGadget.Preference();
            EventGadget.Preference preference = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Event$EventGadget$Preference, preference);
        }

        private EventGadget.Preference()
        {
            name_ = "";
            value_ = "";
        }
    }

    public static final class EventGadget.Preference.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        EventGadget.Preference.Builder()
        {
            super(EventGadget.Preference.DEFAULT_INSTANCE);
        }
    }

    public static final class ExtendedProperty extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final ExtendedProperty DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String name_;
        public String value_;

        public static ExtendedProperty parseFrom(InputStream inputstream)
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
            return (ExtendedProperty)generatedmessagelite;
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
            obj = new ExtendedProperty();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\000\000\001\b\000\002\b\001", new Object[] {
                "bitField0_", "name_", "value_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/calendar/feapi/v1/Event$ExtendedProperty;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/calendar/feapi/v1/Event$ExtendedProperty;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/calendar/feapi/v1/Event$ExtendedProperty;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new ExtendedProperty();
            ExtendedProperty extendedproperty = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Event$ExtendedProperty, extendedproperty);
        }

        private ExtendedProperty()
        {
            name_ = "";
            value_ = "";
        }
    }

    public static final class ExtendedProperty.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        ExtendedProperty.Builder()
        {
            super(ExtendedProperty.DEFAULT_INSTANCE);
        }
    }

    public static final class HabitInstance extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final HabitInstance DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String habitId_;
        public HabitInstanceData habitInstanceData_;

        public static HabitInstance parseFrom(InputStream inputstream)
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
            return (HabitInstance)generatedmessagelite;
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
            obj = new HabitInstance();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\000\000\001\b\000\002\t\001", new Object[] {
                "bitField0_", "habitId_", "habitInstanceData_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/calendar/feapi/v1/Event$HabitInstance;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/calendar/feapi/v1/Event$HabitInstance;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/calendar/feapi/v1/Event$HabitInstance;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new HabitInstance();
            HabitInstance habitinstance = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Event$HabitInstance, habitinstance);
        }

        private HabitInstance()
        {
            habitId_ = "";
        }
    }

    public static final class HabitInstance.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        HabitInstance.Builder()
        {
            super(HabitInstance.DEFAULT_INSTANCE);
        }
    }

    public static final class Principal extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final Principal DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String displayName_;
        public String email_;
        public boolean self_;

        public static Principal parseFrom(InputStream inputstream)
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
            return (Principal)generatedmessagelite;
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
            obj = new Principal();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\003\000\001\001\004\003\000\000\000\001\b\001\002\b\002\004\007\003", new Object[] {
                "bitField0_", "email_", "displayName_", "self_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/calendar/feapi/v1/Event$Principal;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/calendar/feapi/v1/Event$Principal;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/calendar/feapi/v1/Event$Principal;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new Principal();
            Principal principal = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Event$Principal, principal);
        }

        private Principal()
        {
            email_ = "";
            displayName_ = "";
        }
    }

    public static final class Principal.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Principal.Builder()
        {
            super(Principal.DEFAULT_INSTANCE);
        }
    }

    public static final class ResponseSummary extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final ResponseSummary DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int numAccepted_;
        public int numDeclined_;
        public int numNeedsAction_;
        public int numTentative_;

        public static ResponseSummary parseFrom(InputStream inputstream)
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
            return (ResponseSummary)generatedmessagelite;
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
            obj = new ResponseSummary();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\004\000\001\001\004\004\000\000\000\001\004\000\002\004\001\003\004\002\004\004\003", new Object[] {
                "bitField0_", "numNeedsAction_", "numAccepted_", "numDeclined_", "numTentative_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/calendar/feapi/v1/Event$ResponseSummary;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/calendar/feapi/v1/Event$ResponseSummary;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/calendar/feapi/v1/Event$ResponseSummary;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new ResponseSummary();
            ResponseSummary responsesummary = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Event$ResponseSummary, responsesummary);
        }

        private ResponseSummary()
        {
        }
    }

    public static final class ResponseSummary.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        ResponseSummary.Builder()
        {
            super(ResponseSummary.DEFAULT_INSTANCE);
        }
    }

    public static final class Status extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Status $VALUES[];
        public static final Status CANCELLED;
        public static final Status CONFIRMED;
        public static final Status TENTATIVE;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Status forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return CONFIRMED;

            case 1: // '\001'
                return TENTATIVE;

            case 2: // '\002'
                return CANCELLED;
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
            CONFIRMED = new Status("CONFIRMED", 0, 0);
            TENTATIVE = new Status("TENTATIVE", 1, 1);
            CANCELLED = new Status("CANCELLED", 2, 2);
            $VALUES = (new Status[] {
                CONFIRMED, TENTATIVE, CANCELLED
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

    public static final class Transparency extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Transparency $VALUES[];
        public static final Transparency OPAQUE;
        public static final Transparency TRANSPARENT;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Transparency forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return OPAQUE;

            case 1: // '\001'
                return TRANSPARENT;
            }
        }

        public static Transparency[] values()
        {
            return (Transparency[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            OPAQUE = new Transparency("OPAQUE", 0, 0);
            TRANSPARENT = new Transparency("TRANSPARENT", 1, 1);
            $VALUES = (new Transparency[] {
                OPAQUE, TRANSPARENT
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Transparency.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Transparency(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class Type extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Type $VALUES[];
        public static final Type PLUS_EVENT;
        public static final Type UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static Type forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return PLUS_EVENT;
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
            UNKNOWN = new Type("UNKNOWN", 0, 0);
            PLUS_EVENT = new Type("PLUS_EVENT", 1, 1);
            $VALUES = (new Type[] {
                UNKNOWN, PLUS_EVENT
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

    public static final class Visibility extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Visibility $VALUES[];
        public static final Visibility CONFIDENTIAL;
        public static final Visibility DEFAULT;
        public static final Visibility PRIVATE;
        public static final Visibility PUBLIC;
        public static final Visibility SECRET;
        public static final Visibility SHADOW;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Visibility forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return DEFAULT;

            case 1: // '\001'
                return PUBLIC;

            case 2: // '\002'
                return PRIVATE;

            case 3: // '\003'
                return CONFIDENTIAL;

            case 4: // '\004'
                return SECRET;

            case 5: // '\005'
                return SHADOW;
            }
        }

        public static Visibility[] values()
        {
            return (Visibility[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            DEFAULT = new Visibility("DEFAULT", 0, 0);
            PUBLIC = new Visibility("PUBLIC", 1, 1);
            PRIVATE = new Visibility("PRIVATE", 2, 2);
            CONFIDENTIAL = new Visibility("CONFIDENTIAL", 3, 3);
            SECRET = new Visibility("SECRET", 4, 4);
            SHADOW = new Visibility("SHADOW", 5, 5);
            $VALUES = (new Visibility[] {
                DEFAULT, PUBLIC, PRIVATE, CONFIDENTIAL, SECRET, SHADOW
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Visibility.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Visibility(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final Event DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public boolean allFollowing_;
    public com.google.protobuf.Internal.ProtobufList attachment_;
    public com.google.protobuf.Internal.ProtobufList attendee_;
    public String backgroundImageUrl_;
    public int bitField0_;
    public int bitField1_;
    public String color_;
    public ConferenceData conferenceData_;
    public long creationTimeMs_;
    public Principal creator_;
    public String description_;
    public DateOrDateTime end_;
    public EventGadget eventGadget_;
    public EventSource eventSource_;
    public String fingerprint_;
    public boolean guestsCanInviteOthers_;
    public boolean guestsCanModify_;
    public boolean guestsCanSeeGuests_;
    public HabitInstance habitInstance_;
    public String hangoutLink_;
    public String htmlLink_;
    public String icalUid_;
    public String id_;
    public String location_;
    private byte memoizedIsInitialized;
    public long modificationTimeMs_;
    public Principal organizer_;
    public DateOrDateTime originalStartTime_;
    public boolean otherAttendeesExcluded_;
    public UserStatus participantStatus_;
    public boolean phantom_;
    public PrivateEventData privateEventData_;
    public com.google.protobuf.Internal.ProtobufList privateExtendedProperty_;
    public String rangeEventId_;
    public com.google.protobuf.Internal.ProtobufList recurrenceData_;
    public String recurringEventId_;
    public com.google.protobuf.Internal.ProtobufList reminderOverride_;
    public ResponseSummary responseSummary_;
    public int sequence_;
    public com.google.protobuf.Internal.ProtobufList sharedExtendedProperty_;
    public DateOrDateTime start_;
    public int status_;
    public StructuredLocation structuredLocation_;
    public RecurrenceData structuredRecurrenceData_;
    public String summary_;
    public int transparency_;
    public int type_;
    public boolean unbounded_;
    public boolean useDefaultReminders_;
    public int visibility_;

    private Event()
    {
        memoizedIsInitialized = 2;
        id_ = "";
        htmlLink_ = "";
        summary_ = "";
        description_ = "";
        location_ = "";
        color_ = "";
        recurrenceData_ = ProtobufArrayList.EMPTY_LIST;
        recurringEventId_ = "";
        rangeEventId_ = "";
        icalUid_ = "";
        attendee_ = ProtobufArrayList.EMPTY_LIST;
        privateExtendedProperty_ = ProtobufArrayList.EMPTY_LIST;
        sharedExtendedProperty_ = ProtobufArrayList.EMPTY_LIST;
        hangoutLink_ = "";
        guestsCanInviteOthers_ = true;
        guestsCanSeeGuests_ = true;
        useDefaultReminders_ = true;
        reminderOverride_ = ProtobufArrayList.EMPTY_LIST;
        fingerprint_ = "";
        backgroundImageUrl_ = "";
        attachment_ = ProtobufArrayList.EMPTY_LIST;
    }

    public static Event parseFrom(InputStream inputstream)
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
        return (Event)generatedmessagelite;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        boolean flag = false;
        i - 1;
        JVM INSTR tableswitch 0 6: default 48
    //                   0 554
    //                   1 562
    //                   2 74
    //                   3 56
    //                   4 66
    //                   5 501
    //                   6 505;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new Event();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = Status.internalVerifier;
        com.google.protobuf.Internal.EnumVerifier enumverifier = Transparency.internalVerifier;
        com.google.protobuf.Internal.EnumVerifier enumverifier1 = Visibility.internalVerifier;
        com.google.protobuf.Internal.EnumVerifier enumverifier2 = Type.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001/\000\002\001@/\000\006\001\001\b\000\002\f\002\003\b\004\004\002\005\005\002\006\006\b\007\007\b\b\b\b\013\n\t\017\r\032\016\b\025\020\f\031\022\f\033\023\b\034\024\004\035\025\033\026\007\036\030\033\031\033\033\t%\035\007'\036\007(\037\007)!\007,\"\033#\t\020$\t\021%\t\022&\t\027'\b\r(\b-)\f\001*\b.+\007\023.\b#0\t01\t\f2\007\0033\007\0304\u0409 8\b\0269\033:\t$;\t1=\t\037>\t2@\t\024", new Object[] {
            "bitField0_", "bitField1_", "id_", "status_", obj, "htmlLink_", "creationTimeMs_", "modificationTimeMs_", "summary_", "description_", 
            "location_", "creator_", "recurrenceData_", "recurringEventId_", "transparency_", enumverifier, "visibility_", enumverifier1, "icalUid_", "sequence_", 
            "attendee_", com/google/protos/calendar/feapi/v1/Event$Attendee, "otherAttendeesExcluded_", "privateExtendedProperty_", com/google/protos/calendar/feapi/v1/Event$ExtendedProperty, "sharedExtendedProperty_", com/google/protos/calendar/feapi/v1/Event$ExtendedProperty, "eventGadget_", "guestsCanInviteOthers_", "guestsCanModify_", 
            "guestsCanSeeGuests_", "useDefaultReminders_", "reminderOverride_", com/google/protos/calendar/feapi/v1/Reminder, "organizer_", "start_", "end_", "originalStartTime_", "color_", "fingerprint_", 
            "type_", enumverifier2, "backgroundImageUrl_", "unbounded_", "hangoutLink_", "eventSource_", "structuredLocation_", "phantom_", "allFollowing_", "privateEventData_", 
            "rangeEventId_", "attachment_", com/google/protos/calendar/feapi/v1/Event$Attachment, "conferenceData_", "habitInstance_", "responseSummary_", "participantStatus_", "structuredRecurrenceData_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/protos/calendar/feapi/v1/Event;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/protos/calendar/feapi/v1/Event;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/protos/calendar/feapi/v1/Event;
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
        DEFAULT_INSTANCE = new Event();
        Event event = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Event, event);
    }
}
