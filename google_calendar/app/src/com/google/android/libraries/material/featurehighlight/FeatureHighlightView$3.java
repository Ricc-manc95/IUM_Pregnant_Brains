// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView

final class this._cls0 extends android.view.nGestureListener
{

    private final FeatureHighlightView this$0;

    public final boolean onSingleTapUp(MotionEvent motionevent)
    {
        motionevent = FeatureHighlightView.this;
        if (((FeatureHighlightView) (motionevent)).targetView != null)
        {
            ((FeatureHighlightView) (motionevent)).targetView.performClick();
        }
        if (!((FeatureHighlightView) (motionevent)).hiding)
        {
            ((FeatureHighlightView) (motionevent)).callback.onTargetViewClick();
        }
        return true;
    }

    teractionCallback()
    {
        this$0 = FeatureHighlightView.this;
        super();
    }
}
