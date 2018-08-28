// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;


// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightFragment, FeatureHighlightCallbackProvider, FeatureHighlightCallback

final class this._cls0
    implements Runnable
{

    private final FeatureHighlightFragment this$0;

    public final void run()
    {
        Object obj = FeatureHighlightFragment.this;
        if (((FeatureHighlightFragment) (obj)).featureHighlightCallbackProvider != null)
        {
            obj = ((FeatureHighlightFragment) (obj)).featureHighlightCallbackProvider.getFeatureHighlightCallback(((FeatureHighlightFragment) (obj)).callbackId);
        } else
        {
            obj = null;
        }
        if (obj != null)
        {
            ((FeatureHighlightCallback) (obj)).onShow$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
        }
    }

    vider()
    {
        this$0 = FeatureHighlightFragment.this;
        super();
    }
}
