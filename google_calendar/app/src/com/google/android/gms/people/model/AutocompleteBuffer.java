// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.people.model;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzayk;

// Referenced classes of package com.google.android.gms.people.model:
//            DataBufferWithSyncInfo

public final class AutocompleteBuffer extends DataBufferWithSyncInfo
{

    public AutocompleteBuffer(DataHolder dataholder)
    {
        super(dataholder);
    }

    public final Object get(int i)
    {
        return new zzayk(this, zzaKT, i, super.zzaKT.zzaNX);
    }

    public final String toString()
    {
        int i;
        if (super.zzaKT == null)
        {
            i = 0;
        } else
        {
            i = super.zzaKT.zzaNZ;
        }
        return (new StringBuilder(33)).append("AutocompleteList:size=").append(i).toString();
    }
}
