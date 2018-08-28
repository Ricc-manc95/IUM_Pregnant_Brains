// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

// Referenced classes of package android.support.v4.app:
//            OneShotPreDrawListener

final class addAnimation extends AnimationSet
    implements Runnable
{

    private final View mChild;
    private boolean mEnded;
    private final ViewGroup mParent;
    private boolean mTransitionEnded;

    public final boolean getTransformation(long l, Transformation transformation)
    {
        if (!mEnded) goto _L2; else goto _L1
_L1:
        if (mTransitionEnded) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (!super.getTransformation(l, transformation))
        {
            mEnded = true;
            transformation = mParent;
            OneShotPreDrawListener oneshotpredrawlistener = new OneShotPreDrawListener(transformation, this);
            transformation.getViewTreeObserver().addOnPreDrawListener(oneshotpredrawlistener);
            transformation.addOnAttachStateChangeListener(oneshotpredrawlistener);
            return true;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public final boolean getTransformation(long l, Transformation transformation, float f)
    {
        if (!mEnded) goto _L2; else goto _L1
_L1:
        if (mTransitionEnded) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (!super.getTransformation(l, transformation, f))
        {
            mEnded = true;
            transformation = mParent;
            OneShotPreDrawListener oneshotpredrawlistener = new OneShotPreDrawListener(transformation, this);
            transformation.getViewTreeObserver().addOnPreDrawListener(oneshotpredrawlistener);
            transformation.addOnAttachStateChangeListener(oneshotpredrawlistener);
            return true;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public final void run()
    {
        mParent.endViewTransition(mChild);
        mTransitionEnded = true;
    }

    (Animation animation, ViewGroup viewgroup, View view)
    {
        super(false);
        mParent = viewgroup;
        mChild = view;
        addAnimation(animation);
    }
}
