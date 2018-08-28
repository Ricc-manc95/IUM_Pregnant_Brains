// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.view.animation.Animation;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveSubcategorySelectionFragment

final class this._cls0
    implements android.view.animation.nFragment._cls3
{

    private final GrooveSubcategorySelectionFragment this$0;

    public final void onAnimationEnd(Animation animation)
    {
        titleView.requestFocus();
    }

    public final void onAnimationRepeat(Animation animation)
    {
    }

    public final void onAnimationStart(Animation animation)
    {
    }

    ()
    {
        this$0 = GrooveSubcategorySelectionFragment.this;
        super();
    }
}
