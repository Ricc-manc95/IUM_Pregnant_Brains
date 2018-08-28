// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ContentDisplayHandler

final class this._cls0 extends AnimatorListenerAdapter
{

    private final ContentDisplayHandler this$0;

    public final void onAnimationEnd(Animator animator)
    {
        progressView.setVisibility(8);
    }

    ()
    {
        this$0 = ContentDisplayHandler.this;
        super();
    }
}
