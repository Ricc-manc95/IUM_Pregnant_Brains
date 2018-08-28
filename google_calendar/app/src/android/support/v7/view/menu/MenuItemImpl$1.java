// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;


// Referenced classes of package android.support.v7.view.menu:
//            MenuItemImpl, MenuBuilder

final class this._cls0
    implements android.support.v4.view.VisibilityListener
{

    private final MenuItemImpl this$0;

    public final void onActionProviderVisibilityChanged$51D2ILG_0()
    {
        MenuBuilder menubuilder = mMenu;
        menubuilder.mIsVisibleItemsStale = true;
        menubuilder.onItemsChanged(true);
    }

    ilityListener()
    {
        this$0 = MenuItemImpl.this;
        super();
    }
}
