// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.animation.Animator;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityManager;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView, OuterHighlightDrawable, FeatureHighlightContent, InnerZoneDrawable

final class this._cls0 extends android.view.nGestureListener
{

    private final FeatureHighlightView this$0;

    public final boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        float f2;
        float f3;
        float f4;
        float f5;
        if (!swipeToDismissEnabled)
        {
            return false;
        }
        if (!isUserSwiping)
        {
            isUserSwiping = true;
            if (currentAnimation != null)
            {
                currentAnimation.cancel();
            }
            callback._mth51662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIILG_0();
        }
        f = motionevent1.getX();
        f1 = motionevent1.getY();
        f2 = motionevent.getX();
        f3 = motionevent.getY();
        userScrollDistance = (float)Math.hypot(f2 - f, f3 - f1);
        f = getResources().getDimension(0x7f0e028a);
        percentageHidden = Math.min(1.0F, userScrollDistance / f);
        motionevent = FeatureHighlightView.this;
        f = ((FeatureHighlightView) (motionevent)).percentageHidden;
        f1 = ((FeatureHighlightView) (motionevent)).targetBounds.exactCenterX();
        f2 = ((FeatureHighlightView) (motionevent)).outerHighlight.centerX;
        f3 = ((FeatureHighlightView) (motionevent)).percentageHidden;
        f4 = ((FeatureHighlightView) (motionevent)).targetBounds.exactCenterY();
        f5 = ((FeatureHighlightView) (motionevent)).outerHighlight.centerY;
        if (((FeatureHighlightView) (motionevent)).percentageHidden <= 0.1F || !((FeatureHighlightView) (motionevent)).contentShownOnSwipe) goto _L2; else goto _L1
_L1:
        ((FeatureHighlightView) (motionevent)).content.asView().animate().alpha(0.0F).setDuration(200L).start();
        motionevent.contentShownOnSwipe = false;
_L4:
        ((FeatureHighlightView) (motionevent)).outerHighlight.setScale(1.0F - ((FeatureHighlightView) (motionevent)).percentageHidden);
        ((FeatureHighlightView) (motionevent)).outerHighlight.setAlpha((int)((1.0F - ((FeatureHighlightView) (motionevent)).percentageHidden) * 255F));
        ((FeatureHighlightView) (motionevent)).outerHighlight.setTranslationX(f * (f1 - f2));
        ((FeatureHighlightView) (motionevent)).outerHighlight.setTranslationY(f3 * (f4 - f5));
        ((FeatureHighlightView) (motionevent)).innerZone.setAlpha((int)((1.0F - ((FeatureHighlightView) (motionevent)).percentageHidden) * 255F));
        ((FeatureHighlightView) (motionevent)).innerZone.setScale(1.0F - ((FeatureHighlightView) (motionevent)).percentageHidden);
        return true;
_L2:
        if (((FeatureHighlightView) (motionevent)).percentageHidden < 0.1F && !((FeatureHighlightView) (motionevent)).contentShownOnSwipe)
        {
            ((FeatureHighlightView) (motionevent)).content.asView().animate().alpha(1.0F).setDuration(200L).start();
            motionevent.contentShownOnSwipe = true;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final boolean onSingleTapUp(MotionEvent motionevent)
    {
        float f;
        float f1;
        f = motionevent.getX();
        f1 = motionevent.getY();
        if (accessibilityHelper == null || !accessibilityManager.isTouchExplorationEnabled() || ((ExploreByTouchHelper) (accessibilityHelper)).mAccessibilityFocusedVirtualViewId != 3) goto _L2; else goto _L1
_L1:
        motionevent = FeatureHighlightView.this;
        if (!((FeatureHighlightView) (motionevent)).hiding)
        {
            ((FeatureHighlightView) (motionevent)).callback.onDismiss();
        }
_L4:
        return true;
_L2:
        if (!confiningBounds.contains(Math.round(f), Math.round(f1)))
        {
            break; /* Loop/switch isn't completed */
        }
        motionevent = outerHighlight;
        float f2 = ((OuterHighlightDrawable) (motionevent)).centerX;
        float f3 = ((OuterHighlightDrawable) (motionevent)).centerY;
        boolean flag;
        if ((float)Math.hypot(f2 - f, f3 - f1) < ((OuterHighlightDrawable) (motionevent)).radius)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L4; else goto _L3
_L3:
        motionevent = FeatureHighlightView.this;
        if (!((FeatureHighlightView) (motionevent)).hiding)
        {
            ((FeatureHighlightView) (motionevent)).callback.onDismiss();
            return true;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    ()
    {
        this$0 = FeatureHighlightView.this;
        super();
    }
}
