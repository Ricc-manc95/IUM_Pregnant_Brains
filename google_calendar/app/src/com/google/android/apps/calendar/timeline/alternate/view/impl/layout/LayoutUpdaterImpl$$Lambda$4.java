// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import java.util.Comparator;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutUpdaterImpl

final class ms
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        return LayoutUpdaterImpl.lambda$onGetChildDrawingOrder$2$LayoutUpdaterImpl((ms)obj, (ms)obj1);
    }


    private ms()
    {
    }
}
