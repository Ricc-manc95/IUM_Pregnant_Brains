// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.animation;

import android.animation.Animator;

// Referenced classes of package com.google.android.libraries.material.animation:
//            CancelTrackingAnimatorListener, ChoreographerCompat

public final class CancelableLoopingListener extends CancelTrackingAnimatorListener
{

    public final Animator animator;
    public final int iterations;
    public int loopCount;
    public final Runnable onLoopRunnable;
    private ChoreographerCompat.FrameCallback startAnimationFrameCallback;

    public CancelableLoopingListener(Animator animator1, int i, Runnable runnable)
    {
        startAnimationFrameCallback = new _cls1();
        animator = animator1;
        iterations = i;
        onLoopRunnable = runnable;
    }

    public final void onAnimationEnd(Animator animator1)
    {
        if (!isCancelled(animator1))
        {
            ((ChoreographerCompat)ChoreographerCompat.threadInstance.get()).postFrameCallback(startAnimationFrameCallback);
        }
    }

    private class _cls1 extends ChoreographerCompat.FrameCallback
    {

        private final CancelableLoopingListener this$0;

        public final void doFrame$5152ILG_0()
        {
            CancelableLoopingListener cancelableloopinglistener = CancelableLoopingListener.this;
            cancelableloopinglistener.loopCount = cancelableloopinglistener.loopCount + 1;
            if (!isCancelled(animator) && !animator.isStarted())
            {
                CancelableLoopingListener cancelableloopinglistener1 = CancelableLoopingListener.this;
                boolean flag;
                if (cancelableloopinglistener1.iterations != -1 && cancelableloopinglistener1.loopCount >= cancelableloopinglistener1.iterations)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    if (onLoopRunnable != null)
                    {
                        onLoopRunnable.run();
                    }
                    animator.start();
                }
            }
        }

        _cls1()
        {
            this$0 = CancelableLoopingListener.this;
            super();
        }
    }

}
