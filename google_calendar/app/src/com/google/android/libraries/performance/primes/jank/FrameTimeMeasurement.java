// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.jank;

import logs.proto.wireless.performance.mobile.nano.JankMetric;

public interface FrameTimeMeasurement
{

    public abstract void addFrame(int i, int j);

    public abstract JankMetric getMetric();

    public abstract boolean isMetricReadyToBeSent();
}
