// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.view.View;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveDurationSelectionView

final class val.duration
    implements android.view.rationSelectionView._cls1
{

    private final GrooveDurationSelectionView this$0;
    private final int val$duration;

    public final void onClick(View view)
    {
        if (getContext() instanceof stener)
        {
            ((stener)getContext()).onDurationSelectionComplete(val$duration);
        }
    }

    stener()
    {
        this$0 = final_groovedurationselectionview;
        val$duration = I.this;
        super();
    }
}
