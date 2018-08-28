// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            InnerZoneDrawable, OuterHighlightDrawable, LayoutManager, FeatureHighlightContent

public final class FeatureHighlightView extends ViewGroup
{

    private static final boolean USE_ACCESSIBILITY_DELEGATE;
    public AccessibilityHelper accessibilityHelper;
    public final AccessibilityManager accessibilityManager = (AccessibilityManager)getContext().getSystemService("accessibility");
    private final android.view.View.OnAttachStateChangeListener attachStateListener = new _cls1();
    public InteractionCallback callback;
    public final Rect confiningBounds = new Rect();
    public View confiningView;
    public FeatureHighlightContent content;
    public final Rect contentBounds = new Rect();
    public boolean contentShownOnSwipe;
    private final int coordinates[] = new int[2];
    public Animator currentAnimation;
    private final GestureDetectorCompat gestureDetector;
    public boolean hiding;
    public final InnerZoneDrawable innerZone;
    public boolean isUserSwiping;
    public final LayoutManager layoutManager = new LayoutManager(this);
    public int originalTextColor;
    public final OuterHighlightDrawable outerHighlight;
    public float percentageHidden;
    public boolean pinToClosestVerticalEdge;
    public boolean swipeToDismissEnabled;
    public final Rect targetBounds = new Rect();
    public Drawable targetDrawable;
    private GestureDetectorCompat targetGestureDetector;
    public int targetTextColor;
    public View targetView;
    private Paint targetViewLayerPaint;
    private boolean touchOriginatedOnTarget;
    public float userScrollDistance;

    public FeatureHighlightView(Context context)
    {
        super(context);
        hiding = false;
        contentShownOnSwipe = false;
        userScrollDistance = 0.0F;
        percentageHidden = 0.0F;
        isUserSwiping = false;
        swipeToDismissEnabled = true;
        setId(0x7f100019);
        setWillNotDraw(false);
        innerZone = new InnerZoneDrawable(context);
        innerZone.setCallback(this);
        outerHighlight = new OuterHighlightDrawable(context);
        outerHighlight.setCallback(this);
        gestureDetector = new GestureDetectorCompat(context, new _cls2());
        gestureDetector.mImpl.setIsLongpressEnabled(false);
        _cls3 _lcls3 = new _cls3();
        targetGestureDetector = new GestureDetectorCompat(getContext(), _lcls3);
        targetGestureDetector.mImpl.setIsLongpressEnabled(false);
        context = (FeatureHighlightContent)LayoutInflater.from(context).inflate(0x7f05016c, this, false);
        if (content != null)
        {
            removeView(content.asView());
        }
        if (context == null)
        {
            throw new NullPointerException();
        } else
        {
            content = (FeatureHighlightContent)context;
            addView(context.asView(), 0);
            context = new DefaultInteractionCallback();
            content.setCallback(context);
            callback = context;
            setVisibility(8);
            return;
        }
    }

    private final void getRelativeLocation(int ai[], View view)
    {
        getLocationInWindow(ai);
        int i = ai[0];
        int j = ai[1];
        view.getLocationInWindow(ai);
        ai[0] = ai[0] - i;
        ai[1] = ai[1] - j;
    }

