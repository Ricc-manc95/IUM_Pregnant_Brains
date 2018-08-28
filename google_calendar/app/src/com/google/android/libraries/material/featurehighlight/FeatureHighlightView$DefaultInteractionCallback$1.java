// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.view.ViewGroup;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView

final class this._cls0
    implements Runnable
{

    private final ew this$0;

    public final void run()
    {
        if (ew.getParent() instanceof ViewGroup)
        {
            ((ViewGroup)ew.getParent()).removeView(ew);
        }
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
