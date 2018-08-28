// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;

// Referenced classes of package android.support.v7.view.menu:
//            MenuItemImpl, MenuBuilder, SubMenuBuilder

public interface MenuPresenter
{

    public abstract boolean collapseItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl);

    public abstract boolean expandItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl);

    public abstract boolean flagActionItems();

    public abstract void initForMenu(Context context, MenuBuilder menubuilder);

    public abstract void onCloseMenu(MenuBuilder menubuilder, boolean flag);

    public abstract boolean onSubMenuSelected(SubMenuBuilder submenubuilder);

    public abstract void setCallback(Callback callback);

    public abstract void updateMenuView(boolean flag);
}
