// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;

// Referenced classes of package android.support.design.internal:
//            ThemeEnforcement

public class ForegroundLinearLayout extends LinearLayoutCompat
{

    private Drawable foreground;
    private boolean foregroundBoundsChanged;
    private int foregroundGravity;
    private boolean mForegroundInPadding;
    private final Rect overlayBounds;
    private final Rect selfBounds;

    public ForegroundLinearLayout(Context context)
    {
        this(context, null);
    }

    public ForegroundLinearLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ForegroundLinearLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        selfBounds = new Rect();
        overlayBounds = new Rect();
        foregroundGravity = 119;
        mForegroundInPadding = true;
        foregroundBoundsChanged = false;
        context = ThemeEnforcement.obtainStyledAttributes(context, attributeset, R.styleable.ForegroundLinearLayout, i, 0, new int[0]);
        foregroundGravity = context.getInt(R.styleable.ForegroundLinearLayout_android_foregroundGravity, foregroundGravity);
        attributeset = context.getDrawable(R.styleable.ForegroundLinearLayout_android_foreground);
        if (attributeset != null)
        {
            setForeground(attributeset);
        }
        mForegroundInPadding = context.getBoolean(R.styleable.ForegroundLinearLayout_foregroundInsidePadding, true);
        context.recycle();
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if (foreground != null)
        {
            Drawable drawable = foreground;
            if (foregroundBoundsChanged)
            {
                foregroundBoundsChanged = false;
                Rect rect = selfBounds;
                Rect rect1 = overlayBounds;
                int i = getRight() - getLeft();
                int j = getBottom() - getTop();
                if (mForegroundInPadding)
                {
                    rect.set(0, 0, i, j);
                } else
                {
                    rect.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), j - getPaddingBottom());
                }
                Gravity.apply(foregroundGravity, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), rect, rect1);
                drawable.setBounds(rect1);
            }
            drawable.draw(canvas);
        }
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        super.drawableHotspotChanged(f, f1);
        if (foreground != null)
        {
            foreground.setHotspot(f, f1);
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if (foreground != null && foreground.isStateful())
        {
            foreground.setState(getDrawableState());
        }
    }

    public Drawable getForeground()
    {
        return foreground;
    }

    public int getForegroundGravity()
    {
        return foregroundGravity;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if (foreground != null)
        {
            foreground.jumpToCurrentState();
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        foregroundBoundsChanged = foregroundBoundsChanged | flag;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        foregroundBoundsChanged = true;
    }

    public void setForeground(Drawable drawable)
    {
        if (foreground != drawable)
        {
            if (foreground != null)
            {
                foreground.setCallback(null);
                unscheduleDrawable(foreground);
            }
            foreground = drawable;
            if (drawable != null)
            {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful())
                {
                    drawable.setState(getDrawableState());
                }
                if (foregroundGravity == 119)
                {
                    drawable.getPadding(new Rect());
                }
            } else
            {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    public void setForegroundGravity(int i)
    {
        if (foregroundGravity != i)
        {
            if ((0x800007 & i) == 0)
            {
                i = 0x800003 | i;
            }
            int j = i;
            if ((i & 0x70) == 0)
            {
                j = i | 0x30;
            }
            foregroundGravity = j;
            if (foregroundGravity == 119 && foreground != null)
            {
                Rect rect = new Rect();
                foreground.getPadding(rect);
            }
            requestLayout();
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == foreground;
    }
}
