// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Handler;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class LoadingStateManager
{

    private View dataView;
    private final Handler handler = new Handler();
    private int loadingState;
    private View loadingView;
    private Runnable runnableLoadingPhase;
    private boolean wasDataLoaded;

    public LoadingStateManager(View view, View view1)
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final LoadingStateManager arg$1;

            public final void run()
            {
                arg$1.runLoadingPhase();
            }

            .Lambda._cls0()
            {
                arg$1 = LoadingStateManager.this;
            }
        }

        runnableLoadingPhase = new .Lambda._cls0();
        dataView = view;
        loadingView = view1;
        loadingState = -1;
        loadingView.setVisibility(8);
    }

    public final void onDataLoaded()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        wasDataLoaded = true;
        i = loadingState;
        i;
        JVM INSTR tableswitch 0 2: default 40
    //                   0 43
    //                   1 40
    //                   2 64;
           goto _L1 _L2 _L1 _L3
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        loadingState = 3;
        dataView.setVisibility(0);
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L3:
        loadingState = 3;
        final View view = loadingView;
        view.animate().alpha(0.0F).setDuration(250L).setListener(new _cls1());
        view = dataView;
        view.setAlpha(0.0F);
        view.setVisibility(0);
        view.animate().alpha(1.0F).setDuration(250L).setListener(null);
          goto _L1
    }

    final void runLoadingPhase()
    {
        this;
        JVM INSTR monitorenter ;
        int i = loadingState;
        i;
        JVM INSTR tableswitch 0 3: default 40
    //                   0 43
    //                   1 79
    //                   2 40
    //                   3 40;
           goto _L1 _L2 _L3 _L1 _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        loadingState = 1;
        loadingView.setVisibility(0);
        handler.postDelayed(runnableLoadingPhase, 500L);
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L3:
        loadingState = 2;
        if (wasDataLoaded)
        {
            onDataLoaded();
        }
          goto _L1
    }

    public final void startLoadingPhases()
    {
        this;
        JVM INSTR monitorenter ;
        dataView.setVisibility(8);
        wasDataLoaded = false;
        loadingState = 0;
        handler.postDelayed(runnableLoadingPhase, 500L);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final View val$view;

        public final void onAnimationEnd(Animator animator)
        {
            view.setVisibility(8);
        }

        _cls1()
        {
            view = view1;
            super();
        }
    }

}
