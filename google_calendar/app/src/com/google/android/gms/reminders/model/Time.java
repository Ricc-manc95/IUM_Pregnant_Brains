// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Time
    extends Parcelable, Freezable
{
    public static final class Builder
    {

        public Integer zzcjG;
        public Integer zzcjH;
        public Integer zzcjI;

        public Builder()
        {
        }
    }


    public abstract Integer getHour();

    public abstract Integer getMinute();

    public abstract Integer getSecond();
}
