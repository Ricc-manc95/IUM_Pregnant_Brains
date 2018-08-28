// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopup;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.SubMenuBuilder;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuPresenter

final class ack extends MenuPopupHelper
{

    private final ActionMenuPresenter this$0;

    protected final void onDismiss()
    {
        mActionButtonPopup = null;
        super.onDismiss();
    }

    public (Context context, SubMenuBuilder submenubuilder, View view)
    {
        boolean flag = false;
        this$0 = ActionMenuPresenter.this;
        super(context, submenubuilder, view, false, 0x7f01007c);
        if ((((MenuItemImpl)submenubuilder.getItem()).mFlags & 0x20) == 32)
        {
            flag = true;
        }
        if (!flag)
        {
            if (mOverflowButton == null)
            {
                context = (View)mMenuView;
            } else
            {
                context = mOverflowButton;
            }
            super.mAnchorView = context;
        }
        actionmenupresenter = mPopupPresenterCallback;
        super.mPresenterCallback = ActionMenuPresenter.this;
        if (super.mPopup != null)
        {
            super.mPopup.setCallback(ActionMenuPresenter.this);
        }
    }
}
