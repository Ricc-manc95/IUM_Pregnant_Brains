// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

// Referenced classes of package android.support.v4.widget:
//            AutoScrollHelper

final class this._cls0
    implements Runnable
{

    private final AutoScrollHelper this$0;

    public final void run()
    {
        if (!mAnimating)
        {
            return;
        }
        if (mNeedsReset)
        {
            mNeedsReset = false;
            this._cls0 _lcls0 = mScroller;
            _lcls0.me = AnimationUtils.currentAnimationTimeMillis();
            _lcls0.e = -1L;
            _lcls0.me = _lcls0.me;
            _lcls0.ue = 0.5F;
            _lcls0.ue = 0;
            _lcls0.ue = 0;
        }
        this._cls0 _lcls0_1 = mScroller;
        boolean flag;
        if (_lcls0_1.e > 0L && AnimationUtils.currentAnimationTimeMillis() > _lcls0_1.e + (long)_lcls0_1.veRampDown)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || !shouldAnimate())
        {
            mAnimating = false;
            return;
        }
        if (mNeedsCancel)
        {
            mNeedsCancel = false;
            AutoScrollHelper autoscrollhelper = AutoScrollHelper.this;
            long l = SystemClock.uptimeMillis();
            MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
            autoscrollhelper.mTarget.onTouchEvent(motionevent);
            motionevent.recycle();
        }
        if (_lcls0_1.me == 0L)
        {
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        } else
        {
            long l1 = AnimationUtils.currentAnimationTimeMillis();
            float f = _lcls0_1.At(l1);
            f = f * 4F + -4F * f * f;
            long l2 = l1 - _lcls0_1.me;
            _lcls0_1.me = l1;
            _lcls0_1.me = (int)((float)l2 * f * _lcls0_1.elocityX);
            _lcls0_1.elocityX = (int)((float)l2 * f * _lcls0_1.elocityY);
            int i = _lcls0_1.elocityY;
            scrollTargetBy$514KIAAM0(i);
            ViewCompat.postOnAnimation(mTarget, this);
            return;
        }
    }

    ()
    {
        this$0 = AutoScrollHelper.this;
        super();
    }
}
