// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package com.google.android.calendar.utils.viewpager:
//            UserAwareViewPager

public abstract class TransparentViewPager extends UserAwareViewPager
{

    public boolean dispatchOnPager;

    public TransparentViewPager(Context context)
    {
        super(context);
        dispatchOnPager = false;
    }

    public TransparentViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        dispatchOnPager = false;
    }

    public abstract int[] getDispatchableViewIds();

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if (!super.mFakeDragging) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        motionevent.getAction();
        JVM INSTR tableswitch 0 0: default 32
    //                   0 49;
           goto _L3 _L4
_L3:
        if (!dispatchOnPager || !super.onInterceptTouchEvent(motionevent))
        {
            return false;
        }
          goto _L1
_L4:
        View view = findViewWithTag(Integer.valueOf(getCurrentItem()));
        if (view == null) goto _L3; else goto _L5
_L5:
        float f;
        float f1;
        int ai[];
        int i;
        int j;
        f = motionevent.getX();
        f1 = motionevent.getY();
        ai = getDispatchableViewIds();
        j = ai.length;
        i = 0;
_L9:
        if (i >= j) goto _L3; else goto _L6
_L6:
        View view1 = view.findViewById(ai[i]);
        if (view1 == null || (float)view1.getLeft() >= f || f > (float)view1.getRight() || (float)view1.getTop() >= f1 || f1 > (float)view1.getBottom()) goto _L8; else goto _L7
_L7:
        dispatchOnPager = true;
          goto _L3
_L8:
        i++;
          goto _L9
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag1 = false;
        motionevent.getAction();
        JVM INSTR tableswitch 1 1: default 24
    //                   1 47;
           goto _L1 _L2
_L1:
        boolean flag;
        flag = flag1;
        if (dispatchOnPager)
        {
            flag = flag1;
            if (super.onTouchEvent(motionevent))
            {
                flag = true;
            }
        }
_L4:
        return flag;
_L2:
        flag = flag1;
        if (dispatchOnPager)
        {
            dispatchOnPager = false;
            return super.onTouchEvent(motionevent);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
