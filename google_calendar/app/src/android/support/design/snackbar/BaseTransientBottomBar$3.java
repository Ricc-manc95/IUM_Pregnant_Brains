// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.os.Handler;

// Referenced classes of package android.support.design.snackbar:
//            BaseTransientBottomBar

final class this._cls0
    implements this._cls0
{

    private final BaseTransientBottomBar this$0;

    public final void dismiss(int i)
    {
        BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(1, i, 0, BaseTransientBottomBar.this));
    }

    public final void show()
    {
        BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(0, BaseTransientBottomBar.this));
    }

    ()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
