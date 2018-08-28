// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
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

// Referenced classes of package com.google.calendar.suggest.v2:
//            TimeSuggestion, AttendeeEvents, Attendee, OmittedAttendee

public final class SuggestTimeResponse extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(SuggestTimeResponse.DEFAULT_INSTANCE);
        }
    }


    public static final SuggestTimeResponse DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int acceptableSuggestions_;
    public com.google.protobuf.Internal.ProtobufList attendeeEvents_;
    public int bitField0_;
    public com.google.protobuf.Internal.ProtobufList consideredAttendees_;
    public com.google.protobuf.Internal.ProtobufList omittedAttendees_;
    public String responseId_;
    public com.google.protobuf.Internal.ProtobufList suggestions_;

    private SuggestTimeResponse()
    {
        responseId_ = "";
        suggestions_ = ProtobufArrayList.EMPTY_LIST;
        attendeeEvents_ = ProtobufArrayList.EMPTY_LIST;
        consideredAttendees_ = ProtobufArrayList.EMPTY_LIST;
        omittedAttendees_ = ProtobufArrayList.EMPTY_LIST;
    }

    public static SuggestTimeResponse parseFrom(InputStream inputstream)
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
        return (SuggestTimeResponse)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 201
    //                   1 206
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 148
    //                   6 152;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new SuggestTimeResponse();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        return new RawMessageInfo(DEFAULT_INSTANCE, "\000\006\000\001\001\b\006\000\004\000\001\033\002\033\005\033\006\004\007\033\b\u0208", new Object[] {
            "bitField0_", "suggestions_", com/google/calendar/suggest/v2/TimeSuggestion, "attendeeEvents_", com/google/calendar/suggest/v2/AttendeeEvents, "consideredAttendees_", com/google/calendar/suggest/v2/Attendee, "acceptableSuggestions_", "omittedAttendees_", com/google/calendar/suggest/v2/OmittedAttendee, 
            "responseId_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/calendar/suggest/v2/SuggestTimeResponse;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/calendar/suggest/v2/SuggestTimeResponse;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/calendar/suggest/v2/SuggestTimeResponse;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new SuggestTimeResponse();
        SuggestTimeResponse suggesttimeresponse = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/calendar/suggest/v2/SuggestTimeResponse, suggesttimeresponse);
    }
}
