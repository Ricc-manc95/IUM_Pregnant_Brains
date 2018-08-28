// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import java.util.List;
import java.util.Map;

final class commands
{

    public final List commands;
    public final long hitTimeInMilliseconds;
    public final String path;
    public final Map wireFormatParams;

    public (Map map, long l, String s, List list)
    {
        wireFormatParams = map;
        hitTimeInMilliseconds = l;
        path = s;
        commands = list;
    }
}
