// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.syncadapters.timely.groovesync;

import android.content.SyncResult;
import com.google.common.base.Predicate;

final class 
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        return ((SyncResult)obj).hasError();
    }


    private ()
    {
    }
}