// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net;

import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Timestamp;

public final class TimestampUtils
{

    public static Duration durationFromMillis(long l)
    {
        com.google.protobuf.Duration.Builder builder = (com.google.protobuf.Duration.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)Duration.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        long l1 = l / 1000L;
        builder.copyOnWrite();
        ((Duration)builder.instance).seconds_ = l1;
        int i = (int)((l % 1000L) * 0xf4240L);
        builder.copyOnWrite();
        ((Duration)builder.instance).nanos_ = i;
        return (Duration)(GeneratedMessageLite)builder.build();
    }

    public static Timestamp timestampFromMillis(long l)
    {
        com.google.protobuf.Timestamp.Builder builder = (com.google.protobuf.Timestamp.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)Timestamp.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        long l1 = l / 1000L;
        builder.copyOnWrite();
        ((Timestamp)builder.instance).seconds_ = l1;
        int i = (int)((l % 1000L) * 0xf4240L);
        builder.copyOnWrite();
        ((Timestamp)builder.instance).nanos_ = i;
        return (Timestamp)(GeneratedMessageLite)builder.build();
    }
}
