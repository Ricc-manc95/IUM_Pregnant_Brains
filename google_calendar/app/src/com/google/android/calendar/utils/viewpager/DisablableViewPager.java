// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

// Referenced classes of package com.google.android.calendar.utils.viewpager:
//            LayoutDirectionAwareViewPager

public class DisablableViewPager extends LayoutDirectionAwareViewPager
{

    private boolean hasScrolledLeft;
    private boolean hasScrolledRight;
    private float initialX;
    private float lastMotionX;
    public boolean leftScrollEnabled;
    public boolean rightScrollEnabled;

    public DisablableViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        leftScrollEnabled = true;
        rightScrollEnabled = true;
    }

    private final boolean swipeDirectionEnabled(MotionEvent motionevent)
    {
        if (!leftScrollEnabled || !rightScrollEnabled) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        float f;
        float f1;
        boolean flag;
        boolean flag1;
        if (motionevent.getAction() == 0)
        {
            initialX = motionevent.getX();
            hasScrolledLeft = false;
            hasScrolledRight = false;
            return true;
        }
        f = motionevent.getX() - initialX;
        f1 = motionevent.getX() - lastMotionX;
        if (f > 0.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (f < 0.0F)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        lastMotionX = motionevent.getX();
        if (flag)
        {
            hasScrolledLeft = true;
        }
        if (flag1)
        {
            hasScrolledRight = true;
        }
        if (flag1 && !leftScrollEnabled && getCurrentItem() == 0)
        {
            return false;
        }
        if (flag && !rightScrollEnabled && getCurrentItem() == 0)
        {
            return false;
        }
        if (!hasScrolledRight || f1 <= 0.0F || !rightScrollEnabled)
        {
            break; /* Loop/switch isn't completed */
        }
        if (f > 0.0F)
        {
            motionevent.setLocation(initialX, motionevent.getY());
            lastMotionX = initialX;
            return true;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (!hasScrolledLeft || f1 >= 0.0F || !leftScrollEnabled)
        {
            break; /* Loop/switch isn't completed */
        }
        if (f < 0.0F)
        {
            motionevent.setLocation(initialX, motionevent.getY());
            lastMotionX = initialX;
            return true;
        }
        if (true) goto _L1; else goto _L4
_L4:
        if (flag && !leftScrollEnabled)
        {
            return false;
        }
        if (flag1 && !rightScrollEnabled)
        {
            return false;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if (!isEnabled() || !swipeDirectionEnabled(motionevent))
        {
            break MISSING_BLOCK_LABEL_31;
        }
        boolean flag = super.onInterceptTouchEvent(motionevent);
        return flag;
        motionevent;
        ThrowableExtension.STRATEGY.printStackTrace(motionevent);
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (!isEnabled() || !swipeDirectionEnabled(motionevent))
        {
            break MISSING_BLOCK_LABEL_31;
        }
        boolean flag = super.onTouchEvent(motionevent);
        return flag;
        motionevent;
        ThrowableExtension.STRATEGY.printStackTrace(motionevent);
        return false;
    }
}
