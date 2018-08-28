// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Supplier;

final class arg._cls1
    implements Supplier
{

    private final ObservableReference arg$1;

    public final Object get()
    {
        return arg$1.get();
    }

    ference(ObservableReference observablereference)
    {
        arg$1 = observablereference;
    }
}
