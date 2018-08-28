// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.view.ViewPager;
import android.view.VelocityTracker;

final class this._cls0 extends AnimatorListenerAdapter
{

    private final ger this$0;

    public final void onAnimationEnd(Animator animator)
    {
        animator = ger;
        if (!((ViewPager) (animator)).mFakeDragging)
        {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (((ViewPager) (animator)).mAdapter != null)
        {
            Object obj = ((ViewPager) (animator)).mVelocityTracker;
            ((VelocityTracker) (obj)).computeCurrentVelocity(1000, ((ViewPager) (animator)).mMaximumVelocity);
            int i = (int)((VelocityTracker) (obj)).getXVelocity(((ViewPager) (animator)).mActivePointerId);
            animator.mPopulatePending = true;
            int j = animator.getMeasuredWidth();
            int k = animator.getPaddingLeft();
            int l = animator.getPaddingRight();
            int i1 = animator.getScrollX();
            obj = animator.infoForCurrentScrollPosition();
            animator.setCurrentItemInternal(animator.determineTargetPage(((android.support.v4.view.erHelper.pager) (obj)).pager, ((float)i1 / (float)(j - k - l) - ((android.support.v4.view.erHelper.pager) (obj)).pager) / ((android.support.v4.view.erHelper.pager) (obj)).pager, i, (int)(((ViewPager) (animator)).mLastMotionX - ((ViewPager) (animator)).mInitialMotionX)), true, true, i);
        }
        animator.mIsBeingDragged = false;
        animator.mIsUnableToDrag = false;
        if (((ViewPager) (animator)).mVelocityTracker != null)
        {
            ((ViewPager) (animator)).mVelocityTracker.recycle();
            animator.mVelocityTracker = null;
        }
        animator.mFakeDragging = false;
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
