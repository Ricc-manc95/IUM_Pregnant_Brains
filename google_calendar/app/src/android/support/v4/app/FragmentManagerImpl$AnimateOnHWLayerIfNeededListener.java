// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Animation;

final class mView extends mView
{

    public View mView;

    public final void onAnimationEnd(Animation animation)
    {
        class _cls1
            implements Runnable
        {

            private final FragmentManagerImpl.AnimateOnHWLayerIfNeededListener this$0;

            public final void run()
            {
                mView.setLayerType(0, null);
            }

            _cls1()
            {
                this$0 = FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.this;
                super();
            }
        }

        if (ViewCompat.isAttachedToWindow(mView) || android.os.dedListener.mView >= 24)
        {
            mView.post(new _cls1());
        } else
        {
            mView.setLayerType(0, null);
        }
        super.ionEnd(animation);
    }

    _cls1(View view, android.view.animation._cls1 _pcls1)
    {
        super(_pcls1);
        mView = view;
    }
}
