// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowInsets;
import java.util.List;

// Referenced classes of package android.support.design.appbar:
//            ViewOffsetBehavior

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior
{

    public int overlayTop;
    public final Rect tempRect1;
    private final Rect tempRect2;
    public int verticalLayoutGap;

    public HeaderScrollingViewBehavior()
    {
        tempRect1 = new Rect();
        tempRect2 = new Rect();
        verticalLayoutGap = 0;
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        tempRect1 = new Rect();
        tempRect2 = new Rect();
        verticalLayoutGap = 0;
    }

    abstract View findFirstDependency(List list);

    float getOverlapRatioForOffset(View view)
    {
        return 1.0F;
    }

    int getScrollRange(View view)
    {
        return view.getMeasuredHeight();
    }

    protected final void layoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        View view1 = findFirstDependency(coordinatorlayout.getDependencies(view));
        if (view1 == null) goto _L2; else goto _L1
_L1:
        android.support.design.widget.CoordinatorLayout.LayoutParams layoutparams = (android.support.design.widget.CoordinatorLayout.LayoutParams)view.getLayoutParams();
        Rect rect = tempRect1;
        rect.set(coordinatorlayout.getPaddingLeft() + layoutparams.leftMargin, view1.getBottom() + layoutparams.topMargin, coordinatorlayout.getWidth() - coordinatorlayout.getPaddingRight() - layoutparams.rightMargin, (coordinatorlayout.getHeight() + view1.getBottom()) - coordinatorlayout.getPaddingBottom() - layoutparams.bottomMargin);
        WindowInsetsCompat windowinsetscompat = coordinatorlayout.mLastInsets;
        if (windowinsetscompat != null && ViewCompat.getFitsSystemWindows(coordinatorlayout) && !ViewCompat.getFitsSystemWindows(view))
        {
            int j = rect.left;
            rect.left = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetLeft() + j;
            rect.right = rect.right - ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetRight();
        }
        coordinatorlayout = tempRect2;
        int i1 = layoutparams.gravity;
        int k = i1;
        if (i1 == 0)
        {
            k = 0x800033;
        }
        Gravity.apply(k, view.getMeasuredWidth(), view.getMeasuredHeight(), rect, coordinatorlayout, i);
        if (overlayTop != 0) goto _L4; else goto _L3
_L3:
        i = 0;
_L5:
        view.layout(((Rect) (coordinatorlayout)).left, ((Rect) (coordinatorlayout)).top - i, ((Rect) (coordinatorlayout)).right, ((Rect) (coordinatorlayout)).bottom - i);
        verticalLayoutGap = ((Rect) (coordinatorlayout)).top - view1.getBottom();
        return;
_L4:
        int l = (int)(getOverlapRatioForOffset(view1) * (float)overlayTop);
        int j1 = overlayTop;
        if (l < 0)
        {
            i = 0;
        } else
        {
            i = j1;
            if (l <= j1)
            {
                i = l;
            }
        }
        if (true) goto _L5; else goto _L2
_L2:
        super.layoutChild(coordinatorlayout, view, i);
        verticalLayoutGap = 0;
        return;
    }

    public boolean onMeasureChild(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k, int l)
    {
        int j1 = view.getLayoutParams().height;
        if (j1 == -1 || j1 == -2)
        {
            View view1 = findFirstDependency(coordinatorlayout.getDependencies(view));
            if (view1 != null)
            {
                if (ViewCompat.getFitsSystemWindows(view1) && !ViewCompat.getFitsSystemWindows(view))
                {
                    ViewCompat.setFitsSystemWindows(view, true);
                    if (ViewCompat.getFitsSystemWindows(view))
                    {
                        view.requestLayout();
                        return true;
                    }
                }
                int i1 = android.view.View.MeasureSpec.getSize(k);
                k = i1;
                if (i1 == 0)
                {
                    k = coordinatorlayout.getHeight();
                }
                int k1 = view1.getMeasuredHeight();
                int l1 = getScrollRange(view1);
                if (j1 == -1)
                {
                    i1 = 0x40000000;
                } else
                {
                    i1 = 0x80000000;
                }
                coordinatorlayout.onMeasureChild(view, i, j, android.view.View.MeasureSpec.makeMeasureSpec(l1 + (k - k1), i1), l);
                return true;
            }
        }
        return false;
    }
}
