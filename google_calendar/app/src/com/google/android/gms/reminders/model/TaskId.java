// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface TaskId
    extends Parcelable, Freezable
{
    public static final class Builder
    {

        public String zzcjE;

        public Builder()
        {
        }
    }


    public abstract String getClientAssignedId();

    public abstract String getClientAssignedThreadId();
}
