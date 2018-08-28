// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

// Referenced classes of package android.support.v7.widget:
//            ForwardingListener

final class this._cls0
    implements Runnable
{

    private final ForwardingListener this$0;

    public final void run()
    {
        ForwardingListener forwardinglistener = ForwardingListener.this;
        forwardinglistener.clearCallbacks();
        View view;
        for (view = forwardinglistener.mSrc; !view.isEnabled() || view.isLongClickable() || !forwardinglistener.onForwardingStarted();)
        {
            return;
        }

        view.getParent().requestDisallowInterceptTouchEvent(true);
        long l = SystemClock.uptimeMillis();
        MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        view.onTouchEvent(motionevent);
        motionevent.recycle();
        forwardinglistener.mForwarding = true;
    }

    ()
    {
        this$0 = ForwardingListener.this;
        super();
    }
}
