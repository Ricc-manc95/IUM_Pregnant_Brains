// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.util;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public final class zza extends AbstractSet
{

    private final ArrayMap zzaTe;

    public zza()
    {
        zzaTe = new ArrayMap();
    }

    public zza(int i)
    {
        zzaTe = new ArrayMap(3);
    }

    private final boolean zza(zza zza1)
    {
        int i = size();
        zzaTe.putAll(zza1.zzaTe);
        return size() > i;
    }

    public final boolean add(Object obj)
    {
        if (zzaTe.containsKey(obj))
        {
            return false;
        } else
        {
            zzaTe.put(obj, obj);
            return true;
        }
    }

    public final boolean addAll(Collection collection)
    {
        if (collection instanceof zza)
        {
            return zza((zza)collection);
        } else
        {
            return super.addAll(collection);
        }
    }

    public final void clear()
    {
        zzaTe.clear();
    }

    public final boolean contains(Object obj)
    {
        return zzaTe.containsKey(obj);
    }

    public final Iterator iterator()
    {
        return zzaTe.keySet().iterator();
    }

    public final boolean remove(Object obj)
    {
        if (!zzaTe.containsKey(obj))
        {
            return false;
        } else
        {
            zzaTe.remove(obj);
            return true;
        }
    }

    public final int size()
    {
        return zzaTe.size();
    }
}
