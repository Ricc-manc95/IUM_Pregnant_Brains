// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.design.animation.AnimationUtils;
import android.support.design.animation.AnimatorSetCompat;
import android.support.design.animation.ImageMatrixProperty;
import android.support.design.animation.MatrixEvaluator;
import android.support.design.animation.MotionSpec;
import android.support.design.animation.MotionTiming;
import android.support.design.internal.CircularBorderDrawable;
import android.support.design.internal.StateListAnimator;
import android.support.design.internal.VisibilityAwareImageButton;
import android.support.design.ripple.RippleUtils;
import android.support.design.shadow.ShadowDrawableWrapper;
import android.support.design.shadow.ShadowViewDelegate;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.util.StateSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

class FloatingActionButtonImpl
{

    public static final TimeInterpolator ELEVATION_ANIM_INTERPOLATOR;
    public static final int EMPTY_STATE_SET[] = new int[0];
    public static final int ENABLED_STATE_SET[] = {
        0x101009e
    };
    public static final int FOCUSED_ENABLED_STATE_SET[] = {
        0x101009c, 0x101009e
    };
    public static final int HOVERED_ENABLED_STATE_SET[] = {
        0x1010367, 0x101009e
    };
    public static final int HOVERED_FOCUSED_ENABLED_STATE_SET[] = {
        0x1010367, 0x101009c, 0x101009e
    };
    public static final int PRESSED_ENABLED_STATE_SET[] = {
        0x10100a7, 0x101009e
    };
    public int animState;
    public CircularBorderDrawable borderDrawable;
    public Drawable contentBackground;
    public Animator currentAnimator;
    public MotionSpec defaultHideMotionSpec;
    public MotionSpec defaultShowMotionSpec;
    public float elevation;
    public MotionSpec hideMotionSpec;
    public float hoveredFocusedTranslationZ;
    public float imageMatrixScale;
    public int maxImageSize;
    public android.view.ViewTreeObserver.OnPreDrawListener preDrawListener;
    public float pressedTranslationZ;
    public Drawable rippleDrawable;
    public float rotation;
    public ShadowDrawableWrapper shadowDrawable;
    public final ShadowViewDelegate shadowViewDelegate;
    public Drawable shapeDrawable;
    public MotionSpec showMotionSpec;
    private final StateListAnimator stateListAnimator = new StateListAnimator();
    public final Matrix tmpMatrix = new Matrix();
    private final Rect tmpRect = new Rect();
    private final RectF tmpRectF1 = new RectF();
    private final RectF tmpRectF2 = new RectF();
    public final VisibilityAwareImageButton view;

