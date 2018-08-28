// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view.overlay;

import android.view.View;
import android.view.ViewTreeObserver;

// Referenced classes of package com.google.android.calendar.common.view.overlay:
//            OverlayFragment

final class val.contentView
    implements android.view.nGlobalLayoutListener
{

    private final OverlayFragment this$0;
    private final View val$contentView;

    public final void onGlobalLayout()
    {
        val$contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        onFinalLayoutFinished();
    }

    ()
    {
        this$0 = final_overlayfragment;
        val$contentView = View.this;
        super();
    }
}
