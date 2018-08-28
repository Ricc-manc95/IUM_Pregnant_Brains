// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridSlabPage

final class this._cls0
    implements android.view.alLayoutListener
{

    private final FindTimeGridSlabPage this$0;

    public final void onGlobalLayout()
    {
        doneFab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        android.view.arams arams = (android.view.arams)doneFab.getLayoutParams();
        arams.topMargin = (doneFab.getHeight() * -1) / 2;
        doneFab.setLayoutParams(arams);
    }

    ()
    {
        this$0 = FindTimeGridSlabPage.this;
        super();
    }
}
