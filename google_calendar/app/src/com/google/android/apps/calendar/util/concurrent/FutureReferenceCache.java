// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import com.google.android.apps.calendar.util.collect.ReferenceCache;
import java.util.Map;

public final class FutureReferenceCache
{

    public final Map pendingFutureMap;
    public final ReferenceCache valueReferenceCache;

    public FutureReferenceCache(Map map, ReferenceCache referencecache)
    {
        pendingFutureMap = map;
        valueReferenceCache = referencecache;
    }
}
