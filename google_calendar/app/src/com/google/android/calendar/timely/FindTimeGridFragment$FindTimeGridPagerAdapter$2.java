// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridViewPager, FindTimeGridSlabPage, FindTimeGridFragment

final class val.container
    implements val.container
{

    private final val.container this$1;
    private final ViewGroup val$container;

    public final void onSlabBarHeightUpdated(FindTimeGridSlabPage findtimegridslabpage)
    {
        if (((FindTimeGridViewPager)val$container).getCurrentItem() == ((Integer)findtimegridslabpage.getTag()).intValue())
        {
            updateMainContentMargin(findtimegridslabpage);
        }
    }

    ()
    {
        this$1 = final_;
        val$container = ViewGroup.this;
        super();
    }
}
