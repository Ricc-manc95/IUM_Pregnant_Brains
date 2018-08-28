// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.inject;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutUpdaterImpl;
import com.google.common.base.Supplier;
import java.util.RandomAccess;

final class arg._cls1
    implements Supplier
{

    private final LayoutUpdaterImpl arg$1;

    public final Object get()
    {
        java.util.List list = arg$1.lastVirtualViews;
        com.google.common.base.Function function = com.google.android.apps.calendar.timeline.alternate.view.impl.layout.ance;
        if (list instanceof RandomAccess)
        {
            return new com.google.common.collect.t(list, function);
        } else
        {
            return new com.google.common.collect.<init>(list, function);
        }
    }

    (LayoutUpdaterImpl layoutupdaterimpl)
    {
        arg$1 = layoutupdaterimpl;
    }
}
