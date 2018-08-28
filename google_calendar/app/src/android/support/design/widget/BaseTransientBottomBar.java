// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.support.design.behavior.SwipeDismissBehavior;
import android.support.design.snackbar.ContentViewCallback;
import android.view.View;
import android.view.ViewGroup;

public class BaseTransientBottomBar extends android.support.design.snackbar.BaseTransientBottomBar
{

    protected BaseTransientBottomBar(ViewGroup viewgroup, View view, ContentViewCallback contentviewcallback)
    {
        super(viewgroup, view, contentviewcallback);
    }

    protected final SwipeDismissBehavior getNewBehavior()
    {
        return new Behavior();
    }

    protected final int getSnackbarBaseLayoutResId()
    {
        return !hasSnackbarStyleAttr() ? 0x7f050044 : 0x7f0500bb;
    }

    private class Behavior extends android.support.design.widget.SwipeDismissBehavior
    {

        private final android.support.design.snackbar.BehaviorDelegate _flddelegate = new android.support.design.snackbar.BehaviorDelegate(this);

        private final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, SnackbarBaseLayout snackbarbaselayout, MotionEvent motionevent)
        {
            _flddelegate.onInterceptTouchEvent(coordinatorlayout, snackbarbaselayout, motionevent);
            return super.onInterceptTouchEvent(coordinatorlayout, snackbarbaselayout, motionevent);
        }

        public final boolean canSwipeDismissView(View view)
        {
            return view instanceof android.support.design.snackbar.SnackbarBaseLayout;
        }

        public final volatile boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
        {
            return onInterceptTouchEvent(coordinatorlayout, (SnackbarBaseLayout)view, motionevent);
        }

        public Behavior()
        {
            _flddelegate.managerCallback = managerCallback;
        }

        private class SnackbarBaseLayout extends android.support.design.snackbar.SnackbarBaseLayout
        {

            protected SnackbarBaseLayout(Context context)
            {
                super(context);
            }

            protected SnackbarBaseLayout(Context context, AttributeSet attributeset)
            {
                super(context, attributeset);
            }
        }

    }

}
