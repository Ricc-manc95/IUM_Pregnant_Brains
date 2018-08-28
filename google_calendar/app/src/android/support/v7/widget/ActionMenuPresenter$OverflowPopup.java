// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopup;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuPresenter

final class ack extends MenuPopupHelper
{

    private final ActionMenuPresenter this$0;

    protected final void onDismiss()
    {
        if (mMenu != null)
        {
            mMenu.close();
        }
        mOverflowPopup = null;
        super.onDismiss();
    }

    public A(Context context, MenuBuilder menubuilder, View view, boolean flag)
    {
        this$0 = ActionMenuPresenter.this;
        super(context, menubuilder, view, true, 0x7f01007c);
        super.mDropDownGravity = 0x800005;
        actionmenupresenter = mPopupPresenterCallback;
        super.mPresenterCallback = ActionMenuPresenter.this;
        if (super.mPopup != null)
        {
            super.mPopup.setCallback(ActionMenuPresenter.this);
        }
    }
}
