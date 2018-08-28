// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UserActionUtil;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip:
//            TooltipFragment

final class arg._cls1
    implements ener
{

    private final TooltipFragment arg$1;

    public final void onDismiss()
    {
        TooltipFragment tooltipfragment = arg$1;
        if (tooltipfragment.userTouched && tooltipfragment.userDismissed)
        {
            tooltipfragment.userActionUtil.persistUserChoice(tooltipfragment.logContext, com.google.identity.growth.proto.ion.DISMISSED);
        }
        tooltipfragment.removeFragment();
    }

    ener(TooltipFragment tooltipfragment)
    {
        arg$1 = tooltipfragment;
    }
}
