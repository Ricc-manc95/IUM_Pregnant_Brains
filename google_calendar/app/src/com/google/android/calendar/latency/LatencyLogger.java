// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.latency;


public interface LatencyLogger
{

    public abstract void markAt(int i);

    public abstract void markAt(int i, int j);

    public abstract void markAt(int i, int j, String s);

    public abstract void markAt(int i, String s);
}
