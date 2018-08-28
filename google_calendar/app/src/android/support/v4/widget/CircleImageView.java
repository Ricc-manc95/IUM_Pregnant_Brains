// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.widget.ImageView;

final class CircleImageView extends ImageView
{

    public android.view.animation.Animation.AnimationListener mListener;
    public int mShadowRadius;

    CircleImageView(Context context, int i)
    {
        super(context);
        float f = getContext().getResources().getDisplayMetrics().density;
        mShadowRadius = (int)(3.5F * f);
        context = new ShapeDrawable(new OvalShape());
        ViewCompat.setElevation(this, f * 4F);
        context.getPaint().setColor(0xfffafafa);
        ViewCompat.setBackground(this, context);
    }

    public final void onAnimationEnd()
    {
        super.onAnimationEnd();
        if (mListener != null)
        {
            mListener.onAnimationEnd(getAnimation());
        }
    }

    public final void onAnimationStart()
    {
        super.onAnimationStart();
        if (mListener != null)
        {
            mListener.onAnimationStart(getAnimation());
        }
    }

    protected final void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
    }

    public final void setBackgroundColor(int i)
    {
        if (getBackground() instanceof ShapeDrawable)
        {
            ((ShapeDrawable)getBackground()).getPaint().setColor(i);
        }
    }

    public final void setBackgroundColorRes(int i)
    {
        setBackgroundColor(ContextCompat.getColor(getContext(), i));
    }
}
