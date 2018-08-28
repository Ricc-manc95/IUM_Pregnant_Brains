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

// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Event, TimeChangeProposal

public static final class responseComment_ extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(Event.Attendee.DEFAULT_INSTANCE);
        }
    }

    public static final class ResponseStatus extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final ResponseStatus $VALUES[];
        public static final ResponseStatus ACCEPTED;
        public static final ResponseStatus DECLINED;
        public static final ResponseStatus NEEDS_ACTION;
        public static final ResponseStatus TENTATIVE;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static ResponseStatus forNumber(int i)
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

        public static ResponseStatus[] values()
        {
            return (ResponseStatus[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            NEEDS_ACTION = new ResponseStatus("NEEDS_ACTION", 0, 0);
            DECLINED = new ResponseStatus("DECLINED", 1, 1);
            TENTATIVE = new ResponseStatus("TENTATIVE", 2, 2);
            ACCEPTED = new ResponseStatus("ACCEPTED", 3, 3);
            $VALUES = (new ResponseStatus[] {
                NEEDS_ACTION, DECLINED, TENTATIVE, ACCEPTED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return ResponseStatus.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private ResponseStatus(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final tanceMap DEFAULT_INSTANCE;
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

    public static ResponseStatus._cls2 parseFrom(InputStream inputstream)
        throws IOException
    {
        GeneratedMessageLite generatedmessagelite = GeneratedMessageLite.parsePartialFrom(DEFAULT_INSTANCE, CodedInputStream.newInstance(inputstream), ExtensionRegistryLite.getEmptyRegistry());
        if (generatedmessagelite != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content..Status.GET_MEMOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                    int i = android.support.v4.content..Status.SET_MEMOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
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
        return (etMessage)generatedmessagelite;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 216
    //                   1 221
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 163
    //                   6 167;
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
        obj = new com.google.protobuf.eLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
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
        DEFAULT_INSTANCE = new <init>();
        PARSER parser = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/Event$Attendee, parser);
    }

    private ResponseStatus.value()
    {
        id_ = "";
        email_ = "";
        displayName_ = "";
        responseComment_ = "";
    }
}
