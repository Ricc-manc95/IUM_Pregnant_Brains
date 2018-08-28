// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.collect;

import com.google.common.base.FinalizableReferenceQueue;
import java.util.Map;

public final class ReferenceCache
{

    public final FinalizableReferenceQueue finalizableReferenceQueue;
    public final Map keyToValueReferenceMap;
    public final Type type;

    public ReferenceCache(Type type1, Map map, FinalizableReferenceQueue finalizablereferencequeue)
    {
        type = type1;
        keyToValueReferenceMap = map;
        finalizableReferenceQueue = finalizablereferencequeue;
    }
}
