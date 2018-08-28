// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableList;

public final class futures
{

    public final boolean allMustSucceed;
    public final ImmutableList futures;

    public (boolean flag, ImmutableList immutablelist)
    {
        allMustSucceed = flag;
        futures = immutablelist;
    }
}
