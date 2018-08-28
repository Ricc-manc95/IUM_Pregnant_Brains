// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.animation;

import android.animation.Animator;

// Referenced classes of package com.google.android.libraries.material.animation:
//            CancelableLoopingListener, CancelTrackingAnimatorListener

final class lback extends lback
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

    er()
    {
        this$0 = CancelableLoopingListener.this;
        super();
    }
}
