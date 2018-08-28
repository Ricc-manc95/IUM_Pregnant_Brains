// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.view.View;
import android.view.ViewTreeObserver;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreenController

final class this._cls0
    implements android.view.alLayoutListener
{

    private final ViewScreenController this$0;

    public final void onGlobalLayout()
    {
        viewScreen.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        onViewUpdated();
    }

    ()
    {
        this$0 = ViewScreenController.this;
        super();
    }
}
