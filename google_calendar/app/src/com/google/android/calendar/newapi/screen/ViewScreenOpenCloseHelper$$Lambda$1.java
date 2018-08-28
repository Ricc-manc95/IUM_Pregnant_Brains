// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import com.google.android.calendar.timely.animations.EventInfoAnimationView;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreenOpenCloseHelper

final class arg._cls2
    implements Runnable
{

    private final ViewScreenOpenCloseHelper arg$1;
    private final Runnable arg$2;

    public final void run()
    {
        ViewScreenOpenCloseHelper viewscreenopenclosehelper = arg$1;
        Runnable runnable = arg$2;
        if (viewscreenopenclosehelper.animationView != null)
        {
            viewscreenopenclosehelper.animationView.setVisibility(8);
        }
        if (runnable != null)
        {
            runnable.run();
        }
    }

    (ViewScreenOpenCloseHelper viewscreenopenclosehelper, Runnable runnable)
    {
        arg$1 = viewscreenopenclosehelper;
        arg$2 = runnable;
    }
}
