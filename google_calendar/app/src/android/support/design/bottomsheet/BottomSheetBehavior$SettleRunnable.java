// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.bottomsheet;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;

// Referenced classes of package android.support.design.bottomsheet:
//            BottomSheetBehavior

final class targetState
    implements Runnable
{

    private final int targetState;
    private final BottomSheetBehavior this$0;
    private final View view;

    public final void run()
    {
        if (viewDragHelper != null && viewDragHelper.continueSettling(true))
        {
            ViewCompat.postOnAnimation(view, this);
            return;
        } else
        {
            setStateInternal(targetState);
            return;
        }
    }

    (View view1, int i)
    {
        this$0 = BottomSheetBehavior.this;
        super();
        view = view1;
        targetState = i;
    }
}
