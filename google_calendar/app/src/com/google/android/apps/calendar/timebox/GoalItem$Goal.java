// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;


public abstract class Builder
{

    public abstract String getHabitId();

    public abstract Integer getType();

    public abstract boolean isDone();

    public abstract Builder toBuilder();

    public Builder()
    {
    }
}
