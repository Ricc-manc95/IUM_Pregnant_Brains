// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.view.View;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            SegmentViews

public final class arg._cls1
    implements Consumer
{

    private final SegmentViews arg$1;

    public final void accept(Object obj)
    {
        arg$1.headerView = (View)obj;
    }

    public (SegmentViews segmentviews)
    {
        arg$1 = segmentviews;
    }
}
