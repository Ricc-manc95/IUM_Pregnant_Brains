// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.Button;

// Referenced classes of package com.google.android.calendar.launch.oobe:
//            WhatsNewFullScreen

final class this._cls0 extends AnimatorListenerAdapter
{

    private final WhatsNewFullScreen this$0;

    public final void onAnimationEnd(Animator animator)
    {
        super.onAnimationEnd(animator);
        doneButton.setEnabled(true);
    }

    ()
    {
        this$0 = WhatsNewFullScreen.this;
        super();
    }
}
