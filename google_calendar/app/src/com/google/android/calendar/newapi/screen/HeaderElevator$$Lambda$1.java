// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.support.v4.widget.NestedScrollView;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            HeaderElevator

final class arg._cls2
    implements android.view.ChangedListener
{

    private final HeaderElevator arg$1;
    private final NestedScrollView arg$2;

    public final void onScrollChanged()
    {
        HeaderElevator headerelevator = arg$1;
        boolean flag;
        if (arg$2.getScrollY() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        headerelevator.elevate(flag);
    }

    (HeaderElevator headerelevator, NestedScrollView nestedscrollview)
    {
        arg$1 = headerelevator;
        arg$2 = nestedscrollview;
    }
}
