// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


public final class PrimesTraceConfigurations
{

    public static final PrimesTraceConfigurations DEFAULT;
    public final boolean isEnabled;
    public final int maxTracingBufferSize;
    public final int minSpanDurationMs;
    public final float samplingPropability;

    PrimesTraceConfigurations(boolean flag, float f, int i, int j)
    {
        isEnabled = flag;
        samplingPropability = f;
        minSpanDurationMs = i;
        maxTracingBufferSize = j;
    }

    static 
    {
        Builder builder = new Builder();
        builder.samplingPropability = 0.5F;
        builder.minSpanDurationMs = 5;
        builder.maxTracingBufferSize = 1000;
        DEFAULT = new PrimesTraceConfigurations(false, builder.samplingPropability, builder.minSpanDurationMs, builder.maxTracingBufferSize);
    }

    private class Builder
    {

        public int maxTracingBufferSize;
        public int minSpanDurationMs;
        public float samplingPropability;

        Builder()
        {
        }
    }

}
