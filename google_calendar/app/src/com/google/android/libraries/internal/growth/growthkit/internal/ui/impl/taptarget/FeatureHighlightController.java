// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UserActionUtil;
import com.google.android.libraries.material.featurehighlight.FeatureHighlightCallback;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget:
//            FeatureHighlightFragment

public final class FeatureHighlightController
{

    public static final Logger logger = new Logger();
    public FeatureHighlightCallback callback;
    public FeatureHighlightFragment featureHighlightFragment;
    public final PromoContext promoContext;
    public final UserActionUtil userActionUtil;

    public FeatureHighlightController(PromoContext promocontext, UserActionUtil useractionutil)
    {
        promoContext = promocontext;
        userActionUtil = useractionutil;
    }

}
