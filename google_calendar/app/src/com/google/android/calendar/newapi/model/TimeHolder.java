// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import android.content.Context;

public interface TimeHolder
{

    public abstract long getEnd(Context context);

    public abstract long getStart(Context context);

    public abstract boolean isAllDay();
}
