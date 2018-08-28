// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.View;

// Referenced classes of package android.support.transition:
//            ViewUtils, ViewUtilsBase

final class mView extends AnimatorListenerAdapter
{

    private boolean mLayerTypeChanged;
    private final View mView;

    public final void onAnimationEnd(Animator animator)
    {
        animator = mView;
        ViewUtils.IMPL.setTransitionAlpha(animator, 1.0F);
        if (mLayerTypeChanged)
        {
            mView.setLayerType(0, null);
        }
    }

    public final void onAnimationStart(Animator animator)
    {
        if (ViewCompat.hasOverlappingRendering(mView) && mView.getLayerType() == 0)
        {
            mLayerTypeChanged = true;
            mView.setLayerType(2, null);
        }
    }

    (View view)
    {
        mLayerTypeChanged = false;
        mView = view;
    }
}
