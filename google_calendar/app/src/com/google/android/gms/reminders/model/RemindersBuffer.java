// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.internal.ref.TaskRef;

public final class RemindersBuffer extends AbstractDataBuffer
{

    public RemindersBuffer(DataHolder dataholder)
    {
        super(dataholder);
    }

    public final Object get(int i)
    {
        return new TaskRef(zzaKT, i);
    }
}
