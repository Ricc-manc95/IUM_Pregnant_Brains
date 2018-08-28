// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.clearcut.impl;

import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;

abstract class BaseClearcutLogEventBuilderImpl
    implements GcoreClearcutLogEventBuilder
{

    public final com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder logEventBuilder;

    BaseClearcutLogEventBuilderImpl(ClearcutLogger clearcutlogger, com.google.android.gms.clearcut.ClearcutLogger.MessageProducer messageproducer)
    {
        logEventBuilder = new com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder(clearcutlogger, messageproducer);
    }

    BaseClearcutLogEventBuilderImpl(ClearcutLogger clearcutlogger, byte abyte0[])
    {
        logEventBuilder = new com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder(clearcutlogger, abyte0);
    }

    public GcoreClearcutLogEventBuilder setEventCode(int i)
    {
        logEventBuilder.zzaHz.eventCode = i;
        return this;
    }

    public GcoreClearcutLogEventBuilder setLogSourceName(String s)
    {
        logEventBuilder.zzaHj = s;
        return this;
    }

    public GcoreClearcutLogEventBuilder setUploadAccountName(String s)
    {
        com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder logeventbuilder = logEventBuilder;
        if (ClearcutLogger.zzh(logeventbuilder.zzaHB))
        {
            throw new IllegalArgumentException("setUploadAccountName forbidden on anonymous logger");
        } else
        {
            logeventbuilder.zzaHl = s;
            return this;
        }
    }
}
