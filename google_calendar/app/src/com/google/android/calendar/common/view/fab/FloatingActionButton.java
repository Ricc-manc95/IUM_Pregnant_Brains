// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view.fab;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.widget.ImageButton;
import com.google.android.calendar.utils.animation.QuantumInterpolators;

public class FloatingActionButton extends ImageButton
{

    private int color;
    private int iconsResId;
    public boolean isHidden;
    private Resources resources;
    private int size;

    public FloatingActionButton(Context context)
    {
        super(context);
        init(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init(context, attributeset);
    }

    public FloatingActionButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        init(context, attributeset);
    }

    private final void createDrawable()
    {
        ShapeDrawable shapedrawable = new ShapeDrawable(new OvalShape());
        shapedrawable.setIntrinsicHeight(size);
        shapedrawable.setIntrinsicWidth(size);
        shapedrawable.setBounds(new Rect(0, 0, size, size));
        shapedrawable.getPaint().setColor(color);
        RippleDrawable rippledrawable = new RippleDrawable(ColorStateList.valueOf(getResources().getColor(0x7f0d0087)), shapedrawable, null);
        Drawable adrawable[];
        int i;
        if (iconsResId == 0)
        {
            i = 1;
        } else
        {
            i = 2;
        }
        adrawable = new Drawable[i];
        adrawable[0] = rippledrawable;
        if (iconsResId != 0)
        {
            i = iconsResId;
            Object obj = BitmapFactory.decodeResource(getResources(), i);
            obj = new BitmapDrawable(resources, ((android.graphics.Bitmap) (obj)));
            ((BitmapDrawable) (obj)).setGravity(17);
            adrawable[1] = ((Drawable) (obj));
        }
        setImageDrawable(new LayerDrawable(adrawable));
        invalidate();
    }

    private final void init(Context context, AttributeSet attributeset)
    {
        resources = context.getResources();
        iconsResId = 0;
        if (attributeset != null)
        {
            context = context.obtainStyledAttributes(attributeset, new int[] {
                0x1010119, 0x10101a5
            });
            if (context != null)
            {
                iconsResId = context.getResourceId(0, 0);
                color = context.getResourceId(1, -1);
                context.recycle();
            }
        }
        setSize(0);
        isHidden = false;
        createDrawable();
    }

    public final void hide(boolean flag)
    {
        if (!isHidden)
        {
            if (flag)
            {
                AnimatorSet animatorset = new AnimatorSet();
                animatorset.playTogether(new Animator[] {
                    ObjectAnimator.ofFloat(this, "scaleX", new float[] {
                        1.0F, 0.0F
                    }), ObjectAnimator.ofFloat(this, "scaleY", new float[] {
                        1.0F, 0.0F
                    })
                });
                animatorset.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                animatorset.setDuration(100L);
                animatorset.start();
            } else
            {
                setScaleX(0.0F);
                setScaleY(0.0F);
            }
            isHidden = true;
        }
    }

    public void setColor(int i)
    {
        color = i;
        createDrawable();
    }

    public void setIcon(int i)
    {
        iconsResId = i;
        createDrawable();
    }

    public void setSize(int i)
    {
        if (i == 1)
        {
            size = resources.getDimensionPixelSize(0x7f0e015e);
        } else
        {
            size = resources.getDimensionPixelSize(0x7f0e0154);
        }
        createDrawable();
    }
}
