// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.behavior;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;

// Referenced classes of package android.support.design.behavior:
//            SwipeDismissBehavior

final class dismiss
    implements Runnable
{

    private final boolean dismiss;
    private final SwipeDismissBehavior this$0;
    private final View view;

    public final void run()
    {
        if (viewDragHelper != null && viewDragHelper.continueSettling(true))
        {
            ViewCompat.postOnAnimation(view, this);
        } else
        if (dismiss && listener != null)
        {
            listener.onDismiss(view);
            return;
        }
    }

    er(View view1, boolean flag)
    {
        this$0 = SwipeDismissBehavior.this;
        super();
        view = view1;
        dismiss = flag;
    }
}
