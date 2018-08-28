// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewPropertyAnimator;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.animation.QuantumInterpolators;

public final class ContentDisplayHandler
    implements android.os.Handler.Callback
{

    private Runnable animationEndCallback;
    private boolean animationFinished;
    public Callback callback;
    public final Handler handler = new Handler(Looper.getMainLooper(), this);
    private boolean loadingFinished;
    public final View progressView;
    public long progressViewVisibleStartedAt;

    public ContentDisplayHandler(View view, Callback callback1)
    {
        callback = callback1;
        progressView = view;
        progressView.setVisibility(4);
        progressView.setAlpha(0.0F);
    }

    public final boolean handleMessage(Message message)
    {
        boolean flag = false;
        if (message.what == 1339)
        {
            progressView.setVisibility(0);
            progressView.animate().alpha(1.0F).setDuration(250L).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN).start();
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            progressViewVisibleStartedAt = l;
        } else
        {
            boolean flag1 = loadingFinished;
            if (message.what == 1337)
            {
                flag = true;
            }
            loadingFinished = flag | flag1;
            if (loadingFinished)
            {
                progressView.animate().alpha(0.0F).setDuration(250L).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN).setListener(new _cls1()).start();
            }
            if (message.what == 1338)
            {
                animationFinished = true;
                animationEndCallback = (Runnable)message.obj;
            }
            if (loadingFinished && animationFinished && callback != null)
            {
                callback.onShowContent(animationEndCallback);
                return true;
            }
        }
        return true;
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final ContentDisplayHandler this$0;

        public final void onAnimationEnd(Animator animator)
        {
            progressView.setVisibility(8);
        }

        _cls1()
        {
            this$0 = ContentDisplayHandler.this;
            super();
        }
    }


    private class Callback
    {

        public abstract void onShowContent(Runnable runnable);
    }

}
