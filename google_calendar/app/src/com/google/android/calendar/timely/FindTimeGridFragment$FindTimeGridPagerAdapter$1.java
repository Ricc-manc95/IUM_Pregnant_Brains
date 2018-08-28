// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridFragment

final class this._cls1
    implements android.view.imeGridPagerAdapter._cls1
{

    private final this._cls1 this$1;

    public final void onClick(View view)
    {
        boolean flag;
        if (!isManualTimeSlot && suggestionIndex < bestTimesCount)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        listener._mth1(currentSuggestion, flag, isManualTimeSlot);
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
