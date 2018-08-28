// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuPresenter

final class mPopup
    implements Runnable
{

    private mPopup mPopup;
    private final ActionMenuPresenter this$0;

    public final void run()
    {
        if (mMenu != null)
        {
            MenuBuilder menubuilder = mMenu;
            if (menubuilder.mCallback != null)
            {
                menubuilder.mCallback.pup(menubuilder);
            }
        }
        View view = (View)mMenuView;
        if (view != null && view.getWindowToken() != null && mPopup.tryShow())
        {
            mOverflowPopup = mPopup;
        }
        mPostedOpenRunnable = null;
    }

    public ( )
    {
        this$0 = ActionMenuPresenter.this;
        super();
        mPopup = ;
    }
}
