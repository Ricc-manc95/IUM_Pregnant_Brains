// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.support.design.internal.CircularBorderDrawable;
import android.support.design.internal.CircularBorderDrawableLollipop;
import android.support.design.internal.VisibilityAwareImageButton;
import android.support.design.ripple.RippleUtils;
import android.support.design.shadow.ShadowDrawableWrapper;
import android.support.design.shadow.ShadowViewDelegate;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.design.floatingactionbutton:
//            FloatingActionButtonImpl

final class FloatingActionButtonImplLollipop extends FloatingActionButtonImpl
{

    private InsetDrawable insetDrawable;

    FloatingActionButtonImplLollipop(VisibilityAwareImageButton visibilityawareimagebutton, ShadowViewDelegate shadowviewdelegate)
    {
        super(visibilityawareimagebutton, shadowviewdelegate);
    }

    private final Animator createElevationAnimator(float f, float f1)
    {
        AnimatorSet animatorset = new AnimatorSet();
        animatorset.play(ObjectAnimator.ofFloat(view, "elevation", new float[] {
            f
        }).setDuration(0L)).with(ObjectAnimator.ofFloat(view, View.TRANSLATION_Z, new float[] {
            f1
        }).setDuration(100L));
        animatorset.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
        return animatorset;
    }

    public final float getElevation()
    {
        return view.getElevation();
    }

    final void getPadding(Rect rect)
    {
        if (shadowViewDelegate.isCompatPaddingEnabled())
        {
            float f = shadowViewDelegate.getRadius();
            float f1 = getElevation() + pressedTranslationZ;
            int i = (int)Math.ceil(ShadowDrawableWrapper.calculateHorizontalPadding(f1, f, false));
            int j = (int)Math.ceil(ShadowDrawableWrapper.calculateVerticalPadding(f1, f, false));
            rect.set(i, j, i, j);
            return;
        } else
        {
            rect.set(0, 0, 0, 0);
            return;
        }
    }

    final void jumpDrawableToCurrentState()
    {
    }

    final CircularBorderDrawable newCircularDrawable()
    {
        return new CircularBorderDrawableLollipop();
    }

    final GradientDrawable newGradientDrawableForShape()
    {
        return new AlwaysStatefulGradientDrawable();
    }

    final void onDrawableStateChanged(int ai[])
    {
label0:
        {
label1:
            {
                if (android.os.Build.VERSION.SDK_INT == 21)
                {
                    if (!view.isEnabled())
                    {
                        break label0;
                    }
                    view.setElevation(elevation);
                    if (!view.isPressed())
                    {
                        break label1;
                    }
                    view.setTranslationZ(pressedTranslationZ);
                }
                return;
            }
            if (view.isFocused() || view.isHovered())
            {
                view.setTranslationZ(hoveredFocusedTranslationZ);
                return;
            } else
            {
                view.setTranslationZ(0.0F);
                return;
            }
        }
        view.setElevation(0.0F);
        view.setTranslationZ(0.0F);
    }

    final void onElevationsChanged(float f, float f1, float f2)
    {
        if (android.os.Build.VERSION.SDK_INT == 21)
        {
            view.refreshDrawableState();
        } else
        {
            StateListAnimator statelistanimator = new StateListAnimator();
            statelistanimator.addState(PRESSED_ENABLED_STATE_SET, createElevationAnimator(f, f2));
            statelistanimator.addState(HOVERED_FOCUSED_ENABLED_STATE_SET, createElevationAnimator(f, f1));
            statelistanimator.addState(FOCUSED_ENABLED_STATE_SET, createElevationAnimator(f, f1));
            statelistanimator.addState(HOVERED_ENABLED_STATE_SET, createElevationAnimator(f, f1));
            AnimatorSet animatorset = new AnimatorSet();
            ArrayList arraylist = new ArrayList();
            arraylist.add(ObjectAnimator.ofFloat(view, "elevation", new float[] {
                f
            }).setDuration(0L));
            if (android.os.Build.VERSION.SDK_INT >= 22 && android.os.Build.VERSION.SDK_INT <= 24)
            {
                arraylist.add(ObjectAnimator.ofFloat(view, View.TRANSLATION_Z, new float[] {
                    view.getTranslationZ()
                }).setDuration(100L));
            }
            arraylist.add(ObjectAnimator.ofFloat(view, View.TRANSLATION_Z, new float[] {
                0.0F
            }).setDuration(100L));
            animatorset.playSequentially((Animator[])arraylist.toArray(new Animator[0]));
            animatorset.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
            statelistanimator.addState(ENABLED_STATE_SET, animatorset);
            statelistanimator.addState(EMPTY_STATE_SET, createElevationAnimator(0.0F, 0.0F));
            view.setStateListAnimator(statelistanimator);
        }
        if (shadowViewDelegate.isCompatPaddingEnabled())
        {
            updatePadding();
        }
    }

    final void onPaddingUpdated(Rect rect)
    {
        if (shadowViewDelegate.isCompatPaddingEnabled())
        {
            insetDrawable = new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
            shadowViewDelegate.setBackgroundDrawable(insetDrawable);
            return;
        } else
        {
            shadowViewDelegate.setBackgroundDrawable(rippleDrawable);
            return;
        }
    }

    final boolean requirePreDrawListener()
    {
        return false;
    }

    final void setBackgroundDrawable(ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode, ColorStateList colorstatelist1, int i)
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
        if (i > 0)
        {
            borderDrawable = createBorderDrawable(i, colorstatelist);
            colorstatelist = new LayerDrawable(new Drawable[] {
                borderDrawable, shapeDrawable
            });
        } else
        {
            borderDrawable = null;
            colorstatelist = shapeDrawable;
        }
        rippleDrawable = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(colorstatelist1), colorstatelist, null);
        contentBackground = rippleDrawable;
        shadowViewDelegate.setBackgroundDrawable(rippleDrawable);
    }

    final void setRippleColor(ColorStateList colorstatelist)
    {
        if (rippleDrawable instanceof RippleDrawable)
        {
            ((RippleDrawable)rippleDrawable).setColor(RippleUtils.convertToRippleDrawableColor(colorstatelist));
            return;
        } else
        {
            super.setRippleColor(colorstatelist);
            return;
        }
    }

    private class AlwaysStatefulGradientDrawable extends GradientDrawable
    {

        public final boolean isStateful()
        {
            return true;
        }

        AlwaysStatefulGradientDrawable()
        {
        }
    }

}
