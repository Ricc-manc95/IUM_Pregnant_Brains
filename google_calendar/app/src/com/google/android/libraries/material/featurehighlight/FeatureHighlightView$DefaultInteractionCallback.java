// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.view.ViewGroup;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView

public final class view extends view
{

    private final Runnable removeFromParentRunnable = new _cls1();
    public final FeatureHighlightView view;

    public final void onDismiss()
    {
        view.hideWithDismissAnimation(removeFromParentRunnable);
    }

    public final void onTargetViewClick()
    {
        view.hideWithAcceptAnimation(removeFromParentRunnable);
    }

    public _cls1.this._cls0(FeatureHighlightView featurehighlightview)
    {
        class _cls1
            implements Runnable
        {

            private final FeatureHighlightView.DefaultInteractionCallback this$0;

            public final void run()
            {
                if (view.getParent() instanceof ViewGroup)
                {
                    ((ViewGroup)view.getParent()).removeView(view);
                }
            }

            _cls1()
            {
                this$0 = FeatureHighlightView.DefaultInteractionCallback.this;
                super();
            }
        }

        view = featurehighlightview;
    }
}
