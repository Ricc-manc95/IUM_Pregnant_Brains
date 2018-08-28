// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.design.appbar.AppBarLayout;
import android.support.design.bottomsheet.BottomSheetBehavior;
import android.support.design.internal.DescendantOffsetUtils;
import android.support.design.internal.VisibilityAwareImageButton;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

// Referenced classes of package android.support.design.floatingactionbutton:
//            FloatingActionButton

public static class ehavior_Layout_behavior_autoHide extends android.support.design.widget..BaseBehavior
{

    private boolean autoHideEnabled;
    private Rect tmpRect;

    private final boolean shouldUpdateVisibility(View view, FloatingActionButton floatingactionbutton)
    {
        android.support.design.widget.  = (android.support.design.widget.)floatingactionbutton.getLayoutParams();
        if (!autoHideEnabled)
        {
            return false;
        }
        if (.chorId != view.getId())
        {
            return false;
        }
        return ((VisibilityAwareImageButton) (floatingactionbutton)).userSetVisibility == 0;
    }

    private final boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, FloatingActionButton floatingactionbutton)
    {
        if (!shouldUpdateVisibility(appbarlayout, floatingactionbutton))
        {
            return false;
        }
        if (tmpRect == null)
        {
            tmpRect = new Rect();
        }
        floatingactionbutton = tmpRect;
        DescendantOffsetUtils.getDescendantRect(coordinatorlayout, appbarlayout, floatingactionbutton);
        int i = ((Rect) (floatingactionbutton)).bottom;
        throw new NoSuchMethodError();
    }

    private final boolean updateFabVisibilityForBottomSheet(View view, FloatingActionButton floatingactionbutton)
    {
        if (!shouldUpdateVisibility(view, floatingactionbutton))
        {
            return false;
        }
        android.support.design.widget.  = (android.support.design.widget.)floatingactionbutton.getLayoutParams();
        int i = view.getTop();
        int j = floatingactionbutton.getHeight() / 2;
        if (i < .Margin + j)
        {
            floatingactionbutton.hide(null, false);
        } else
        {
            floatingactionbutton.show(null, false);
        }
        return true;
    }

    public boolean getInsetDodgeRect(CoordinatorLayout coordinatorlayout, FloatingActionButton floatingactionbutton, Rect rect)
    {
        coordinatorlayout = floatingactionbutton.shadowPadding;
        rect.set(floatingactionbutton.getLeft() + ((Rect) (coordinatorlayout)).left, floatingactionbutton.getTop() + ((Rect) (coordinatorlayout)).top, floatingactionbutton.getRight() - ((Rect) (coordinatorlayout)).right, floatingactionbutton.getBottom() - ((Rect) (coordinatorlayout)).bottom);
        return true;
    }

    public final volatile boolean getInsetDodgeRect(CoordinatorLayout coordinatorlayout, View view, Rect rect)
    {
        return getInsetDodgeRect(coordinatorlayout, (FloatingActionButton)view, rect);
    }

    public void onAttachedToLayoutParams(android.support.design.widget..BaseBehavior basebehavior)
    {
        if (basebehavior.geInsetEdges == 0)
        {
            basebehavior.geInsetEdges = 80;
        }
    }

    public boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, FloatingActionButton floatingactionbutton, View view)
    {
        if (view instanceof AppBarLayout)
        {
            updateFabVisibilityForAppBarLayout(coordinatorlayout, (AppBarLayout)view, floatingactionbutton);
        } else
        {
            coordinatorlayout = view.getLayoutParams();
            boolean flag;
            if (coordinatorlayout instanceof android.support.design.widget.)
            {
                flag = ((android.support.design.widget.)coordinatorlayout).havior instanceof BottomSheetBehavior;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                updateFabVisibilityForBottomSheet(view, floatingactionbutton);
                return false;
            }
        }
        return false;
    }

    public final volatile boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, View view, View view1)
    {
        return onDependentViewChanged(coordinatorlayout, (FloatingActionButton)view, view1);
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorlayout, FloatingActionButton floatingactionbutton, int i)
    {
        List list;
        int j;
        boolean flag;
        int k;
        flag = false;
        list = coordinatorlayout.getDependencies(floatingactionbutton);
        k = list.size();
        j = 0;
_L7:
        if (j >= k) goto _L2; else goto _L1
_L1:
        Object obj = (View)list.get(j);
        if (!(obj instanceof AppBarLayout)) goto _L4; else goto _L3
_L3:
        if (!updateFabVisibilityForAppBarLayout(coordinatorlayout, (AppBarLayout)obj, floatingactionbutton)) goto _L5; else goto _L2
_L2:
        coordinatorlayout.onLayoutChild(floatingactionbutton, i);
        Rect rect = floatingactionbutton.shadowPadding;
        if (rect != null && rect.centerX() > 0 && rect.centerY() > 0)
        {
            obj = (android.support.design.widget..shadowPadding)floatingactionbutton.getLayoutParams();
            android.view.atingActionButton atingactionbutton;
            boolean flag1;
            if (floatingactionbutton.getRight() >= coordinatorlayout.getWidth() - ((android.support.design.widget..getRight) (obj)).htMargin)
            {
                i = rect.right;
            } else
            if (floatingactionbutton.getLeft() <= ((android.support.design.widget..getLeft) (obj)).tMargin)
            {
                i = -rect.left;
            } else
            {
                i = 0;
            }
            if (floatingactionbutton.getBottom() >= coordinatorlayout.getHeight() - ((android.support.design.widget..getBottom) (obj)).tomMargin)
            {
                j = rect.bottom;
            } else
            {
                j = ((flag) ? 1 : 0);
                if (floatingactionbutton.getTop() <= ((android.support.design.widget..getTop) (obj)).Margin)
                {
                    j = -rect.top;
                }
            }
            if (j != 0)
            {
                ViewCompat.offsetTopAndBottom(floatingactionbutton, j);
            }
            if (i != 0)
            {
                ViewCompat.offsetLeftAndRight(floatingactionbutton, i);
            }
        }
        return true;
_L4:
        atingactionbutton = ((View) (obj)).getLayoutParams();
        if (atingactionbutton instanceof android.support.design.widget.Margin)
        {
            flag1 = ((android.support.design.widget.Margin)atingactionbutton).havior instanceof BottomSheetBehavior;
        } else
        {
            flag1 = false;
        }
        if (flag1 && updateFabVisibilityForBottomSheet(((View) (obj)), floatingactionbutton)) goto _L2; else goto _L5
_L5:
        j++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public volatile boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        return onLayoutChild(coordinatorlayout, (FloatingActionButton)view, i);
    }

    public ()
    {
        autoHideEnabled = true;
    }

    public autoHideEnabled(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, ehavior_Layout);
        autoHideEnabled = context.getBoolean(ehavior_Layout_behavior_autoHide, true);
        context.recycle();
    }
}
