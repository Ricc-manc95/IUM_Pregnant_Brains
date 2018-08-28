// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ForwardingListener, ActionMenuPresenter

final class this._cls1 extends ForwardingListener
{

    private final this._cls1 this$1;

    public final ShowableListMenu getPopup()
    {
        if (mOverflowPopup == null)
        {
            return null;
        } else
        {
            return mOverflowPopup.getPopup();
        }
    }

    public final boolean onForwardingStarted()
    {
        showOverflowMenu();
        return true;
    }

    public final boolean onForwardingStopped()
    {
        if (mPostedOpenRunnable != null)
        {
            return false;
        } else
        {
            hideOverflowMenu();
            return true;
        }
    }

    (View view, ActionMenuPresenter actionmenupresenter)
    {
        this$1 = this._cls1.this;
        super(view);
    }
}
