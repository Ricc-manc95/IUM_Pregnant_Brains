// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.SubMenu;
import android.view.View;

// Referenced classes of package android.support.v7.view.menu:
//            BaseMenuWrapper, MenuItemWrapperICS

class mInner extends ActionProvider
{

    public final android.view.ActionProvider mInner;
    private final MenuItemWrapperICS this$0;

    public final boolean hasSubMenu()
    {
        return mInner.hasSubMenu();
    }

    public final View onCreateActionView()
    {
        return mInner.onCreateActionView();
    }

    public final boolean onPerformDefaultAction()
    {
        return mInner.onPerformDefaultAction();
    }

    public final void onPrepareSubMenu(SubMenu submenu)
    {
        mInner.onPrepareSubMenu(getSubMenuWrapper(submenu));
    }

    public (Context context, android.view.ActionProvider actionprovider)
    {
        this$0 = MenuItemWrapperICS.this;
        super(context);
        mInner = actionprovider;
    }
}
