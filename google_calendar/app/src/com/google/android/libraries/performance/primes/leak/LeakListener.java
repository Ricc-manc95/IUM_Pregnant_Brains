// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.leak;

import java.util.List;

public interface LeakListener
{

    public abstract void onBatchComplete(boolean flag);

    public abstract void onHeapDumpResult(List list);

    public abstract void onLeaked(String s);

    public abstract void onReleased(String s);
}
