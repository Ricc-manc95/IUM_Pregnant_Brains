// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.appbar;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.OverScroller;

// Referenced classes of package android.support.design.appbar:
//            HeaderBehavior

final class layout
    implements Runnable
{

    private final View layout;
    private final CoordinatorLayout parent;
    private final HeaderBehavior this$0;

    public final void run()
    {
label0:
        {
            if (layout != null && scroller != null)
            {
                if (!scroller.computeScrollOffset())
                {
                    break label0;
                }
                setHeaderTopBottomOffset(parent, layout, scroller.getCurrY(), 0x80000000, 0x7fffffff);
                ViewCompat.postOnAnimation(layout, this);
            }
            return;
        }
        onFlingFinished(parent, layout);
    }

    (CoordinatorLayout coordinatorlayout, View view)
    {
        this$0 = HeaderBehavior.this;
        super();
        parent = coordinatorlayout;
        layout = view;
    }
}
