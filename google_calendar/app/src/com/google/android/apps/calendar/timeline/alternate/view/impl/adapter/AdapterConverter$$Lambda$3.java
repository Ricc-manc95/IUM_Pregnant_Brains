// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AdapterConverter

final class arg._cls1
    implements Predicate
{

    private final AdapterConverter arg$1;

    public final boolean apply(Object obj)
    {
        return arg$1.isTimedEvent(obj);
    }

    (AdapterConverter adapterconverter)
    {
        arg$1 = adapterconverter;
    }
}