    protected final boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof android.view.ViewGroup.MarginLayoutParams;
    }

    public final boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        if (accessibilityHelper != null && accessibilityHelper.dispatchHoverEvent(motionevent))
        {
            return true;
        } else
        {
            return super.dispatchHoverEvent(motionevent);
        }
    }

    protected final android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new android.view.ViewGroup.MarginLayoutParams(-2, -2);
    }

    public final android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new android.view.ViewGroup.MarginLayoutParams(getContext(), attributeset);
    }

    protected final android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new android.view.ViewGroup.MarginLayoutParams(layoutparams);
    }

    public final void hideWithAcceptAnimation(final Runnable hideRunnable)
    {
        if (!hiding)
        {
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(content.asView(), "alpha", new float[] {
                0.0F
            }).setDuration(200L);
            objectanimator.setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.fastOutLinearIn);
            Object obj = outerHighlight;
            obj = ObjectAnimator.ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
                PropertyValuesHolder.ofFloat("scale", new float[] {
                    ((OuterHighlightDrawable) (obj)).getScale(), 1.125F
                }), PropertyValuesHolder.ofInt("alpha", new int[] {
                    ((OuterHighlightDrawable) (obj)).getAlpha(), 0
                })
            });
            ((Animator) (obj)).setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.fastOutLinearIn);
            obj = ((Animator) (obj)).setDuration(200L);
            Animator animator = innerZone.createDismissAnimation();
            AnimatorSet animatorset = new AnimatorSet();
            animatorset.playTogether(new Animator[] {
                objectanimator, obj, animator
            });
            animatorset.addListener(new _cls8());
            if (currentAnimation != null)
            {
                currentAnimation.cancel();
            }
            if (animatorset != null)
            {
                currentAnimation = animatorset;
                currentAnimation.start();
            }
        }
    }

    public final void hideWithDismissAnimation(final Runnable hideRunnable)
    {
        if (!hiding)
        {
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(content.asView(), "alpha", new float[] {
                0.0F
            }).setDuration(200L);
            objectanimator.setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.fastOutLinearIn);
            float f = targetBounds.exactCenterX();
            float f1 = outerHighlight.centerX;
            float f2 = targetBounds.exactCenterY();
            float f3 = outerHighlight.centerY;
            Object obj = outerHighlight;
            Object obj1 = PropertyValuesHolder.ofFloat("scale", new float[] {
                ((OuterHighlightDrawable) (obj)).getScale(), 0.0F
            });
            Object obj2 = PropertyValuesHolder.ofInt("alpha", new int[] {
                ((OuterHighlightDrawable) (obj)).getAlpha(), 0
            });
            obj = ObjectAnimator.ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
                obj1, PropertyValuesHolder.ofFloat("translationX", new float[] {
                    ((OuterHighlightDrawable) (obj)).getTranslationX(), f - f1
                }), PropertyValuesHolder.ofFloat("translationY", new float[] {
                    ((OuterHighlightDrawable) (obj)).getTranslationY(), f2 - f3
                }), obj2
            });
            ((Animator) (obj)).setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.fastOutLinearIn);
            obj = ((Animator) (obj)).setDuration(200L);
            obj1 = innerZone.createDismissAnimation();
            obj2 = new AnimatorSet();
            ((AnimatorSet) (obj2)).playTogether(new Animator[] {
                objectanimator, obj, obj1
            });
            ((AnimatorSet) (obj2)).addListener(new _cls8());
            if (currentAnimation != null)
            {
                currentAnimation.cancel();
            }
            if (obj2 != null)
            {
                currentAnimation = ((Animator) (obj2));
                currentAnimation.start();
            }
        }
    }

    protected final void onDetachedFromWindow()
    {
        if (targetView != null)
        {
            targetView.removeOnAttachStateChangeListener(attachStateListener);
        }
        if (currentAnimation != null)
        {
            currentAnimation.removeAllListeners();
            currentAnimation.cancel();
            currentAnimation = null;
        }
        super.onDetachedFromWindow();
    }

    protected final void onDraw(Canvas canvas)
    {
        canvas.save();
        if (confiningView != null)
        {
            canvas.clipRect(confiningBounds);
        }
        outerHighlight.draw(canvas);
        if (!pinToClosestVerticalEdge)
        {
            innerZone.draw(canvas);
        }
        if (targetDrawable != null)
        {
            float f = targetBounds.exactCenterX();
            float f1 = targetBounds.exactCenterY();
            canvas.translate(f - (float)targetDrawable.getBounds().width() / 2.0F, f1 - (float)targetDrawable.getBounds().height() / 2.0F);
            targetDrawable.draw(canvas);
        } else
        if (targetView != null)
        {
            canvas.translate(targetBounds.left, targetBounds.top);
            if (targetViewLayerPaint != null)
            {
                int i = canvas.saveLayer(null, targetViewLayerPaint, 31);
                targetView.draw(canvas);
                canvas.restoreToCount(i);
            } else
            {
                targetView.draw(canvas);
            }
        } else
        {
            throw new IllegalStateException("Neither target view nor drawable was set");
        }
        canvas.restore();
    }

    protected final void onLayout(boolean flag, int i, int j, int k, int l)
    {
        Object obj;
        Object obj1;
        Rect rect1;
        Object obj2;
        boolean flag1;
        if (targetView != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalStateException(String.valueOf("Target view must be set before layout"));
        }
        getRelativeLocation(coordinates, targetView);
        targetBounds.set(coordinates[0], coordinates[1], coordinates[0] + targetView.getWidth(), coordinates[1] + targetView.getHeight());
        if (targetDrawable != null)
        {
            Drawable drawable = targetDrawable;
            int k1 = getResources().getDimensionPixelSize(0x7f0e0288) / 2;
            int i1 = Math.max(drawable.getBounds().height() / 2, k1);
            k1 = Math.max(drawable.getBounds().width() / 2, k1);
            int l1 = targetBounds.centerX();
            int i2 = targetBounds.centerY();
            targetBounds.set(l1 - k1, i2 - i1, k1 + l1, i2 + i1);
        }
        int j1;
        if (confiningView != null)
        {
            getRelativeLocation(coordinates, confiningView);
            confiningBounds.set(coordinates[0], coordinates[1], coordinates[0] + confiningView.getMeasuredWidth(), coordinates[1] + confiningView.getMeasuredHeight());
        } else
        {
            confiningBounds.set(i, j, k, l);
        }
        outerHighlight.setBounds(confiningBounds);
        if (!pinToClosestVerticalEdge)
        {
            innerZone.setBounds(confiningBounds);
        }
        obj1 = layoutManager;
        obj = targetBounds;
        rect1 = confiningBounds;
        obj2 = ((LayoutManager) (obj1)).view.content.asView();
        if (!((Rect) (obj)).isEmpty() && !rect1.isEmpty()) goto _L2; else goto _L1
_L1:
        ((View) (obj2)).layout(0, 0, 0, 0);
_L3:
        ((LayoutManager) (obj1)).textBounds.set(((View) (obj2)).getLeft(), ((View) (obj2)).getTop(), ((View) (obj2)).getRight(), ((View) (obj2)).getBottom());
        obj2 = ((LayoutManager) (obj1)).view.outerHighlight;
        Object obj3 = ((LayoutManager) (obj1)).textBounds;
        flag = ((LayoutManager) (obj1)).pinToClosestVerticalEdge;
        ((OuterHighlightDrawable) (obj2)).targetBounds.set(((Rect) (obj)));
        ((OuterHighlightDrawable) (obj2)).textBounds.set(((Rect) (obj3)));
        float f = ((Rect) (obj)).exactCenterX();
        float f1 = ((Rect) (obj)).exactCenterY();
        if (flag)
        {
            Rect rect2;
            if (((Rect) (obj)).centerY() < rect1.centerY())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            k = ((OuterHighlightDrawable) (obj2)).outerVisualPadding << 1;
            obj2.centerX = f / 2.0F;
            if (i != 0)
            {
                i = ((Rect) (obj3)).bottom;
                j = (int)((9F * (f * f) - (float)(i * 8 * k) - (float)(k * k * 4)) / (float)(k * 8));
                obj2.centerY = 0 - j;
            } else
            {
                i = rect1.height() - ((Rect) (obj3)).top;
                j = (int)((9F * (f * f) - (float)(i * 8 * k) - (float)(k * k * 4)) / (float)(k * 8));
                obj2.centerY = rect1.height() + j;
            }
            obj2.radius = i + j + k;
        } else
        {
            Rect rect = ((OuterHighlightDrawable) (obj2)).getBounds();
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            if (Math.min(f1 - (float)rect.top, (float)rect.bottom - f1) < (float)((OuterHighlightDrawable) (obj2)).centerThresholdPx)
            {
                obj2.centerX = f;
                f = f1;
            } else
            {
                if (f <= rect.exactCenterX())
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    f = ((Rect) (obj3)).exactCenterX() + (float)((OuterHighlightDrawable) (obj2)).offsetHorizontalOffsetPx;
                } else
                {
                    f = ((Rect) (obj3)).exactCenterX() - (float)((OuterHighlightDrawable) (obj2)).offsetHorizontalOffsetPx;
                }
                obj2.centerX = f;
                if (f1 <= rect.exactCenterY())
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    f = ((Rect) (obj3)).exactCenterY() + (float)((OuterHighlightDrawable) (obj2)).offsetVerticalOffsetPx;
                } else
                {
                    f = ((Rect) (obj3)).exactCenterY() - (float)((OuterHighlightDrawable) (obj2)).offsetVerticalOffsetPx;
                }
            }
            obj2.centerY = f;
            f4 = ((OuterHighlightDrawable) (obj2)).outerVisualPadding;
            f3 = ((OuterHighlightDrawable) (obj2)).centerX;
            f5 = ((OuterHighlightDrawable) (obj2)).centerY;
            f6 = ((Rect) (obj)).left;
            f1 = ((Rect) (obj)).top;
            f2 = ((Rect) (obj)).right;
            f7 = ((Rect) (obj)).bottom;
            f = (float)Math.hypot(f6 - f3, f1 - f5);
            f1 = (float)Math.hypot(f2 - f3, f1 - f5);
            f2 = (float)Math.hypot(f2 - f3, f7 - f5);
            f3 = (float)Math.hypot(f6 - f3, f7 - f5);
            if (f <= f1 || f <= f2 || f <= f3)
            {
                if (f1 > f2 && f1 > f3)
                {
                    f = f1;
                } else
                if (f2 > f3)
                {
                    f = f2;
                } else
                {
                    f = f3;
                }
            }
            f5 = (float)Math.ceil(f);
            f3 = ((OuterHighlightDrawable) (obj2)).centerX;
            f6 = ((OuterHighlightDrawable) (obj2)).centerY;
            f7 = ((Rect) (obj3)).left;
            f1 = ((Rect) (obj3)).top;
            f2 = ((Rect) (obj3)).right;
            f8 = ((Rect) (obj3)).bottom;
            f = (float)Math.hypot(f7 - f3, f1 - f6);
            f1 = (float)Math.hypot(f2 - f3, f1 - f6);
            f2 = (float)Math.hypot(f2 - f3, f8 - f6);
            f3 = (float)Math.hypot(f7 - f3, f8 - f6);
            if (f <= f1 || f <= f2 || f <= f3)
            {
                if (f1 > f2 && f1 > f3)
                {
                    f = f1;
                } else
                if (f2 > f3)
                {
                    f = f2;
                } else
                {
                    f = f3;
                }
            }
            obj2.radius = Math.max(f5, (float)Math.ceil(f)) + f4;
        }
        ((OuterHighlightDrawable) (obj2)).invalidateSelf();
        obj = content.asView();
        getRelativeLocation(coordinates, ((View) (obj)));
        obj1 = contentBounds;
        i = coordinates[0];
        j = coordinates[1];
        k = coordinates[0];
        l = ((View) (obj)).getMeasuredWidth();
        j1 = coordinates[1];
        ((Rect) (obj1)).set(i, j, k + l, ((View) (obj)).getMeasuredHeight() + j1);
        return;
