// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.support.design.behavior.SwipeDismissBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package android.support.design.snackbar:
//            BaseTransientBottomBar

public static class elegate extends SwipeDismissBehavior
{

    public final elegate _flddelegate = new elegate(this);

    public final boolean canSwipeDismissView(View view)
    {
        return view instanceof aseLayout;
    }

    public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        _flddelegate.onInterceptTouchEvent(coordinatorlayout, view, motionevent);
        return super.onInterceptTouchEvent(coordinatorlayout, view, motionevent);
    }

    public elegate()
    {
    }
}
