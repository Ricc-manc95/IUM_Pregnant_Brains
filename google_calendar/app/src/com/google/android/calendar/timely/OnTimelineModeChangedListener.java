// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


public interface OnTimelineModeChangedListener
{

    public abstract void onModeChangeFinished();

    public abstract void onModeChanged(int i);

    public abstract void onModeChanged(int i, int j, int k, int l, int i1);
}
