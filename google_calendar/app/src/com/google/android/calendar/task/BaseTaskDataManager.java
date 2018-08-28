// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;


public interface BaseTaskDataManager
{

    public abstract void includeData(int i);

    public abstract void onDestroy();

    public abstract void refreshComplete();

    public abstract void refreshStart();

    public abstract void updateToday();
}