    FloatingActionButtonImpl(VisibilityAwareImageButton visibilityawareimagebutton, ShadowViewDelegate shadowviewdelegate)
    {
        animState = 0;
        imageMatrixScale = 1.0F;
        view = visibilityawareimagebutton;
        shadowViewDelegate = shadowviewdelegate;
        visibilityawareimagebutton = stateListAnimator;
        int ai[] = PRESSED_ENABLED_STATE_SET;
        shadowviewdelegate = createElevationAnimator(new ElevateToPressedTranslationZAnimation());
        android.support.design.internal.StateListAnimator.Tuple tuple = new android.support.design.internal.StateListAnimator.Tuple(ai, shadowviewdelegate);
        shadowviewdelegate.addListener(((StateListAnimator) (visibilityawareimagebutton)).animationListener);
        ((StateListAnimator) (visibilityawareimagebutton)).tuples.add(tuple);
        visibilityawareimagebutton = stateListAnimator;
        tuple = HOVERED_FOCUSED_ENABLED_STATE_SET;
        shadowviewdelegate = createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation());
        tuple = new android.support.design.internal.StateListAnimator.Tuple(tuple, shadowviewdelegate);
        shadowviewdelegate.addListener(((StateListAnimator) (visibilityawareimagebutton)).animationListener);
        ((StateListAnimator) (visibilityawareimagebutton)).tuples.add(tuple);
        visibilityawareimagebutton = stateListAnimator;
        tuple = FOCUSED_ENABLED_STATE_SET;
        shadowviewdelegate = createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation());
        tuple = new android.support.design.internal.StateListAnimator.Tuple(tuple, shadowviewdelegate);
        shadowviewdelegate.addListener(((StateListAnimator) (visibilityawareimagebutton)).animationListener);
        ((StateListAnimator) (visibilityawareimagebutton)).tuples.add(tuple);
        visibilityawareimagebutton = stateListAnimator;
        tuple = HOVERED_ENABLED_STATE_SET;
        shadowviewdelegate = createElevationAnimator(new ElevateToHoveredFocusedTranslationZAnimation());
        tuple = new android.support.design.internal.StateListAnimator.Tuple(tuple, shadowviewdelegate);
        shadowviewdelegate.addListener(((StateListAnimator) (visibilityawareimagebutton)).animationListener);
        ((StateListAnimator) (visibilityawareimagebutton)).tuples.add(tuple);
        visibilityawareimagebutton = stateListAnimator;
        tuple = ENABLED_STATE_SET;
        shadowviewdelegate = createElevationAnimator(new ResetElevationAnimation());
        tuple = new android.support.design.internal.StateListAnimator.Tuple(tuple, shadowviewdelegate);
        shadowviewdelegate.addListener(((StateListAnimator) (visibilityawareimagebutton)).animationListener);
        ((StateListAnimator) (visibilityawareimagebutton)).tuples.add(tuple);
        visibilityawareimagebutton = stateListAnimator;
        tuple = EMPTY_STATE_SET;
        shadowviewdelegate = createElevationAnimator(new DisabledElevationAnimation());
        tuple = new android.support.design.internal.StateListAnimator.Tuple(tuple, shadowviewdelegate);
        shadowviewdelegate.addListener(((StateListAnimator) (visibilityawareimagebutton)).animationListener);
        ((StateListAnimator) (visibilityawareimagebutton)).tuples.add(tuple);
        rotation = view.getRotation();
    }

    private static ValueAnimator createElevationAnimator(ShadowAnimatorImpl shadowanimatorimpl)
    {
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
        valueanimator.setDuration(100L);
        valueanimator.addListener(shadowanimatorimpl);
        valueanimator.addUpdateListener(shadowanimatorimpl);
        valueanimator.setFloatValues(new float[] {
            0.0F, 1.0F
        });
        return valueanimator;
    }

    final void calculateImageMatrixFromScale(float f, Matrix matrix)
    {
        matrix.reset();
        Drawable drawable = view.getDrawable();
        if (drawable != null && maxImageSize != 0)
        {
            RectF rectf = tmpRectF1;
            RectF rectf1 = tmpRectF2;
            rectf.set(0.0F, 0.0F, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            rectf1.set(0.0F, 0.0F, maxImageSize, maxImageSize);
            matrix.setRectToRect(rectf, rectf1, android.graphics.Matrix.ScaleToFit.CENTER);
            matrix.postScale(f, f, (float)maxImageSize / 2.0F, (float)maxImageSize / 2.0F);
        }
    }

    final AnimatorSet createAnimator(MotionSpec motionspec, float f, float f1, float f2)
    {
        ArrayList arraylist = new ArrayList();
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(view, View.ALPHA, new float[] {
            f
        });
        motionspec.getTiming("opacity").apply(objectanimator);
        arraylist.add(objectanimator);
        objectanimator = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[] {
            f1
        });
        motionspec.getTiming("scale").apply(objectanimator);
        arraylist.add(objectanimator);
        objectanimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[] {
            f1
        });
        motionspec.getTiming("scale").apply(objectanimator);
        arraylist.add(objectanimator);
        calculateImageMatrixFromScale(f2, tmpMatrix);
        objectanimator = ObjectAnimator.ofObject(view, new ImageMatrixProperty(), new MatrixEvaluator(), new Matrix[] {
            new Matrix(tmpMatrix)
        });
        motionspec.getTiming("iconScale").apply(objectanimator);
        arraylist.add(objectanimator);
        motionspec = new AnimatorSet();
        AnimatorSetCompat.playTogether(motionspec, arraylist);
        return motionspec;
    }

    final CircularBorderDrawable createBorderDrawable(int i, ColorStateList colorstatelist)
    {
        android.content.Context context = view.getContext();
        CircularBorderDrawable circularborderdrawable = newCircularDrawable();
        int j = ContextCompat.getColor(context, 0x7f0d0093);
        int k = ContextCompat.getColor(context, 0x7f0d0092);
        int l = ContextCompat.getColor(context, 0x7f0d0090);
        int i1 = ContextCompat.getColor(context, 0x7f0d0091);
        circularborderdrawable.topOuterStrokeColor = j;
        circularborderdrawable.topInnerStrokeColor = k;
        circularborderdrawable.bottomOuterStrokeColor = l;
        circularborderdrawable.bottomInnerStrokeColor = i1;
        float f = i;
        if (circularborderdrawable.borderWidth != f)
        {
            circularborderdrawable.borderWidth = f;
            circularborderdrawable.paint.setStrokeWidth(f * 1.3333F);
            circularborderdrawable.invalidateShader = true;
            circularborderdrawable.invalidateSelf();
        }
        circularborderdrawable.setBorderTint(colorstatelist);
        return circularborderdrawable;
    }

    float getElevation()
    {
        return elevation;
    }

    void getPadding(Rect rect)
    {
        shadowDrawable.getPadding(rect);
    }

    void jumpDrawableToCurrentState()
    {
        StateListAnimator statelistanimator = stateListAnimator;
        if (statelistanimator.runningAnimator != null)
        {
            statelistanimator.runningAnimator.end();
            statelistanimator.runningAnimator = null;
        }
    }

    CircularBorderDrawable newCircularDrawable()
    {
        return new CircularBorderDrawable();
    }

    GradientDrawable newGradientDrawableForShape()
    {
        return new GradientDrawable();
    }

    void onDrawableStateChanged(int ai[])
    {
        StateListAnimator statelistanimator;
        int i;
        int j;
        statelistanimator = stateListAnimator;
        j = statelistanimator.tuples.size();
        i = 0;
_L3:
        android.support.design.internal.StateListAnimator.Tuple tuple;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        tuple = (android.support.design.internal.StateListAnimator.Tuple)statelistanimator.tuples.get(i);
        if (!StateSet.stateSetMatches(tuple.specs, ai)) goto _L2; else goto _L1
_L1:
        ai = tuple;
_L4:
        if (ai != statelistanimator.lastMatch)
        {
            if (statelistanimator.lastMatch != null && statelistanimator.runningAnimator != null)
            {
                statelistanimator.runningAnimator.cancel();
                statelistanimator.runningAnimator = null;
            }
            statelistanimator.lastMatch = ai;
            if (ai != null)
            {
                statelistanimator.runningAnimator = ((android.support.design.internal.StateListAnimator.Tuple) (ai)).animator;
                statelistanimator.runningAnimator.start();
            }
        }
        return;
_L2:
        i++;
          goto _L3
        ai = null;
          goto _L4
    }

    void onElevationsChanged(float f, float f1, float f2)
    {
        if (shadowDrawable != null)
        {
            shadowDrawable.setShadowSize(f, pressedTranslationZ + f);
            updatePadding();
        }
    }

    void onPaddingUpdated(Rect rect)
    {
    }

    boolean requirePreDrawListener()
    {
        return true;
    }

    void setBackgroundDrawable(ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode, ColorStateList colorstatelist1, int i)
    {
        Object obj = newGradientDrawableForShape();
        ((GradientDrawable) (obj)).setShape(1);
        ((GradientDrawable) (obj)).setColor(-1);
        if (android.os.Build.VERSION.SDK_INT < 23 && !(obj instanceof TintAwareDrawable))
        {
            obj = new WrappedDrawableApi21(((Drawable) (obj)));
        }
        shapeDrawable = ((Drawable) (obj));
        shapeDrawable.setTintList(colorstatelist);
        if (mode != null)
        {
            shapeDrawable.setTintMode(mode);
        }
        mode = newGradientDrawableForShape();
        mode.setShape(1);
        mode.setColor(-1);
        if (android.os.Build.VERSION.SDK_INT < 23 && !(mode instanceof TintAwareDrawable))
        {
            mode = new WrappedDrawableApi21(mode);
        }
        rippleDrawable = mode;
        rippleDrawable.setTintList(RippleUtils.convertToRippleDrawableColor(colorstatelist1));
        if (i > 0)
        {
            borderDrawable = createBorderDrawable(i, colorstatelist);
            colorstatelist = new Drawable[3];
            colorstatelist[0] = borderDrawable;
            colorstatelist[1] = shapeDrawable;
            colorstatelist[2] = rippleDrawable;
        } else
        {
            borderDrawable = null;
            colorstatelist = new Drawable[2];
            colorstatelist[0] = shapeDrawable;
            colorstatelist[1] = rippleDrawable;
        }
        contentBackground = new LayerDrawable(colorstatelist);
        shadowDrawable = new ShadowDrawableWrapper(view.getContext(), contentBackground, shadowViewDelegate.getRadius(), elevation, elevation + pressedTranslationZ);
        colorstatelist = shadowDrawable;
        colorstatelist.addPaddingForCorners = false;
        colorstatelist.invalidateSelf();
        shadowViewDelegate.setBackgroundDrawable(shadowDrawable);
    }

    void setRippleColor(ColorStateList colorstatelist)
    {
        if (rippleDrawable != null)
        {
            rippleDrawable.setTintList(RippleUtils.convertToRippleDrawableColor(colorstatelist));
        }
    }

    final void updatePadding()
    {
        Rect rect = tmpRect;
        getPadding(rect);
        onPaddingUpdated(rect);
        shadowViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    static 
    {
        ELEVATION_ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    }

    private class ElevateToPressedTranslationZAnimation extends ShadowAnimatorImpl
    {
        private class ShadowAnimatorImpl extends AnimatorListenerAdapter
            implements android.animation.ValueAnimator.AnimatorUpdateListener
        {

            private float shadowSizeEnd;
            private float shadowSizeStart;
            private final FloatingActionButtonImpl this$0;
            private boolean validValues;

            protected abstract float getTargetShadowSize();

            public void onAnimationEnd(Animator animator)
            {
                animator = shadowDrawable;
                animator.setShadowSize(shadowSizeEnd, ((ShadowDrawableWrapper) (animator)).rawMaxShadowSize);
                validValues = false;
            }

            public void onAnimationUpdate(ValueAnimator valueanimator)
            {
                if (!validValues)
                {
                    shadowSizeStart = shadowDrawable.rawShadowSize;
                    shadowSizeEnd = getTargetShadowSize();
                    validValues = true;
                }
                ShadowDrawableWrapper shadowdrawablewrapper = shadowDrawable;
                shadowdrawablewrapper.setShadowSize(shadowSizeStart + (shadowSizeEnd - shadowSizeStart) * valueanimator.getAnimatedFraction(), shadowdrawablewrapper.rawMaxShadowSize);
            }

            private ShadowAnimatorImpl()
            {
                this$0 = FloatingActionButtonImpl.this;
                super();
            }

            ShadowAnimatorImpl(byte byte0)
            {
                this();
            }
        }


        private final FloatingActionButtonImpl this$0;

        protected final float getTargetShadowSize()
        {
            return elevation + pressedTranslationZ;
        }

        ElevateToPressedTranslationZAnimation()
        {
            this$0 = FloatingActionButtonImpl.this;
            super((byte)0);
        }
    }


    private class ElevateToHoveredFocusedTranslationZAnimation extends ShadowAnimatorImpl
    {

        private final FloatingActionButtonImpl this$0;

        protected final float getTargetShadowSize()
        {
            return elevation + hoveredFocusedTranslationZ;
        }

        ElevateToHoveredFocusedTranslationZAnimation()
        {
            this$0 = FloatingActionButtonImpl.this;
            super((byte)0);
        }
    }


    private class ResetElevationAnimation extends ShadowAnimatorImpl
    {

        private final FloatingActionButtonImpl this$0;

        protected final float getTargetShadowSize()
        {
            return elevation;
        }

        ResetElevationAnimation()
        {
            this$0 = FloatingActionButtonImpl.this;
            super((byte)0);
        }
    }


    private class DisabledElevationAnimation extends ShadowAnimatorImpl
    {

        protected final float getTargetShadowSize()
        {
            return 0.0F;
        }

        DisabledElevationAnimation()
        {
            super((byte)0);
        }
    }

}
