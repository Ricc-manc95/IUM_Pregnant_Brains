// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.MenuItem;
import java.util.List;

// Referenced classes of package android.support.v7.view.menu:
//            CascadingMenuPopup, MenuBuilder

final class this._cls0
    implements MenuItemHoverListener
{

    public final CascadingMenuPopup this$0;

    public final void onItemHoverEnter(final MenuBuilder menu, final MenuItem item)
    {
        int i;
        int j;
        mSubMenuHoverHandler.removeCallbacksAndMessages(null);
        i = 0;
        j = mShowingMenus.size();
_L4:
label0:
        {
            if (i >= j)
            {
                break MISSING_BLOCK_LABEL_158;
            }
            if (menu == ((scadingMenuInfo)mShowingMenus.get(i)).menu)
            {
                break label0;
            } else
            {
                i++;
                continue; /* Loop/switch isn't completed */
            }
        }
_L2:
        if (i == -1)
        {
            return;
        }
        i++;
        class _cls1
            implements Runnable
        {

            private final CascadingMenuPopup._cls3 this$1;
            private final MenuItem val$item;
            private final MenuBuilder val$menu;
            private final CascadingMenuPopup.CascadingMenuInfo val$nextInfo;

            public final void run()
            {
                if (nextInfo != null)
                {
                    mShouldCloseImmediately = true;
                    nextInfo.menu.close(false);
                    mShouldCloseImmediately = false;
                }
                if (item.isEnabled() && item.hasSubMenu())
                {
                    menu.performItemAction(item, null, 4);
                }
            }

            _cls1()
            {
                this$1 = CascadingMenuPopup._cls3.this;
                nextInfo = cascadingmenuinfo;
                item = menuitem;
                menu = menubuilder;
                super();
            }
        }

        final scadingMenuInfo nextInfo;
        long l;
        if (i < mShowingMenus.size())
        {
            nextInfo = (scadingMenuInfo)mShowingMenus.get(i);
        } else
        {
            nextInfo = null;
        }
        item = new _cls1();
        l = SystemClock.uptimeMillis();
        mSubMenuHoverHandler.postAtTime(item, menu, l + 200L);
        return;
        i = -1;
        if (true) goto _L2; else goto _L1
_L1:
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onItemHoverExit(MenuBuilder menubuilder, MenuItem menuitem)
    {
        mSubMenuHoverHandler.removeCallbacksAndMessages(menubuilder);
    }

    _cls1()
    {
        this$0 = CascadingMenuPopup.this;
        super();
    }
}
