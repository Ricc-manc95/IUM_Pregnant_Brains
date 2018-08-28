// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipFactory;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.geometry.SimplePartitionItem;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;

// Referenced classes of package com.google.android.calendar.timely.animations:
//            EventInfoAnimationData

public final class EventInfoAnimationView extends FrameLayout
{

    public AnimatorSet animSet;
    public EventInfoAnimationData animationData;
    private int bgColor;
    private int bgOverlayAlpha;
    public Chip chip;
    public View chipReplacement;
    public View contentView;
    public Rect finalRect;
    public final FrameLayout headlineView;
    private float height;
    public TimelineItem item;
    private float left;
    private Paint paint;
    public boolean shouldDrawScrim;
    public Rect startRect;
    private float top;
    public float viewTranslationTop;
    private float width;

    public EventInfoAnimationView(Context context)
    {
        this(context, null);
    }

    private EventInfoAnimationView(Context context, AttributeSet attributeset)
    {
        super(context, null);
        paint = new Paint();
        bgColor = getResources().getColor(0x7f0d0313);
        headlineView = new FrameLayout(context);
        addView(headlineView);
        chip = ChipFactory.createChipWithContext(context);
        chip.setLayerType(2, null);
        chip.setFocusable(false);
        chip.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -1));
    }

    public final boolean canAnimate()
    {
        return animationData != null;
    }

    protected final void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (shouldDrawScrim)
        {
            paint.setColor(0xff000000);
            paint.setAlpha(bgOverlayAlpha);
            canvas.drawRect(0.0F, 0.0F, getWidth(), getHeight(), paint);
        }
        paint.setColor(bgColor);
        float f = left;
        float f1 = top;
        float f2 = left;
        float f3 = width;
        float f4 = top;
        canvas.drawRect(f, f1, f3 + f2, height + f4, paint);
    }

    public final void reinitialize(TimelineItem timelineitem, View view)
    {
        item = timelineitem;
        chipReplacement = view;
        chip.partitionInfo = new SimplePartitionItem(item);
        chip.setViewModel(animationData.getChipViewModel(getContext(), item));
        headlineView.removeAllViews();
        headlineView.addView(chipReplacement);
        headlineView.addView(chip);
    }

    public final void setAnimationHeight(float f)
    {
        float f1;
        int i;
        int j;
        if (finalRect == null)
        {
            i = 0;
        } else
        {
            i = finalRect.top;
        }
        if (finalRect == null)
        {
            j = getHeight();
        } else
        {
            j = finalRect.height();
        }
        f1 = startRect.top;
        top = ((float)(i - startRect.top) + viewTranslationTop) * f + f1;
        height = (float)startRect.height() + (float)(j - startRect.height()) * f;
        if (contentView != null)
        {
            contentView.setTranslationY(top);
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)contentView.getLayoutParams();
            layoutparams.gravity = 3;
            layoutparams.height = (int)height;
            contentView.requestLayout();
        }
        headlineView.setTranslationY(top);
        invalidate();
    }

    public final void setAnimationWidth(float f)
    {
        float f1;
        android.widget.FrameLayout.LayoutParams layoutparams1;
        int i;
        int j;
        if (finalRect == null)
        {
            i = 0;
        } else
        {
            i = finalRect.left;
        }
        if (finalRect == null)
        {
            j = getWidth();
        } else
        {
            j = finalRect.width();
        }
        f1 = startRect.left;
        left = (float)(i - startRect.left) * f + f1;
        width = (float)startRect.width() + (float)(j - startRect.width()) * f;
        if (contentView != null)
        {
            contentView.setTranslationX(left);
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)contentView.getLayoutParams();
            layoutparams.gravity = 3;
            layoutparams.width = (int)width;
            contentView.requestLayout();
        }
        headlineView.setTranslationX(left);
        layoutparams1 = (android.widget.FrameLayout.LayoutParams)headlineView.getLayoutParams();
        layoutparams1.gravity = 3;
        layoutparams1.width = (int)width;
        headlineView.requestLayout();
        invalidate();
    }

    public final void setHeadlineHeight(int i)
    {
        headlineView.getLayoutParams().height = i;
        headlineView.requestLayout();
    }

    public final void setOverlayAlpha(float f)
    {
        bgOverlayAlpha = (int)(255F * f);
    }

    public final void startOpenAnimation(Rect rect, int i, android.animation.Animator.AnimatorListener animatorlistener, StatusbarAnimatorCompat statusbaranimatorcompat)
    {
        viewTranslationTop = 0.0F;
        finalRect = rect;
        animSet = new AnimatorSet();
        chip.setAlpha(1.0F);
        rect = animSet.play(ObjectAnimator.ofFloat(chip, "alpha", new float[] {
            1.0F, 0.0F
        }).setDuration(150L));
        setHeadlineHeight(startRect.height());
        rect.with(ObjectAnimator.ofInt(this, "headlineHeight", new int[] {
            startRect.height(), i
        }).setDuration(150L));
        setAnimationWidth(0.0F);
        rect.with(ObjectAnimator.ofFloat(this, "animationWidth", new float[] {
            0.0F, 1.0F
        }).setDuration(150L));
        setAnimationHeight(0.0F);
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(this, "animationHeight", new float[] {
            0.0F, 1.0F
        }).setDuration(300L);
        rect.with(objectanimator);
        objectanimator.addListener(animatorlistener);
        if (shouldDrawScrim)
        {
            setOverlayAlpha(0.0F);
            rect.with(ObjectAnimator.ofFloat(this, "overlayAlpha", new float[] {
                0.0F, 0.2F
            }).setDuration(150L));
        }
        if (statusbaranimatorcompat != null)
        {
            animatorlistener = statusbaranimatorcompat.animateStatusbarColor(getResources().getColor(0x7f0d0095), 150, statusbaranimatorcompat.getStatusbarColor());
            statusbaranimatorcompat.addLightStatusbarChangeToAnimationStart(animatorlistener, false);
            animatorlistener.setStartDelay(150L);
            rect.with(animatorlistener);
        }
        animSet.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
        animSet.start();
    }
}
