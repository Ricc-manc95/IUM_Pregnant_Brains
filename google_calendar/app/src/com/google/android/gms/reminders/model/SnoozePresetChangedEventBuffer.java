// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbbn;

public final class SnoozePresetChangedEventBuffer extends AbstractDataBuffer
    implements Result
{

    private final Status zzahG;

    public SnoozePresetChangedEventBuffer(DataHolder dataholder)
    {
        super(dataholder);
        zzahG = new Status(dataholder.zzaEP);
    }

    public final Object get(int i)
    {
        return new zzbbn(zzaKT, i);
    }

    public final Status getStatus()
    {
        return zzahG;
    }
}
