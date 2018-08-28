// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;

import com.google.protobuf.GeneratedMessageLite;

// Referenced classes of package com.google.android.libraries.streamz:
//            CellValue

final class CounterCellValue
    implements CellValue
{

    private long value;

    CounterCellValue()
    {
        value = 0L;
    }

    public final void record(Object obj)
    {
        obj = (Long)obj;
        value = value + ((Long) (obj)).longValue();
    }

    public final String toString()
    {
        return Long.toString(value);
    }

    public final com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Value toValueProto()
    {
        com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Value.Builder builder = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Value.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Value.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        long l = value;
        builder.copyOnWrite();
        com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Value value1 = (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Value)builder.instance;
        value1.valueCase_ = 1;
        value1.value_ = Long.valueOf(l);
        return (com.google.frameworks.client.streamz.StreamzObjectsProto.IncrementBatch.Increment.Value)(GeneratedMessageLite)builder.build();
    }
}
