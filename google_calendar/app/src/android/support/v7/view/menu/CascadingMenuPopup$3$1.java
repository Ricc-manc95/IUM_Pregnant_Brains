// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.view.MenuItem;

// Referenced classes of package android.support.v7.view.menu:
//            CascadingMenuPopup, MenuBuilder

final class val.menu
    implements Runnable
{

    private final val.item this$1;
    private final MenuItem val$item;
    private final MenuBuilder val$menu;
    private final adingMenuInfo val$nextInfo;

    public final void run()
    {
        if (val$nextInfo != null)
        {
            mShouldCloseImmediately = true;
            val$nextInfo.menu.close(false);
            mShouldCloseImmediately = false;
        }
        if (val$item.isEnabled() && val$item.hasSubMenu())
        {
            val$menu.performItemAction(val$item, null, 4);
        }
    }

    adingMenuInfo()
    {
        this$1 = final_adingmenuinfo;
        val$nextInfo = adingmenuinfo1;
        val$item = menuitem;
        val$menu = MenuBuilder.this;
        super();
    }
}
