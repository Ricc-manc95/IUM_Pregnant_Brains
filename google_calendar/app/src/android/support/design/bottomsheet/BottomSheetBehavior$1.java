// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.bottomsheet;

import android.view.View;

// Referenced classes of package android.support.design.bottomsheet:
//            BottomSheetBehavior

final class val.finalState
    implements Runnable
{

    private final BottomSheetBehavior this$0;
    private final View val$child;
    private final int val$finalState;

    public final void run()
    {
        startSettlingAnimation(val$child, val$finalState);
    }

    ()
    {
        this$0 = final_bottomsheetbehavior;
        val$child = view;
        val$finalState = I.this;
        super();
    }
}
