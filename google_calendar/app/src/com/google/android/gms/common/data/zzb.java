// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package com.google.android.gms.common.data:
//            DataBuffer

public final class zzb
    implements Iterator
{

    private final DataBuffer zzaNN;
    private int zzaNO;

    public zzb(DataBuffer databuffer)
    {
        if (databuffer == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            zzaNN = (DataBuffer)databuffer;
            zzaNO = -1;
            return;
        }
    }

    public final boolean hasNext()
    {
        return zzaNO < zzaNN.getCount() - 1;
    }

    public final Object next()
    {
        if (!hasNext())
        {
            int i = zzaNO;
            throw new NoSuchElementException((new StringBuilder(46)).append("Cannot advance the iterator beyond ").append(i).toString());
        } else
        {
            DataBuffer databuffer = zzaNN;
            int j = zzaNO + 1;
            zzaNO = j;
            return databuffer.get(j);
        }
    }

    public final void remove()
    {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
