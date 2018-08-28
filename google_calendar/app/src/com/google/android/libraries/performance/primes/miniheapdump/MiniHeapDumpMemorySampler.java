// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.miniheapdump;

import android.content.SharedPreferences;
import android.util.Base64;
import com.google.android.libraries.performance.primes.PrimesLog;
import com.google.android.libraries.performance.primes.persistent.PersistentStorage;
import com.google.android.libraries.performance.proto.primes.persistent.nano.MemorySample;
import com.google.android.libraries.performance.proto.primes.persistent.nano.PersistentMemorySamples;
import com.google.protobuf.nano.MessageNano;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class MiniHeapDumpMemorySampler
{

    private final double minSampleDistributionFactor = 1.2D;
    private final Random random;
    public final ArrayList samples = new ArrayList();
    private final PersistentStorage storage;
    private final int versionNameHash;

    public MiniHeapDumpMemorySampler(PersistentStorage persistentstorage, PersistentMemorySamples persistentmemorysamples, double d, int i, Random random1)
    {
        storage = persistentstorage;
        versionNameHash = i;
        random = random1;
        persistentstorage = persistentmemorysamples.samples;
        int j = persistentstorage.length;
        for (i = 0; i < j; i++)
        {
            persistentmemorysamples = persistentstorage[i];
            samples.add(((MemorySample) (persistentmemorysamples)).totalPssKb);
        }

    }

    public final void addSample(int i)
    {
        this;
        JVM INSTR monitorenter ;
        PersistentMemorySamples persistentmemorysamples;
        if (samples.size() + 1 > 100)
        {
            samples.remove(random.nextInt(samples.size()));
        }
        samples.add(Integer.valueOf(i));
        persistentmemorysamples = new PersistentMemorySamples();
        persistentmemorysamples.versionNameHash = Integer.valueOf(versionNameHash);
        persistentmemorysamples.samples = new MemorySample[samples.size()];
        i = 0;
_L2:
        if (i >= samples.size())
        {
            break; /* Loop/switch isn't completed */
        }
        MemorySample memorysample = new MemorySample();
        memorysample.totalPssKb = (Integer)samples.get(i);
        persistentmemorysamples.samples[i] = memorysample;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        Object obj = storage;
        if (persistentmemorysamples != null)
        {
            break MISSING_BLOCK_LABEL_155;
        }
        throw new NullPointerException();
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        byte abyte0[];
        byte abyte1[];
        MessageNano messagenano = (MessageNano)persistentmemorysamples;
        i = messagenano.computeSerializedSize();
        messagenano.cachedSize = i;
        abyte0 = new byte[i];
        MessageNano.toByteArray(messagenano, abyte0, 0, abyte0.length);
        abyte1 = new byte[abyte0.length + 1];
        abyte1[0] = 1;
        System.arraycopy(abyte0, 0, abyte1, 1, abyte0.length);
        if (!((PersistentStorage) (obj)).sharedPreferences.edit().putString("primes.miniheapdump.memorySamples", Base64.encodeToString(abyte1, 0)).commit())
        {
            PrimesLog.log(3, "MhdMemorySampler", "Failed to save mini heap dump memory samples.", new Object[0]);
        }
        this;
        JVM INSTR monitorexit ;
    }

    public final double calculateQuantile(int i)
    {
        double d = 0.0D;
        ArrayList arraylist = (ArrayList)samples;
        int l = arraylist.size();
        int j = 0;
        do
        {
            if (j >= l)
            {
                break;
            }
            Object obj = arraylist.get(j);
            int k = j + 1;
            j = k;
            if (i >= ((Integer)obj).intValue())
            {
                d++;
                j = k;
            }
        } while (true);
        return d / (double)samples.size();
    }

    public final boolean canComputePercentile()
    {
        return samples.size() == 100 && minSampleDistributionFactor * (double)((Integer)Collections.min(samples)).intValue() <= (double)((Integer)Collections.max(samples)).intValue();
    }
}
