// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.ValueAnimator;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import java.util.ArrayList;

final class this._cls0
    implements android.animation.eekPagerHelper._cls1
{

    private final keDragged this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        float f3;
        if (!((ViewPager) (ger)).mFakeDragging)
        {
            keDragged = 0.0F;
            FindTimeGridViewPager findtimegridviewpager = ger;
            if (!((ViewPager) (findtimegridviewpager)).mIsBeingDragged)
            {
                findtimegridviewpager.mFakeDragging = true;
                findtimegridviewpager.setScrollState(1);
                findtimegridviewpager.mLastMotionX = 0.0F;
                findtimegridviewpager.mInitialMotionX = 0.0F;
                MotionEvent motionevent;
                long l;
                if (((ViewPager) (findtimegridviewpager)).mVelocityTracker == null)
                {
                    findtimegridviewpager.mVelocityTracker = VelocityTracker.obtain();
                } else
                {
                    ((ViewPager) (findtimegridviewpager)).mVelocityTracker.clear();
                }
                l = SystemClock.uptimeMillis();
                motionevent = MotionEvent.obtain(l, l, 0, 0.0F, 0.0F, 0);
                ((ViewPager) (findtimegridviewpager)).mVelocityTracker.addMovement(motionevent);
                motionevent.recycle();
                findtimegridviewpager.mFakeDragBeginTime = l;
            }
        }
        f3 = ((Float)valueanimator.getAnimatedValue()).floatValue() - keDragged;
        valueanimator = ger;
        if (!((ViewPager) (valueanimator)).mFakeDragging)
        {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (((ViewPager) (valueanimator)).mAdapter != null)
        {
            valueanimator.mLastMotionX = ((ViewPager) (valueanimator)).mLastMotionX + f3;
            float f2 = (float)valueanimator.getScrollX() - f3;
            int i = valueanimator.getMeasuredWidth() - valueanimator.getPaddingLeft() - valueanimator.getPaddingRight();
            float f = i;
            float f5 = ((ViewPager) (valueanimator)).mFirstOffset;
            float f1 = i;
            float f4 = ((ViewPager) (valueanimator)).mLastOffset;
            Object obj = (android.support.v4.view.erHelper.pager)((ViewPager) (valueanimator)).mItems.get(0);
            android.support.v4.view.erHelper._cls1 _lcls1 = (android.support.v4.view.erHelper.pager)((ViewPager) (valueanimator)).mItems.get(((ViewPager) (valueanimator)).mItems.size() - 1);
            long l1;
            if (((android.support.v4.view.erHelper.pager) (obj)).pager != 0)
            {
                f = ((android.support.v4.view.erHelper.pager) (obj)).pager * (float)i;
            } else
            {
                f *= f5;
            }
            if (_lcls1.pager != ((ViewPager) (valueanimator)).mAdapter.getCount() - 1)
            {
                f1 = _lcls1.pager * (float)i;
            } else
            {
                f1 *= f4;
            }
            if (f2 >= f)
            {
                if (f2 > f1)
                {
                    f = f1;
                } else
                {
                    f = f2;
                }
            }
            valueanimator.mLastMotionX = ((ViewPager) (valueanimator)).mLastMotionX + (f - (float)(int)f);
            valueanimator.scrollTo((int)f, valueanimator.getScrollY());
            valueanimator.pageScrolled((int)f);
            l1 = SystemClock.uptimeMillis();
            obj = MotionEvent.obtain(((ViewPager) (valueanimator)).mFakeDragBeginTime, l1, 2, ((ViewPager) (valueanimator)).mLastMotionX, 0.0F, 0);
            ((ViewPager) (valueanimator)).mVelocityTracker.addMovement(((MotionEvent) (obj)));
            ((MotionEvent) (obj)).recycle();
        }
        valueanimator = this._cls0.this;
        valueanimator.keDragged = ((keDragged) (valueanimator)).keDragged + f3;
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
