// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap;
import java.util.List;
import java.util.Map;

public final class ParseResult
{

    public final IntObjectMap classInstances;
    public final IntObjectMap classes;
    public final Map instancesFound;
    public final List roots;

    public ParseResult(IntObjectMap intobjectmap, IntObjectMap intobjectmap1, List list, Map map)
    {
        classes = intobjectmap;
        classInstances = intobjectmap1;
        roots = list;
        instancesFound = map;
    }
}
