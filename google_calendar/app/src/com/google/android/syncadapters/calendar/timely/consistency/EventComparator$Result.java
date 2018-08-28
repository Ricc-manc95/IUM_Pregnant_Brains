// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import com.google.common.collect.ImmutableList;

public final class shouldReport
{

    public final ImmutableList differentFields;
    public final String inconsistencyClass;
    public final boolean shouldReport;

    ()
    {
        differentFields = ImmutableList.of();
        inconsistencyClass = "Unclassified";
        shouldReport = true;
    }

    shouldReport(ImmutableList immutablelist, String s, boolean flag)
    {
        differentFields = immutablelist;
        inconsistencyClass = s;
        shouldReport = flag;
    }
}
