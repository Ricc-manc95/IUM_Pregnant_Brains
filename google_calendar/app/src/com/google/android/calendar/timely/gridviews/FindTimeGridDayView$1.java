// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewParent;
import com.google.android.calendar.timely.PagedScrollView;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            FindTimeGridDayView, GridDayView

public final class val.animate
    implements Runnable
{

    private final FindTimeGridDayView this$0;
    private final boolean val$animate;

    public final void run()
    {
        Object obj = (PagedScrollView)getParent().getParent();
        int i = Math.max(0, getFirstChipCenterYCoordinate(-1) - ((View) (obj)).getHeight() / 2);
        if (val$animate)
        {
            obj = ObjectAnimator.ofInt(obj, "scrollY", new int[] {
                i
            }).setDuration(300L);
            ((ObjectAnimator) (obj)).setInterpolator(FindTimeGridDayView.AUTO_SCROLL_INTERPOLATOR);
            ((ObjectAnimator) (obj)).start();
            return;
        } else
        {
            ((View) (obj)).scrollTo(0, i);
            return;
        }
    }

    public ()
    {
        this$0 = final_findtimegriddayview;
        val$animate = Z.this;
        super();
    }
}
