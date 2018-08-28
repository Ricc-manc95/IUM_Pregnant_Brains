// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;
import android.view.ViewTreeObserver;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridSlabPage

final class this._cls1
    implements android.view.LayoutListener
{

    private final is._cls0 this$1;

    public final void onGlobalLayout()
    {
        slabBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        if (listener != null)
        {
            listener.onSlabBarHeightUpdated(_fld0);
        }
    }

    abPageUpdatedListener()
    {
        this$1 = this._cls1.this;
        super();
    }
}
