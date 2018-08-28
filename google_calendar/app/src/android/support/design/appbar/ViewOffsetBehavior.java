// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.appbar;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package android.support.design.appbar:
//            ViewOffsetHelper

public class ViewOffsetBehavior extends android.support.design.widget.CoordinatorLayout.Behavior
{

    private int tempLeftRightOffset;
    private int tempTopBottomOffset;
    private ViewOffsetHelper viewOffsetHelper;

    public ViewOffsetBehavior()
    {
        tempTopBottomOffset = 0;
        tempLeftRightOffset = 0;
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        tempTopBottomOffset = 0;
        tempLeftRightOffset = 0;
    }

    public int getTopAndBottomOffset()
    {
        if (viewOffsetHelper != null)
        {
            return viewOffsetHelper.offsetTop;
        } else
        {
            return 0;
        }
    }

    protected void layoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        coordinatorlayout.onLayoutChild(view, i);
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        layoutChild(coordinatorlayout, view, i);
        if (viewOffsetHelper == null)
        {
            viewOffsetHelper = new ViewOffsetHelper(view);
        }
        coordinatorlayout = viewOffsetHelper;
        coordinatorlayout.layoutTop = ((ViewOffsetHelper) (coordinatorlayout)).view.getTop();
        coordinatorlayout.layoutLeft = ((ViewOffsetHelper) (coordinatorlayout)).view.getLeft();
        coordinatorlayout.updateOffsets();
        if (tempTopBottomOffset != 0)
        {
            coordinatorlayout = viewOffsetHelper;
            i = tempTopBottomOffset;
            if (((ViewOffsetHelper) (coordinatorlayout)).offsetTop != i)
            {
                coordinatorlayout.offsetTop = i;
                coordinatorlayout.updateOffsets();
            }
            tempTopBottomOffset = 0;
        }
        return true;
    }

    public boolean setTopAndBottomOffset(int i)
    {
        boolean flag = false;
        if (viewOffsetHelper != null)
        {
            ViewOffsetHelper viewoffsethelper = viewOffsetHelper;
            if (viewoffsethelper.offsetTop != i)
            {
                viewoffsethelper.offsetTop = i;
                viewoffsethelper.updateOffsets();
                flag = true;
            }
            return flag;
        } else
        {
            tempTopBottomOffset = i;
            return false;
        }
    }
}
