// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewTreeObserver;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip:
//            TooltipFragment

final class val.activityRoot
    implements android.view.nGlobalLayoutListener
{

    public final TooltipFragment this$0;
    private final View val$activityRoot;

    public final void onGlobalLayout()
    {
        val$activityRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        class .Lambda._cls0
            implements Runnable
        {

            private final TooltipFragment._cls1 arg$1;

            public final void run()
            {
                arg$1.this$0.init();
            }

            .Lambda._cls0()
            {
                arg$1 = TooltipFragment._cls1.this;
            }
        }

        ViewCompat.postOnAnimation(val$activityRoot.getRootView(), new .Lambda._cls0());
    }

    .Lambda._cls0()
    {
        this$0 = final_tooltipfragment;
        val$activityRoot = View.this;
        super();
    }
}
