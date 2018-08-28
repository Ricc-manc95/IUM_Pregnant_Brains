// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.common.base.Predicate;

final class arg._cls1
    implements Predicate
{

    private final String arg$1;

    public final boolean apply(Object obj)
    {
        return ly._cls1.V2AEventsApi.SearchFilter(arg$1, (String)obj);
    }

    (String s)
    {
        arg$1 = s;
    }
}
