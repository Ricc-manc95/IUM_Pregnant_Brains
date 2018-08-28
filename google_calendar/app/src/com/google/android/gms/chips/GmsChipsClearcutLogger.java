// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.protobuf.nano.MessageNano;
import java.util.Random;
import social.graph.chips.nano.ChipsExtension;

public class GmsChipsClearcutLogger
{
    static final class ChipsExtensionMessageProducer
        implements com.google.android.gms.clearcut.ClearcutLogger.MessageProducer
    {

        private ChipsExtension chipsExtension;

        public final byte[] toProtoBytes()
        {
            ChipsExtension chipsextension = chipsExtension;
            int i = chipsextension.computeSerializedSize();
            chipsextension.cachedSize = i;
            byte abyte0[] = new byte[i];
            MessageNano.toByteArray(chipsextension, abyte0, 0, abyte0.length);
            return abyte0;
        }

        ChipsExtensionMessageProducer(ChipsExtension chipsextension)
        {
            chipsExtension = chipsextension;
        }
    }


    private static GmsChipsClearcutLogger gmsChipsClearcutLogger;
    private static final Random random = new Random();
    private final ClearcutLogger clearcutLogger;

    private GmsChipsClearcutLogger(Context context, String s)
    {
        clearcutLogger = new ClearcutLogger(context, "CHIPS", s);
    }

    public static GmsChipsClearcutLogger getInstance(Context context, String s)
    {
        com/google/android/gms/chips/GmsChipsClearcutLogger;
        JVM INSTR monitorenter ;
        if (gmsChipsClearcutLogger == null)
        {
            gmsChipsClearcutLogger = new GmsChipsClearcutLogger(context, s);
        }
        com/google/android/gms/chips/GmsChipsClearcutLogger;
        JVM INSTR monitorexit ;
        return gmsChipsClearcutLogger;
        context;
        com/google/android/gms/chips/GmsChipsClearcutLogger;
        JVM INSTR monitorexit ;
        throw context;
    }

    final void logEventWithSampling(ChipsExtension chipsextension, double d)
    {
        if (random.nextDouble() >= d)
        {
            return;
        } else
        {
            (new com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder(clearcutLogger, new ChipsExtensionMessageProducer(chipsextension))).logAsync();
            return;
        }
    }

}
