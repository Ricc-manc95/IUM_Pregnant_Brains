// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.animations;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

public class TaskBundleAnimation extends FrameLayout
{

    public AnimatorSet animSet;
    private final int bgColor = getResources().getColor(0x7f0d0313);
    public int bgOverlayAlpha;
    public View contentView;
    private final Paint paint = new Paint();
    public boolean shouldDrawScrim;

    public TaskBundleAnimation(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public boolean canAnimate()
    {
        return contentView != null;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (shouldDrawScrim)
        {
            paint.setColor(bgColor);
            paint.setAlpha(bgOverlayAlpha);
            canvas.drawRect(0.0F, 0.0F, getWidth(), getHeight(), paint);
        }
    }

    public final void startOpenAnimation()
    {
        animSet = new AnimatorSet();
        contentView.setAlpha(0.0F);
        Object obj = contentView;
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(contentView, "alpha", new float[] {
            0.0F, 1.0F
        }).setDuration(150L);
        objectanimator.addListener(new com.google.android.calendar.utils.animation.AnimationUtils._cls4(((View) (obj)), ((View) (obj)).getLayerType()));
        obj = animSet.play(objectanimator);
        if (shouldDrawScrim)
        {
            setVisibility(0);
            bgOverlayAlpha = (int)(255F * 0.0F);
            invalidate();
            ((android.animation.AnimatorSet.Builder) (obj)).with(ObjectAnimator.ofFloat(this, "overlayAlpha", new float[] {
                0.0F, 1.0F
            }).setDuration(150L));
        }
        animSet.setInterpolator(new LinearInterpolator());
        animSet.start();
    }
}
