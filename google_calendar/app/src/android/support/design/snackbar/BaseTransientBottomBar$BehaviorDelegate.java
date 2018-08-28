// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.support.design.behavior.SwipeDismissBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package android.support.design.snackbar:
//            SnackbarManager

public final class ce
{

    public managerCallback managerCallback;

    public final void onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 36
    //                   0 37
    //                   1 82
    //                   2 36
    //                   3 82;
           goto _L1 _L2 _L3 _L1 _L3
_L1:
        return;
_L2:
        if (coordinatorlayout.isPointInChildBounds(view, (int)motionevent.getX(), (int)motionevent.getY()))
        {
            if (SnackbarManager.snackbarManager == null)
            {
                SnackbarManager.snackbarManager = new SnackbarManager();
            }
            SnackbarManager.snackbarManager.pauseTimeout(managerCallback);
            return;
        }
          goto _L1
_L3:
        if (SnackbarManager.snackbarManager == null)
        {
            SnackbarManager.snackbarManager = new SnackbarManager();
        }
        SnackbarManager.snackbarManager.restoreTimeoutIfPaused(managerCallback);
        return;
    }

    public (SwipeDismissBehavior swipedismissbehavior)
    {
        swipedismissbehavior.alphaStartSwipeDistance = Math.min(Math.max(0.0F, 0.1F), 1.0F);
        swipedismissbehavior.alphaEndSwipeDistance = Math.min(Math.max(0.0F, 0.6F), 1.0F);
        swipedismissbehavior.swipeDirection = 0;
    }
}
