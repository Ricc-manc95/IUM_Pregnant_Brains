// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.view.View;

// Referenced classes of package com.google.android.calendar.groove:
//            GroovePreferredTimesSelectionView

final class val.preferredTimes
    implements android.view.dTimesSelectionView._cls1
{

    private final GroovePreferredTimesSelectionView this$0;
    private final int val$preferredTimes;

    public final void onClick(View view)
    {
        if (getContext() instanceof stener)
        {
            ((stener)getContext()).onPreferredTimesSelectionComplete(val$preferredTimes);
        }
    }

    stener()
    {
        this$0 = final_groovepreferredtimesselectionview;
        val$preferredTimes = I.this;
        super();
    }
}
