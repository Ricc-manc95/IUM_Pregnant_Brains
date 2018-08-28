// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;


public interface Loader
{

    public abstract Object getResult();

    public abstract boolean isFinished();

    public abstract boolean isFinishedSuccessfully();

    public abstract boolean isRunning();

    public abstract void load();

    public abstract void setCallback(Callback callback);
}