_L2:
label0:
        {
            i = ((Rect) (obj)).centerY();
            j = ((Rect) (obj)).centerX();
            if (!((LayoutManager) (obj1)).pinToClosestVerticalEdge)
            {
                obj3 = ((LayoutManager) (obj1)).view.innerZone;
                obj3.centerX = ((Rect) (obj)).exactCenterX();
                obj3.centerY = ((Rect) (obj)).exactCenterY();
                f = (float)Math.max(((Rect) (obj)).width(), ((Rect) (obj)).height()) / 2.0F;
                obj3.radius = Math.max(((InnerZoneDrawable) (obj3)).minRadius, f + (float)((InnerZoneDrawable) (obj3)).padding);
                ((InnerZoneDrawable) (obj3)).invalidateSelf();
                obj3 = ((LayoutManager) (obj1)).view.innerZone;
                rect2 = ((LayoutManager) (obj1)).innerBounds;
                f = ((InnerZoneDrawable) (obj3)).radius + (float)((InnerZoneDrawable) (obj3)).margin;
                rect2.set(Math.round(((InnerZoneDrawable) (obj3)).centerX - f), Math.round(((InnerZoneDrawable) (obj3)).centerY - f), Math.round(((InnerZoneDrawable) (obj3)).centerX + f), Math.round(((InnerZoneDrawable) (obj3)).centerY + f));
            }
            if (((LayoutManager) (obj1)).textVerticalGravityHint == 48)
            {
                break label0;
            }
            if (((LayoutManager) (obj1)).textVerticalGravityHint == 80)
            {
                i = 1;
            } else
            {
                if (i >= rect1.centerY())
                {
                    break label0;
                }
                i = 1;
            }
        }
