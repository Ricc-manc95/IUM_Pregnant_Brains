// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.view.View;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveBelongIntegrationSelectionView

final class val.status
    implements android.view.rationSelectionView._cls2
{

    private final GrooveBelongIntegrationSelectionView this$0;
    private final int val$status;

    public final void onClick(View view)
    {
        if (getContext() instanceof stener)
        {
            ((stener)getContext()).onBelongIntegrationSelectionComplete(val$status);
        }
    }

    stener()
    {
        this$0 = final_groovebelongintegrationselectionview;
        val$status = I.this;
        super();
    }
}
