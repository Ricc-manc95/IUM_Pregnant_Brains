// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.animation.Animator;
import android.view.View;
import com.google.android.calendar.PreventDoubleClick;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreen

final class this._cls0 extends PreventDoubleClick
{

    private final ViewScreen this$0;

    public final void onFirstClick$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0()
    {
        if (callback != null)
        {
            callback.onEditClicked();
            ViewScreen viewscreen = ViewScreen.this;
            Animator animator = viewscreen.editButtonPulseAnimator;
            View view = viewscreen.findViewById(0x7f1002ad);
            if (animator != null)
            {
                animator.cancel();
                view.setScaleX(1.0F);
                view.setScaleY(1.0F);
                view.setRotation(0.0F);
            }
            viewscreen.editButtonPulseAnimator = null;
        }
    }

    llback()
    {
        this$0 = ViewScreen.this;
        super();
    }
}
