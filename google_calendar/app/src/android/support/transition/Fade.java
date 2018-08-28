// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import java.util.Map;

// Referenced classes of package android.support.transition:
//            Visibility, ViewUtils, ViewUtilsBase, Transition, 
//            TransitionValues

public final class Fade extends Visibility
{

    public Fade()
    {
    }

    public Fade(int i)
    {
        if ((i & -4) != 0)
        {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        } else
        {
            super.mMode = i;
            return;
        }
    }

    private final Animator createAnimation(final View view, float f, float f1)
    {
        if (f == f1)
        {
            return null;
        } else
        {
            ViewUtils.IMPL.setTransitionAlpha(view, f);
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(view, ViewUtils.TRANSITION_ALPHA, new float[] {
                f1
            });
            objectanimator.addListener(new FadeAnimatorListener(view));
            addListener(new _cls1());
            return objectanimator;
        }
    }

    public final void captureStartValues(TransitionValues transitionvalues)
    {
        super.captureStartValues(transitionvalues);
        Map map = transitionvalues.values;
        transitionvalues = transitionvalues.view;
        map.put("android:fade:transitionAlpha", Float.valueOf(ViewUtils.IMPL.getTransitionAlpha(transitionvalues)));
    }

    public final Animator onAppear$51662RJ4E9NMIP1FEPKMATPFAPKMATQ7E9NNAS1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EP99HGMSP3IDTKM8BR1DPKMQOBKD5NMSBQ1DPKMQOBKDTP3M___0(View view, TransitionValues transitionvalues)
    {
        if (transitionvalues == null) goto _L2; else goto _L1
_L1:
        transitionvalues = (Float)transitionvalues.values.get("android:fade:transitionAlpha");
        if (transitionvalues == null) goto _L2; else goto _L3
_L3:
        float f = transitionvalues.floatValue();
_L5:
        float f1 = f;
        if (f == 1.0F)
        {
            f1 = 0.0F;
        }
        return createAnimation(view, f1, 1.0F);
_L2:
        f = 0.0F;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final Animator onDisappear$51662RJ4E9NMIP1FEPKMATPFAPKMATQ7E9NNAS1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EP99HGMSP3IDTKM8BR1DPKMQOBKD5NMSBQ1DPKMQOBKDTP3M___0(View view, TransitionValues transitionvalues)
    {
        ViewUtils.saveNonTransitionAlpha(view);
        if (transitionvalues == null) goto _L2; else goto _L1
_L1:
        transitionvalues = (Float)transitionvalues.values.get("android:fade:transitionAlpha");
        if (transitionvalues == null) goto _L2; else goto _L3
_L3:
        float f = transitionvalues.floatValue();
_L5:
        return createAnimation(view, f, 0.0F);
_L2:
        f = 1.0F;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private class FadeAnimatorListener extends AnimatorListenerAdapter
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

        FadeAnimatorListener(View view)
        {
            mLayerTypeChanged = false;
            mView = view;
        }
    }


    private class _cls1 extends TransitionListenerAdapter
    {

        private final View val$view;

        public final void onTransitionEnd(Transition transition)
        {
            View view1 = view;
            ViewUtils.IMPL.setTransitionAlpha(view1, 1.0F);
            ViewUtils.clearNonTransitionAlpha(view);
            transition.removeListener(this);
        }

        _cls1()
        {
            view = view1;
            super();
        }
    }

}
