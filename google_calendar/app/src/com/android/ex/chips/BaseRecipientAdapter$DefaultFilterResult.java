// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

final class paramsList
{

    public final List entries;
    public final LinkedHashMap entryMap;
    public final Set existingDestinations;
    public final List nonAggregatedEntries;
    public final List paramsList;

    public (List list, LinkedHashMap linkedhashmap, List list1, Set set, List list2)
    {
        entries = list;
        entryMap = linkedhashmap;
        nonAggregatedEntries = list1;
        existingDestinations = set;
        paramsList = list2;
    }
}
