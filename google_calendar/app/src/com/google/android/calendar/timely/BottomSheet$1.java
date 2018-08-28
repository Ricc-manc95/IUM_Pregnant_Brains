// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;
import android.view.animation.Animation;

// Referenced classes of package com.google.android.calendar.timely:
//            BottomSheet

final class val.fadeIn
    implements Runnable
{

    private final BottomSheet this$0;
    private final Animation val$bottomUp;
    private final Animation val$fadeIn;

    public final void run()
    {
        onShow();
        setVisibility(0);
        mainLayer.startAnimation(val$bottomUp);
        if (val$fadeIn != null)
        {
            fadeLayer.startAnimation(val$fadeIn);
        }
        mRunnable = null;
    }

    ()
    {
        this$0 = final_bottomsheet;
        val$bottomUp = animation;
        val$fadeIn = Animation.this;
        super();
    }
}
