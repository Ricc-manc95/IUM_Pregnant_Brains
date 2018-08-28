// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.view.View;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView

final class this._cls0
    implements android.view.eListener
{

    private final FeatureHighlightView this$0;

    public final void onViewAttachedToWindow(View view)
    {
    }

    public final void onViewDetachedFromWindow(View view)
    {
        view = FeatureHighlightView.this;
        if (!((FeatureHighlightView) (view)).hiding)
        {
            ((FeatureHighlightView) (view)).callback.onDismiss();
        }
    }

    teractionCallback()
    {
        this$0 = FeatureHighlightView.this;
        super();
    }
}
