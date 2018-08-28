// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate.api;

import com.google.caribou.tasks.service.CustomizedSnoozePreset;
import com.google.personalization.assist.annotate.ClientType;
import com.google.personalization.assist.annotate.DeviceType;
import com.google.personalization.assist.annotate.SuggestionType;
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

// Referenced classes of package com.google.personalization.assist.annotate.api:
//            Annotation, GeoLocation, SuggestionClick

public final class AnnotatedSuggestRequest extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(AnnotatedSuggestRequest.DEFAULT_INSTANCE);
        }
    }


    public static final AnnotatedSuggestRequest DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Annotation annotation_;
    public int bitField0_;
    public int clientType_;
    public String country_;
    public int deviceType_;
    public String language_;
    public GeoLocation location_;
    private byte memoizedIsInitialized;
    public String query_;
    public String sessionId_;
    public CustomizedSnoozePreset snoozePresets_;
    public SuggestionClick suggestionClick_;
    public int suggestionType_;
    public String timezone_;

    private AnnotatedSuggestRequest()
    {
        memoizedIsInitialized = 2;
        query_ = "";
        language_ = "";
        country_ = "";
        timezone_ = "";
        sessionId_ = "";
    }

    public static AnnotatedSuggestRequest parseFrom(InputStream inputstream)
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
        return (AnnotatedSuggestRequest)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        boolean flag = false;
        i - 1;
        JVM INSTR tableswitch 0 6: default 48
    //                   0 246
    //                   1 254
    //                   2 74
    //                   3 56
    //                   4 66
    //                   5 193
    //                   6 197;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new AnnotatedSuggestRequest();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = ClientType.internalVerifier;
        com.google.protobuf.Internal.EnumVerifier enumverifier = DeviceType.internalVerifier;
        com.google.protobuf.Internal.EnumVerifier enumverifier1 = SuggestionType.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\f\000\001\001\023\f\000\000\001\001\b\000\002\b\001\004\b\002\005\f\005\006\t\007\b\b\n\t\t\013\n\f\006\r\f\016\016\t\004\017\b\b\023\u0409\021", new Object[] {
            "bitField0_", "query_", "language_", "country_", "clientType_", obj, "location_", "sessionId_", "suggestionClick_", "deviceType_", 
            enumverifier, "suggestionType_", enumverifier1, "annotation_", "timezone_", "snoozePresets_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/personalization/assist/annotate/api/AnnotatedSuggestRequest;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/personalization/assist/annotate/api/AnnotatedSuggestRequest;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/personalization/assist/annotate/api/AnnotatedSuggestRequest;
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
        new _cls1();
        DEFAULT_INSTANCE = new AnnotatedSuggestRequest();
        AnnotatedSuggestRequest annotatedsuggestrequest = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/personalization/assist/annotate/api/AnnotatedSuggestRequest, annotatedsuggestrequest);
    }

    private class _cls1
        implements com.google.protobuf.Internal.ListAdapter.Converter
    {

        public final Object convert(Object obj)
        {
            AnnotationType annotationtype = AnnotationType.forNumber(((Integer)obj).intValue());
            obj = annotationtype;
            if (annotationtype == null)
            {
                obj = AnnotationType.ACTIVATE_BANK_CARD_ACTION;
            }
            return obj;
        }

        _cls1()
        {
        }
    }

}
