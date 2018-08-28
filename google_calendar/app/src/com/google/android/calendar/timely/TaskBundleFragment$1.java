// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.calendar.timely.animations.TaskBundleAnimation;

// Referenced classes of package com.google.android.calendar.timely:
//            TaskBundleFragment

final class val.view
    implements android.view.obalLayoutListener
{

    private final TaskBundleFragment this$0;
    private final View val$view;

    public final void onGlobalLayout()
    {
        val$view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        animationView.startOpenAnimation();
    }

    Animation()
    {
        this$0 = final_taskbundlefragment;
        val$view = View.this;
        super();
    }
}
