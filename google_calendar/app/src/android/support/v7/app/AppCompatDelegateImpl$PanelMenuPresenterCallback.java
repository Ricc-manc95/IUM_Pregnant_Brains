// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v7.view.menu.MenuBuilder;
import android.view.Window;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImpl

final class this._cls0
    implements android.support.v7.view.menu.
{

    private final AppCompatDelegateImpl this$0;

    public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
label0:
        {
            MenuBuilder menubuilder1 = menubuilder.getRootMenu();
            AppCompatDelegateImpl appcompatdelegateimpl;
            boolean flag1;
            if (menubuilder1 != menubuilder)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            appcompatdelegateimpl = AppCompatDelegateImpl.this;
            if (flag1)
            {
                menubuilder = menubuilder1;
            }
            menubuilder = appcompatdelegateimpl.findMenuPanel(menubuilder);
            if (menubuilder != null)
            {
                if (!flag1)
                {
                    break label0;
                }
                callOnPanelClosed(((this._cls0) (menubuilder))., menubuilder, menubuilder1);
                closePanel(menubuilder, true);
            }
            return;
        }
        closePanel(menubuilder, flag);
    }

    public final boolean onOpenSubMenu(MenuBuilder menubuilder)
    {
        if (menubuilder == null && mHasActionBar)
        {
            android.view.rCallback rcallback = mWindow.getCallback();
            if (rcallback != null && !mIsDestroyed)
            {
                rcallback.rCallback(108, menubuilder);
            }
        }
        return true;
    }

    ()
    {
        this$0 = AppCompatDelegateImpl.this;
        super();
    }
}
