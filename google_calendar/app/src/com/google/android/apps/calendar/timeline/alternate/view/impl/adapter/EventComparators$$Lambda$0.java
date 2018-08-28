// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import java.util.Comparator;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterEvent

public final class arg._cls1
    implements Comparator
{

    private final Comparator arg$1;

    public final int compare(Object obj, Object obj1)
    {
        Comparator comparator = arg$1;
        obj = (AdapterEvent)obj;
        obj1 = (AdapterEvent)obj1;
        return comparator.compare(((AdapterEvent) (obj)).getItem(), ((AdapterEvent) (obj1)).getItem());
    }

    public (Comparator comparator)
    {
        arg$1 = comparator;
    }
}
