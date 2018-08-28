// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.support.design.snackbar.BaseTransientBottomBar;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package android.support.design.widget:
//            SwipeDismissBehavior, BaseTransientBottomBar, CoordinatorLayout

final class Callback extends SwipeDismissBehavior
{

    private final android.support.design.snackbar.elegate _flddelegate = new android.support.design.snackbar.elegate(this);

    private final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, aseLayout aselayout, MotionEvent motionevent)
    {
        _flddelegate.onInterceptTouchEvent(coordinatorlayout, aselayout, motionevent);
        return super.onInterceptTouchEvent(coordinatorlayout, aselayout, motionevent);
    }

    public final boolean canSwipeDismissView(View view)
    {
        return view instanceof android.support.design.snackbar.aseLayout;
    }

    public final volatile boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        return onInterceptTouchEvent(coordinatorlayout, (aseLayout)view, motionevent);
    }

    public aseLayout(android.support.design.widget.BaseTransientBottomBar basetransientbottombar)
    {
        _flddelegate.managerCallback = ((BaseTransientBottomBar) (basetransientbottombar)).managerCallback;
    }
}
