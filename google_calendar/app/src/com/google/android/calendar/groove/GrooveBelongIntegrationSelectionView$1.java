// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.view.View;
import com.google.android.calendar.belong.BelongUtils;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveBelongIntegrationSelectionView

final class this._cls0
    implements android.view.rationSelectionView._cls1
{

    private final GrooveBelongIntegrationSelectionView this$0;

    public final void onClick(View view)
    {
        BelongUtils.onLearnMoreLinkClicked(getContext(), "creation_wizard");
    }

    ()
    {
        this$0 = GrooveBelongIntegrationSelectionView.this;
        super();
    }
}
