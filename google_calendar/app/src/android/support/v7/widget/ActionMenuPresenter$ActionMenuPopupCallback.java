// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.ShowableListMenu;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuPresenter

final class this._cls0 extends android.support.v7.view.menu.s._cls0
{

    private final ActionMenuPresenter this$0;

    public final ShowableListMenu getPopup()
    {
        if (mActionButtonPopup != null)
        {
            return mActionButtonPopup.getPopup();
        } else
        {
            return null;
        }
    }

    ()
    {
        this$0 = ActionMenuPresenter.this;
        super();
    }
}