_L4:
        if (i != 0)
        {
            i = rect1.bottom;
            k = ((LayoutManager) (obj1)).innerBounds.bottom;
            ((LayoutManager) (obj1)).measureContent(((View) (obj2)), rect1.width(), i - k);
            j = ((LayoutManager) (obj1)).getContentLeft(((View) (obj2)), rect1.left, rect1.right, ((View) (obj2)).getMeasuredWidth(), j);
            if (((LayoutManager) (obj1)).pinToClosestVerticalEdge)
            {
                i = ((Rect) (obj)).bottom + ((LayoutManager) (obj1)).verticalTextOffset;
            } else
            {
                i = ((LayoutManager) (obj1)).innerBounds.bottom;
            }
            ((View) (obj2)).layout(j, i, ((View) (obj2)).getMeasuredWidth() + j, ((View) (obj2)).getMeasuredHeight() + i);
        } else
        {
            if (((LayoutManager) (obj1)).pinToClosestVerticalEdge)
            {
                i = ((Rect) (obj)).top - rect1.top;
            } else
            {
                i = ((LayoutManager) (obj1)).innerBounds.top - rect1.top;
            }
            ((LayoutManager) (obj1)).measureContent(((View) (obj2)), rect1.width(), i);
            j = ((LayoutManager) (obj1)).getContentLeft(((View) (obj2)), rect1.left, rect1.right, ((View) (obj2)).getMeasuredWidth(), j);
            if (((LayoutManager) (obj1)).pinToClosestVerticalEdge)
            {
                i = ((Rect) (obj)).top - ((LayoutManager) (obj1)).verticalTextOffset;
            } else
            {
                i = ((LayoutManager) (obj1)).innerBounds.top;
            }
            ((View) (obj2)).layout(j, i - ((View) (obj2)).getMeasuredHeight(), ((View) (obj2)).getMeasuredWidth() + j, i);
        }
          goto _L3
        i = 0;
          goto _L4
    }

    protected final void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(i);
        int l = android.view.View.MeasureSpec.getSize(j);
        setMeasuredDimension(resolveSize(k, i), resolveSize(l, j));
    }

    public final boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        i = motionevent.getActionMasked();
        if (i == 0)
        {
            touchOriginatedOnTarget = targetBounds.contains((int)motionevent.getX(), (int)motionevent.getY());
        }
        if (!touchOriginatedOnTarget || targetView == null) goto _L2; else goto _L1
