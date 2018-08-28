// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

// Referenced classes of package android.support.transition:
//            ViewGroupOverlayImpl, Visibility

final class r extends AnimatorListenerAdapter
{

    private final View val$finalOverlayView;
    private final ViewGroupOverlayImpl val$overlay;

    public final void onAnimationEnd(Animator animator)
    {
        val$overlay.remove(val$finalOverlayView);
    }

    layImpl(View view)
    {
        val$overlay = viewgroupoverlayimpl;
        val$finalOverlayView = view;
        super();
    }
}
