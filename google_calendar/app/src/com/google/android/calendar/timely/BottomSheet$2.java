// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;

// Referenced classes of package com.google.android.calendar.timely:
//            BottomSheet

final class val.positiveClicked
    implements Runnable
{

    private final BottomSheet this$0;
    private final boolean val$positiveClicked;
    private final boolean val$triggerAction;

    public final void run()
    {
        setVisibility(8);
        if (val$triggerAction)
        {
            if (val$positiveClicked)
            {
                onPositiveButtonClicked();
            } else
            {
                onNegativeButtonClicked();
            }
        }
        mainLayer.clearAnimation();
        if (fadeLayer != null)
        {
            fadeLayer.clearAnimation();
        }
    }

    ()
    {
        this$0 = final_bottomsheet;
        val$triggerAction = flag;
        val$positiveClicked = Z.this;
        super();
    }
}