_L1:
        MotionEvent motionevent1 = motionevent;
        if (targetGestureDetector != null)
        {
            targetGestureDetector.mImpl.onTouchEvent(motionevent);
            motionevent1 = motionevent;
            if (i == 1)
            {
                motionevent1 = MotionEvent.obtain(motionevent);
                motionevent1.setAction(3);
            }
        }
        targetView.onTouchEvent(motionevent1);
_L4:
        return true;
_L2:
        gestureDetector.mImpl.onTouchEvent(motionevent);
        if (i != 1 || !isUserSwiping)
        {
            continue; /* Loop/switch isn't completed */
        }
        isUserSwiping = false;
        if (userScrollDistance <= getResources().getDimension(0x7f0e028b))
        {
            break; /* Loop/switch isn't completed */
        }
        if (!hiding)
        {
            callback.onDismiss();
        }
_L5:
        if (!hiding)
        {
            callback._mth51662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIILG_0();
            return true;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (currentAnimation != null)
        {
            currentAnimation.cancel();
        }
        motionevent = ObjectAnimator.ofFloat(content.asView(), "alpha", new float[] {
            1.0F - percentageHidden, 1.0F
        }).setDuration(150L);
        motionevent.setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.linearOutSlowIn);
        float f = targetBounds.exactCenterX();
        float f1 = outerHighlight.centerX;
        float f2 = targetBounds.exactCenterY();
        float f3 = outerHighlight.centerY;
        Animator animator = outerHighlight.createShowAnimation(f - f1, f2 - f3, 1.0F - percentageHidden);
        Animator animator1 = innerZone.createShowAnimation(1.0F - percentageHidden);
        AnimatorSet animatorset = new AnimatorSet();
        animatorset.playTogether(new Animator[] {
            motionevent, animator, animator1
        });
        animatorset.addListener(new _cls7());
        if (currentAnimation != null)
        {
            currentAnimation.cancel();
        }
        if (animatorset != null)
        {
            currentAnimation = animatorset;
            currentAnimation.start();
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    public final void setBodyTextAlignment(int i)
    {
        content.setBodyTextAlignment(i);
    }

    public final void setBodyTextAppearance(int i)
    {
        content.setBodyTextAppearance(i);
    }

    public final void setBodyTextSize(float f)
    {
        content.setBodyTextSize(f);
    }

    public final void setCenterThreshold(int i)
    {
        outerHighlight.centerThresholdPx = i;
    }

    public final void setContentMaxWidth(int i)
    {
        layoutManager.contentMaxWidth = i;
    }

    public final void setDismissActionTextAlignment(int i)
    {
        content.setDismissActionTextAlignment(i);
    }

    public final void setDismissActionTextAppearance(int i)
    {
        content.setDismissActionTextAppearance(i);
    }

    public final void setHeaderTextAlignment(int i)
    {
        content.setHeaderTextAlignment(i);
    }

    public final void setHeaderTextAppearance(int i)
    {
        content.setHeaderTextAppearance(i);
    }

    public final void setHeaderTextSize(float f)
    {
        content.setHeaderTextSize(f);
    }

    public final void setInnerColor(int i)
    {
        InnerZoneDrawable innerzonedrawable = innerZone;
        innerzonedrawable.paint.setColor(i);
        innerzonedrawable.finalAlpha = innerzonedrawable.paint.getAlpha();
        innerzonedrawable.pulsePaint.setColor(i);
        innerzonedrawable.invalidateSelf();
    }

    public final void setOuterColor(int i)
    {
        OuterHighlightDrawable outerhighlightdrawable = outerHighlight;
        outerhighlightdrawable.paint.setColor(i);
        outerhighlightdrawable.finalAlpha = outerhighlightdrawable.paint.getAlpha();
        outerhighlightdrawable.invalidateSelf();
    }

    public final void setOuterVisualPadding(int i)
    {
        outerHighlight.outerVisualPadding = i;
    }

    public final void setTargetTextColor(int i)
    {
        targetTextColor = i;
    }

    public final void setTargetViewTintColor(int i)
    {
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(i, android.graphics.PorterDuff.Mode.SRC_IN));
        targetViewLayerPaint = paint;
    }

    public final void setTextVerticalGravityHint(int i)
    {
        layoutManager.textVerticalGravityHint = i;
    }

    public final void setVisibility(int i)
    {
        if (i != getVisibility()) goto _L2; else goto _L1
_L1:
        super.setVisibility(i);
_L4:
        return;
_L2:
        super.setVisibility(i);
        if (targetView != null)
        {
            if (i != 8 && i != 4)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (USE_ACCESSIBILITY_DELEGATE)
            {
                ViewCompat.setImportantForAccessibility(targetView, 0);
            }
            android.view.ViewParent viewparent = ViewCompat.getParentForAccessibility(this);
            if (viewparent instanceof View)
            {
                ((View)viewparent).sendAccessibilityEvent(32);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
        if (i != 0) goto _L4; else goto _L3
_L3:
        sendAccessibilityEvent(32);
        if (USE_ACCESSIBILITY_DELEGATE)
        {
            ViewCompat.setImportantForAccessibility(targetView, 2);
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    final void setupForTarget(View view)
    {
        if (!ViewCompat.isAttachedToWindow(this))
        {
            throw new IllegalStateException(String.valueOf("Must be attached to window before showing"));
        }
        if (view == null)
        {
            throw new NullPointerException();
        }
        targetView = (View)view;
        if (USE_ACCESSIBILITY_DELEGATE)
        {
            accessibilityHelper = new AccessibilityHelper(view);
            ViewCompat.setAccessibilityDelegate(this, accessibilityHelper);
        }
        boolean flag;
        if (targetTextColor != 0 && (targetView instanceof TextView))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            TextView textview = (TextView)view;
            originalTextColor = textview.getCurrentTextColor();
            textview.setTextColor(targetTextColor);
        }
        if (getVisibility() == 8)
        {
            setVisibility(4);
        }
        view.addOnAttachStateChangeListener(attachStateListener);
    }

    protected final boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == outerHighlight || drawable == innerZone || drawable == targetDrawable;
    }

    static 
    {
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        USE_ACCESSIBILITY_DELEGATE = flag;
    }

    private class _cls1
        implements android.view.View.OnAttachStateChangeListener
    {

        private final FeatureHighlightView this$0;

        public final void onViewAttachedToWindow(View view)
        {
        }

        public final void onViewDetachedFromWindow(View view)
        {
            view = FeatureHighlightView.this;
            if (!((FeatureHighlightView) (view)).hiding)
            {
                ((FeatureHighlightView) (view)).callback.onDismiss();
            }
        }

        _cls1()
        {
            this$0 = FeatureHighlightView.this;
            super();
        }
    }


    private class _cls2 extends android.view.GestureDetector.SimpleOnGestureListener
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

        _cls2()
        {
            this$0 = FeatureHighlightView.this;
            super();
        }
    }


    private class _cls3 extends android.view.GestureDetector.SimpleOnGestureListener
    {

        private final FeatureHighlightView this$0;

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            motionevent = FeatureHighlightView.this;
            if (((FeatureHighlightView) (motionevent)).targetView != null)
            {
                ((FeatureHighlightView) (motionevent)).targetView.performClick();
            }
            if (!((FeatureHighlightView) (motionevent)).hiding)
            {
                ((FeatureHighlightView) (motionevent)).callback.onTargetViewClick();
            }
            return true;
        }

        _cls3()
        {
            this$0 = FeatureHighlightView.this;
            super();
        }
    }


    private class DefaultInteractionCallback extends InteractionCallback
    {
        private class InteractionCallback
        {

            public void onDismiss()
            {
            }

            public void onSwipeCompleted$51662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIILG_0()
            {
            }

            public void onSwipeStarted$51662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIILG_0()
            {
            }

            public void onTargetViewClick()
            {
            }

            public InteractionCallback()
            {
            }
        }


        private final Runnable removeFromParentRunnable = new _cls1();
        public final FeatureHighlightView view;

        public final void onDismiss()
        {
            view.hideWithDismissAnimation(removeFromParentRunnable);
        }

        public final void onTargetViewClick()
        {
            view.hideWithAcceptAnimation(removeFromParentRunnable);
        }

        public DefaultInteractionCallback()
        {
            class _cls1
                implements Runnable
            {

                private final DefaultInteractionCallback this$0;

                public final void run()
                {
                    if (view.getParent() instanceof ViewGroup)
                    {
                        ((ViewGroup)view.getParent()).removeView(view);
                    }
                }

                _cls1()
                {
                    this$0 = DefaultInteractionCallback.this;
                    super();
                }
            }

            view = FeatureHighlightView.this;
        }
    }


    private class _cls8 extends AnimatorListenerAdapter
    {

        private final FeatureHighlightView this$0;
        private final Runnable val$hideRunnable;

        public final void onAnimationEnd(Animator animator)
        {
            boolean flag1 = false;
            hiding = false;
            animator = FeatureHighlightView.this;
            boolean flag = flag1;
            if (((FeatureHighlightView) (animator)).targetTextColor != 0)
            {
                flag = flag1;
                if (((FeatureHighlightView) (animator)).targetView instanceof TextView)
                {
                    flag = true;
                }
            }
            if (flag)
            {
                ((TextView)targetView).setTextColor(originalTextColor);
            }
            setVisibility(8);
            currentAnimation = null;
            if (hideRunnable != null)
            {
                hideRunnable.run();
            }
        }

        public final void onAnimationStart(Animator animator)
        {
            hiding = true;
        }

        _cls8()
        {
            this$0 = FeatureHighlightView.this;
            hideRunnable = runnable;
            super();
        }
    }


    private class _cls7 extends AnimatorListenerAdapter
    {

        private final FeatureHighlightView this$0;

        public final void onAnimationEnd(Animator animator)
        {
            currentAnimation = null;
            if (!hiding)
            {
                animator = FeatureHighlightView.this;
                Object obj1 = FeatureHighlightView.this;
                Object obj = ((FeatureHighlightView) (obj1)).innerZone;
                obj1 = ((FeatureHighlightView) (obj1)).getContext();
                AnimatorSet animatorset = new AnimatorSet();
                ObjectAnimator objectanimator = ObjectAnimator.ofFloat(obj, "scale", new float[] {
                    1.0F, 1.1F
                }).setDuration(500L);
                ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(obj, "scale", new float[] {
                    1.1F, 1.0F
                }).setDuration(500L);
                obj = ObjectAnimator.ofPropertyValuesHolder(obj, new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofFloat("pulseScale", new float[] {
                        1.1F, 2.0F
                    }), PropertyValuesHolder.ofFloat("pulseAlpha", new float[] {
                        1.0F, 0.0F
                    })
                }).setDuration(500L);
                animatorset.play(objectanimator);
                animatorset.play(objectanimator1).with(((Animator) (obj))).after(objectanimator);
                animatorset.setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.fastOutSlowIn);
                animatorset.setStartDelay(500L);
                animatorset.addListener(new CancelableLoopingListener(animatorset, -1, null));
                animatorset.addListener(new InnerZoneDrawable.SystemAnimatorsDisabledListener(((Context) (obj1))));
                if (((FeatureHighlightView) (animator)).currentAnimation != null)
                {
                    ((FeatureHighlightView) (animator)).currentAnimation.cancel();
                }
                if (animatorset != null)
                {
                    animator.currentAnimation = animatorset;
                    ((FeatureHighlightView) (animator)).currentAnimation.start();
                }
            }
        }

        _cls7()
        {
            this$0 = FeatureHighlightView.this;
            super();
        }
    }


    private class AccessibilityHelper extends ExploreByTouchHelper
    {

        private final Rect bounds = new Rect();
        private final String dismissString;
        private final View targetView;
        private final FeatureHighlightView view;

        protected final int getVirtualViewAt(float f, float f1)
        {
label0:
            {
                boolean flag = true;
                if (!view.content.isEmpty() && view.contentBounds.contains((int)f, (int)f1))
                {
                    return 1;
                }
                if (view.targetBounds.contains((int)f, (int)f1))
                {
                    return 2;
                }
                if (view.confiningBounds.contains(Math.round(f), Math.round(f1)))
                {
                    OuterHighlightDrawable outerhighlightdrawable = view.outerHighlight;
                    float f2 = outerhighlightdrawable.centerX;
                    float f3 = outerhighlightdrawable.centerY;
                    if ((float)Math.hypot(f2 - f, f3 - f1) >= outerhighlightdrawable.radius)
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        break label0;
                    }
                }
                return 3;
            }
            return -1;
        }

        protected final void getVisibleVirtualViews(List list)
        {
            if (!view.content.isEmpty())
            {
                list.add(Integer.valueOf(1));
            }
            list.add(Integer.valueOf(2));
            list.add(Integer.valueOf(3));
        }

        protected final boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
        {
            if (j != 16)
            {
                break MISSING_BLOCK_LABEL_73;
            }
            if (i != 2) goto _L2; else goto _L1
_L1:
            bundle = view;
            if (((FeatureHighlightView) (bundle)).targetView != null)
            {
                ((FeatureHighlightView) (bundle)).targetView.performClick();
            }
            if (!((FeatureHighlightView) (bundle)).hiding)
            {
                ((FeatureHighlightView) (bundle)).callback.onTargetViewClick();
            }
_L4:
            return true;
_L2:
            if (i != 3)
            {
                break MISSING_BLOCK_LABEL_73;
            }
            bundle = view;
            if (((FeatureHighlightView) (bundle)).hiding) goto _L4; else goto _L3
_L3:
            ((FeatureHighlightView) (bundle)).callback.onDismiss();
            return true;
            return false;
        }

        protected final void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
        {
            if (i == 1)
            {
                accessibilityevent.getText().add(view.content.getText());
            } else
            {
                if (i == 2)
                {
                    accessibilityevent.setContentDescription(targetView.getContentDescription());
                    Object obj = targetView;
                    if (android.os.Build.VERSION.SDK_INT >= 23)
                    {
                        obj = ((View) (obj)).getAccessibilityClassName();
                    } else
                    {
                        obj = obj.getClass().getName();
                    }
                    accessibilityevent.setClassName(((CharSequence) (obj)));
                    return;
                }
                if (i == 3)
                {
                    accessibilityevent.setContentDescription(dismissString);
                    return;
                }
            }
        }

        protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            i;
            JVM INSTR tableswitch 1 3: default 28
        //                       1 58
        //                       2 112
        //                       3 253;
               goto _L1 _L2 _L3 _L4
