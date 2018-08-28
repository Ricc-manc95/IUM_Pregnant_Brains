// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import java.util.Iterator;

// Referenced classes of package com.google.android.gms.common.data:
//            DataBuffer, DataHolder, zzb

public abstract class AbstractDataBuffer
    implements DataBuffer
{

    public final DataHolder zzaKT;

    public AbstractDataBuffer(DataHolder dataholder)
    {
        zzaKT = dataholder;
    }

    public final void close()
    {
        if (zzaKT != null)
        {
            zzaKT.close();
        }
    }

    public final int getCount()
    {
        if (zzaKT == null)
        {
            return 0;
        } else
        {
            return zzaKT.zzaNZ;
        }
    }

    public Iterator iterator()
    {
        return new zzb(this);
    }

    public final void release()
    {
        if (zzaKT != null)
        {
            zzaKT.close();
        }
    }
}
