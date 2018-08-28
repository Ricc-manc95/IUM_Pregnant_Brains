// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.libraries.stitch.util.ThreadUtil;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            PrimesLog, PrimesStartupMeasure

final class view
    implements android.view.StartupMeasure.MyOnPreDrawListener
{

    public final PrimesStartupMeasure this$0;
    private View view;

    public final boolean onPreDraw()
    {
        if (view != null)
        {
            view.getViewTreeObserver().removeOnPreDrawListener(this);
            class _cls1
                implements Runnable
            {

                private final PrimesStartupMeasure.MyOnPreDrawListener this$1;

                public final void run()
                {
                    firstDrawnAt = SystemClock.elapsedRealtime();
                    runOnDrawListeners();
                }

            _cls1()
            {
                this$1 = PrimesStartupMeasure.MyOnPreDrawListener.this;
                super();
            }
            }

            _cls1 _lcls1 = new _cls1();
            if (ThreadUtil.sMainThreadHandler == null)
            {
                ThreadUtil.sMainThreadHandler = new Handler(Looper.getMainLooper());
            }
            ThreadUtil.sMainThreadHandler.post(_lcls1);
        }
        view = null;
_L2:
        return true;
        Object obj;
        obj;
        PrimesLog.log(3, "PrimesStartupMeasure", ((Throwable) (obj)), "Error handling PrimesStartupMeasure's onPreDraw", new Object[0]);
        view = null;
        if (true) goto _L2; else goto _L1
_L1:
        obj;
        view = null;
        throw obj;
    }

    _cls1(View view1)
    {
        this$0 = PrimesStartupMeasure.this;
        super();
        view = view1;
    }
}
