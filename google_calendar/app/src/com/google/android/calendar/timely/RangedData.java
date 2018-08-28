// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


public interface RangedData
{

    public abstract boolean containsDay(int i);

    public abstract String getDebugTag();

    public abstract int getEndDay();

    public abstract int getStartDay();

    public abstract int getToken();

    public abstract void recycle(int i);

    public abstract void setToken(int i);
}
