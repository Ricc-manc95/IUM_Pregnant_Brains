// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.os.Handler;

// Referenced classes of package android.support.design.snackbar:
//            SnackbarManager, BaseTransientBottomBar

final class this._cls0
    implements AttachStateChangeListener
{

    public final BaseTransientBottomBar this$0;

    public final void onViewAttachedToWindow$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0()
    {
    }

    public final void onViewDetachedFromWindow$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0()
    {
        BaseTransientBottomBar basetransientbottombar = BaseTransientBottomBar.this;
        if (SnackbarManager.snackbarManager == null)
        {
            SnackbarManager.snackbarManager = new SnackbarManager();
        }
        class _cls1
            implements Runnable
        {

            private final BaseTransientBottomBar._cls5 this$1;

            public final void run()
            {
                onViewHidden(3);
            }

            _cls1()
            {
                this$1 = BaseTransientBottomBar._cls5.this;
                super();
            }
        }

        if (SnackbarManager.snackbarManager.isCurrentOrNext(basetransientbottombar.managerCallback))
        {
            BaseTransientBottomBar.handler.post(new _cls1());
        }
    }

    _cls1()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