_L1:
            bounds.setEmpty();
            accessibilitynodeinfocompat.mInfo.setContentDescription("");
_L6:
            Rect rect = bounds;
            accessibilitynodeinfocompat.mInfo.setBoundsInParent(rect);
            return;
_L2:
            bounds.set(view.contentBounds);
            Object obj = view.content.getText();
            accessibilitynodeinfocompat.mInfo.setText(((CharSequence) (obj)));
            obj = view.getContentDescription();
            accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj)));
            continue; /* Loop/switch isn't completed */
_L3:
            bounds.set(view.targetBounds);
            Object obj1;
            boolean flag;
            if (targetView instanceof TextView)
            {
                CharSequence charsequence = ((TextView)targetView).getText();
                accessibilitynodeinfocompat.mInfo.setText(charsequence);
            } else
            {
                CharSequence charsequence1 = targetView.getContentDescription();
                obj1 = charsequence1;
                if (charsequence1 == null)
                {
                    obj1 = "";
                }
                accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj1)));
            }
            obj1 = targetView;
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                obj1 = ((View) (obj1)).getAccessibilityClassName();
            } else
            {
                obj1 = obj1.getClass().getName();
            }
            accessibilitynodeinfocompat.mInfo.setClassName(((CharSequence) (obj1)));
            flag = targetView.isClickable();
            accessibilitynodeinfocompat.mInfo.setClickable(flag);
            accessibilitynodeinfocompat.mInfo.addAction(16);
            continue; /* Loop/switch isn't completed */
_L4:
            bounds.set(0, 0, view.getWidth(), view.getHeight());
            String s = dismissString;
            accessibilitynodeinfocompat.mInfo.setContentDescription(s);
            accessibilitynodeinfocompat.mInfo.addAction(16);
            if (true) goto _L6; else goto _L5
_L5:
        }

        AccessibilityHelper(View view1)
        {
            view = FeatureHighlightView.this;
            targetView = view1;
            dismissString = getResources().getString(0x7f13031f);
        }
    }

}
