// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.IntArrayList;
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
//            Freq, Weekday, ByDay, DateOrDateTime

public final class RecurRulePart extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(RecurRulePart.DEFAULT_INSTANCE);
        }
    }


    public static final RecurRulePart DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public com.google.protobuf.Internal.ProtobufList byday_;
    public com.google.protobuf.Internal.IntList byhour_;
    public com.google.protobuf.Internal.IntList byminute_;
    public com.google.protobuf.Internal.IntList bymonth_;
    public com.google.protobuf.Internal.IntList bymonthday_;
    public com.google.protobuf.Internal.IntList bysecond_;
    public com.google.protobuf.Internal.IntList bysetpos_;
    public com.google.protobuf.Internal.IntList byweekno_;
    public com.google.protobuf.Internal.IntList byyearday_;
    public int count_;
    public int freq_;
    public int interval_;
    public DateOrDateTime until_;
    public int wkst_;

    private RecurRulePart()
    {
        freq_ = 3;
        bysecond_ = IntArrayList.EMPTY_LIST;
        byminute_ = IntArrayList.EMPTY_LIST;
        byhour_ = IntArrayList.EMPTY_LIST;
        byday_ = ProtobufArrayList.EMPTY_LIST;
        bymonthday_ = IntArrayList.EMPTY_LIST;
        byyearday_ = IntArrayList.EMPTY_LIST;
        byweekno_ = IntArrayList.EMPTY_LIST;
        bymonth_ = IntArrayList.EMPTY_LIST;
        bysetpos_ = IntArrayList.EMPTY_LIST;
        wkst_ = 1;
    }

    public static RecurRulePart parseFrom(InputStream inputstream)
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
        return (RecurRulePart)generatedmessagelite;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 249
    //                   1 254
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 196
    //                   6 200;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new RecurRulePart();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = Freq.internalVerifier;
        com.google.protobuf.Internal.EnumVerifier enumverifier = Weekday.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\016\000\001\001\016\016\000\t\000\001\f\000\002\t\001\003\004\002\004\004\003\005\026\006\026\007\026\b\033\t\026\n\026\013\026\f\026\r\026\016\f\004", new Object[] {
            "bitField0_", "freq_", obj, "until_", "count_", "interval_", "bysecond_", "byminute_", "byhour_", "byday_", 
            com/google/protos/calendar/feapi/v1/ByDay, "bymonthday_", "byyearday_", "byweekno_", "bymonth_", "bysetpos_", "wkst_", enumverifier
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/protos/calendar/feapi/v1/RecurRulePart;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/protos/calendar/feapi/v1/RecurRulePart;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/protos/calendar/feapi/v1/RecurRulePart;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new RecurRulePart();
        RecurRulePart recurrulepart = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/calendar/feapi/v1/RecurRulePart, recurrulepart);
    }
}
