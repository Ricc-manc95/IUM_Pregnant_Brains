// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.api;


public interface ItemAdapter
{

    public abstract int compareType(Object obj, Object obj1);

    public abstract int getEndDay(Object obj);

    public abstract Object getKey(Object obj);

    public abstract long getLocalEndMillis(Object obj);

    public abstract long getLocalStartMillis(Object obj);

    public abstract int getStartDay(Object obj);

    public abstract boolean isAllDay(Object obj);

    public abstract Object moveTime(Object obj, long l);
}
